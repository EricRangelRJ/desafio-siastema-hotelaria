package br.com.ntconsult.desafio.hotel_management.model.dto.hotel;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomResponseDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import lombok.Builder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record HotelResponseDto(
        UUID id,
        String name,
        String location,
        double rating,
        String description,
        String amenities,
        List<RoomResponseDto> rooms

) {
    public static Hotel toEntity(HotelResponseDto dto) {
        Hotel hotel = new Hotel();
        hotel.setId(dto.id());
        hotel.setName(dto.name());
        hotel.setLocation(dto.location());
        hotel.setRating(dto.rating());
        hotel.setDescription(dto.description());
        hotel.setAmenities(dto.amenities());

        // Mapeando a lista de RoomResponseDto para a lista de Room
        if (dto.rooms() != null) {
            List<Room> rooms = dto.rooms().stream()
                    .map(RoomResponseDto::toEntity)
                    .collect(Collectors.toList());
            hotel.setRooms(rooms);
        }

        return hotel;

    }

}
