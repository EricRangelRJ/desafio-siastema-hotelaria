package br.com.ntconsult.desafio.hotel_management.services.impl;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import br.com.ntconsult.desafio.hotel_management.model.entities.RoomAvailability;
import br.com.ntconsult.desafio.hotel_management.repositories.HotelRepository;
import br.com.ntconsult.desafio.hotel_management.repositories.RoomRepository;
import br.com.ntconsult.desafio.hotel_management.services.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public Room addRoom(RoomRequestDto roomRequestDto, UUID hotelId) {
        // Verifica se o hotel existe
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Cria e configura o quarto
        Room room = new Room();
        room.setType(roomRequestDto.type());
        room.setPricePerNight(roomRequestDto.pricePerNight());
        room.setNumberOfGuests(roomRequestDto.numberOfGuests());
        room.setAmenities(roomRequestDto.amenities());

        // Verifica se availabilityRequestDto é nulo e cria uma lista de RoomAvailability
        List<RoomAvailability> availabilityList = new ArrayList<>();
        if (roomRequestDto.roomAvailabilityRequestDtoList() != null) {
            availabilityList = roomRequestDto.roomAvailabilityRequestDtoList().stream()
                    .map(dto -> {
                        RoomAvailability availability = new RoomAvailability();
                        availability.setDate(dto.date());
                        availability.setAvailable(dto.available());
                        availability.setRoom(room);
                        return availability;
                    })
                    .collect(Collectors.toList());
        }


        room.setAvailability(availabilityList);
        room.setHotel(hotel);

        // Salva o quarto no banco de dados
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public Room updateRoom(UUID roomId, RoomRequestDto roomRequestDto) {
        // Busca o quarto existente
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Atualiza os campos do quarto
        existingRoom.setType(roomRequestDto.type());
        existingRoom.setPricePerNight(roomRequestDto.pricePerNight());

        // Salva as mudanças no banco de dados
        return roomRepository.save(existingRoom);
    }
}
