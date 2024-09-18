package br.com.ntconsult.desafio.hotel_management.services.impl;

import br.com.ntconsult.desafio.hotel_management.enumerated.EventTypeEnum;
import br.com.ntconsult.desafio.hotel_management.events.HotelEvent;
import br.com.ntconsult.desafio.hotel_management.events.produces.HotelStatusUpdateProducer;
import br.com.ntconsult.desafio.hotel_management.feign.CustomerClient;
import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelResponseDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomResponseDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import br.com.ntconsult.desafio.hotel_management.model.entities.RoomAvailability;
import br.com.ntconsult.desafio.hotel_management.services.HotelService;
import br.com.ntconsult.desafio.hotel_management.repositories.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDate.parse;
import static java.util.Objects.nonNull;

@Service
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    private final HotelRepository hotelRepository;

    private final HotelStatusUpdateProducer hotelEventProducer;

    private final CustomerClient customerClient;


    public HotelServiceImpl(HotelRepository hotelRepository, HotelStatusUpdateProducer hotelEventProducer, CustomerClient customerClient) {
        this.hotelRepository = hotelRepository;
        this.hotelEventProducer = hotelEventProducer;
        this.customerClient = customerClient;
    }

    public List<HotelResponseDto> findAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(Hotel::toDto)
                .collect(Collectors.toList());
    }

    public Hotel addHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelRequestDto.name());
        hotel.setLocation(hotelRequestDto.location());
        hotel.setDescription(hotelRequestDto.description());
        hotel.setAmenities(hotelRequestDto.amenities());
        hotel.setRating(hotelRequestDto.rating());
        Hotel result = hotelRepository.save(hotel);

        // Enviando pro Kafka dados do hotel criado para consumo pelo MS de avaliação
        hotelEventProducer.sendHotelEvent(
                HotelEvent.builder()
                        .hotelId(result.getId().toString())
                        .hotelName(result.getName())
                        .eventType(EventTypeEnum.CREATE.toString())
                        .build());
        logger.info("::: Enviado dados do hotel cadastrado para a fila :::");
        return result;
    }

    @Override
    public List<HotelResponseDto> compareHotels(String location, BigDecimal maxPrice, Double minRating, String amenities) {
        String locationPattern = (nonNull(location)) ? "%" + location.toLowerCase() + "%" : "%%";
        String amenitiesPattern = (nonNull(amenities)) ? "%" + amenities.toLowerCase() + "%" : "%%";
        List<Hotel> hotels = hotelRepository.findHotelsForComparison(locationPattern, minRating, amenitiesPattern, maxPrice);
        verificarRetornoConsulta(hotels);
        logger.info("::: Comparando Hoteis :::");
        return hotels.stream()
                .map(Hotel::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HotelResponseDto> searchHotels(String location, String checkIn, String checkOut, Integer rooms, Integer guests) {
        String locationPattern = nonNull(location) ? "%" + location.toLowerCase() + "%" : "%%";
        List<Hotel> hotels = hotelRepository.findHotels(locationPattern, parse(checkIn), parse(checkOut), rooms, guests);
        verificarRetornoConsulta(hotels);
        logger.info("::: Buscando Hoteis :::");
        return hotels.stream()
                .map(Hotel::toDto)
                .collect(Collectors.toList());

    }

    public boolean hasAvailableRooms(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, int requiredRooms) {
        return hotel.getRooms().stream()
                .filter(room -> isRoomAvailable(room, checkInDate, checkOutDate))
                .count() >= requiredRooms;
    }

    private boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        return room.getAvailability().stream()
                .filter(availability -> !availability.getDate().isBefore(checkInDate) && !availability.getDate().isAfter(checkOutDate))
                .allMatch(RoomAvailability::getAvailable);
    }

    private int compareHotelsBy(Hotel h1, Hotel h2, String sortBy, String sortOrder) {
        int result = 0;
        if ("price".equals(sortBy)) {
            BigDecimal price1 = h1.getRooms().stream().map(Room::getPricePerNight).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
            BigDecimal price2 = h2.getRooms().stream().map(Room::getPricePerNight).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
            result = price1.compareTo(price2);
        } else if ("rating".equals(sortBy)) {
            result = Double.compare(h1.getRating(), h2.getRating());
        }
        return "desc".equalsIgnoreCase(sortOrder) ? -result : result;
    }

    public HotelResponseDto findById(UUID id) {
        Optional<Hotel> result = hotelRepository.findById(id);
        if (result.isPresent()) {
            Hotel hotel = result.get();
            return HotelResponseDto.builder()
                    .id(hotel.getId())
                    .name(hotel.getName())
                    .location(hotel.getLocation())
                    .rating(hotel.getRating())
                    .description(hotel.getDescription())
                    .amenities(hotel.getAmenities())
                    .rooms(getRooms(hotel.getRooms()))
                    .build();
        }
        return null;
    }

    private List<RoomResponseDto> getRooms(List<Room> rooms) {
        return rooms.stream()
                .map(room -> RoomResponseDto.builder()
                        .id(room.getId())
                        .type(room.getType())
                        .pricePerNight(room.getPricePerNight())
                        .amenities(room.getAmenities())
                        .numberOfGuests(room.getNumberOfGuests())
                        .roomAvailability(room.getAvailability() != null ? room.getAvailability().stream()
                                .map(RoomAvailability::toDto)
                                .collect(Collectors.toList()) : null)
                        .build())
                .collect(Collectors.toList());
    }

    private static void verificarRetornoConsulta(List<Hotel> hotels) {
        if(hotels.isEmpty()){
            throw new IllegalArgumentException("Não foram encontrados hotéis com os parâmetros informados," +
                    " faça uma busca pelo findAll para obter alguns parâmetros para a consulta.");
        }
    }

}

