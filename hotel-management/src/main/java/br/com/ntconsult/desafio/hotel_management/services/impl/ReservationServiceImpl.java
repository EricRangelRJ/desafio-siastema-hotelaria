package br.com.ntconsult.desafio.hotel_management.services.impl;

import br.com.ntconsult.desafio.hotel_management.events.produces.ReservationConfirmationProducer;
import br.com.ntconsult.desafio.hotel_management.feign.CustomerClient;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationConfirmationDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationResponseDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import br.com.ntconsult.desafio.hotel_management.model.entities.Reservation;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import br.com.ntconsult.desafio.hotel_management.services.PaymentSimulationService;
import br.com.ntconsult.desafio.hotel_management.services.ReservationService;
import br.com.ntconsult.desafio.hotel_management.repositories.HotelRepository;
import br.com.ntconsult.desafio.hotel_management.repositories.ReservationRepository;
import br.com.ntconsult.desafio.hotel_management.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.ntconsult.desafio.hotel_management.converters.ReservationConverter.*;
import static br.com.ntconsult.desafio.hotel_management.util.DataUtil.getDaysBetween;
import static java.util.Objects.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private ModelMapper modelMapper;

    private RoomRepository roomRepository;

    private ReservationRepository repository;

    private HotelRepository hotelRepository;

    private CustomerClient customerClient;

    private PaymentSimulationService paymentSimulationService;

    private final ReservationConfirmationProducer reservationConfirmationProducer;

    public ReservationServiceImpl(ModelMapper modelMapper, RoomRepository roomRepository, ReservationRepository reservationRepository,
                                  HotelRepository hotelRepository, CustomerClient customerClient, PaymentSimulationService paymentSimulationService, ReservationConfirmationProducer reservationConfirmationProducer) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
        this.repository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.customerClient = customerClient;
        this.paymentSimulationService = paymentSimulationService;
        this.reservationConfirmationProducer = reservationConfirmationProducer;
    }

    @Override
    public ReservationConfirmationDto reserveRoom(ReservationRequestDto request) {

        //Captura id do cliente via FeignClient
        UUID customerId = UUID.fromString(customerClient.getCustomerById(request.customerId()).customerId());
        logger.info("::: Buscando cliente no microserviço de Clientes com id {} ::: ",request.customerId());
        if (isNull(customerId)) {
            throw new IllegalArgumentException("O cliente informado não foi encontrado!");
        }

        // Verifica a disponibilidade do quarto
        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Quarto não encontrado"));

        Hotel hotel = hotelRepository.findById(request.hotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel Não encontrado!"));

        // Verifica a disponibilidade do quarto para as datas solicitadas
        boolean isAvailable = room.getAvailability().stream()
                .anyMatch(a -> a.getDate().equals(request.checkInDate()) && a.getAvailable());
        if (!isAvailable) {
            throw new IllegalStateException("Quarto não disponível para as datas solicitadas");
        }

        // Cria a reserva
        Reservation reservation = new Reservation();
        reservation.setHotel(hotel);
        reservation.setRoom(room);
        reservation.setCheckInDate(request.checkInDate());
        reservation.setCheckOutDate(request.checkOutDate());

        //Chama o simulador de gateway de pagamentos
        reservation.setPaymentStatus(paymentSimulationService.processPaymentSimulation(request.paymentDetails()).status());

        //Status da Reserva
        reservation.setReservationStatus(reservation.getPaymentStatus().equals("APPROVED") ? "CONFIRMED" : "CANCELED" );

        //Valida o idCustomer no Microserviço de clientes
        reservation.setGuestId(customerId);
        reservation.setCustomerId(request.customerId());

        //Preço da reserva
        reservation.setTotalPrice(reservation.calculateTotalPrice(
                room.getPricePerNight(), getDaysBetween(request.checkInDate(), request.checkOutDate())));

        reservation.setPaymentDetails(request.paymentDetails().toString());

        // Salva a reserva
        Reservation savedReservation = repository.save(reservation);

        roomRepository.save(room);
        logger.info("::: Finalizando reserva do quarto com id {} ",room.getId());

        // Envia os dados para a fila notificações
        logger.info("::: Enviando dados para notificação da reserve para o Broker, reserva com id  {} ::: ", reservation.getId());
        reservationConfirmationProducer.sendConfirmation(toReservationEvent(reservation));

        // Retorna o DTO de confirmação
        return new ReservationConfirmationDto(
                savedReservation.getId(),
                "Confirmada",
                "Sua reserva foi confirmada com sucesso!"
        );
    }

    @Override
    public List<ReservationResponseDto> getAllReservations() {
        logger.info("::: Buscando todas as reservas :::");
        var lista = repository.findAll().stream()
                .map(r -> modelMapper.map(r, ReservationResponseDto.class))
                .collect(Collectors.toList());
        if(lista.isEmpty()){
            throw new IllegalArgumentException("Nenhuma reserva ainda foi cadastrada");
        }
        return lista;
    }
}
