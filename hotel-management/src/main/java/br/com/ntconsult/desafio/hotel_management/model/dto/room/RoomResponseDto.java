package br.com.ntconsult.desafio.hotel_management.model.dto.room;

import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record RoomResponseDto(

        UUID id,
        String type,
        BigDecimal pricePerNight,

        String amenities,

        Integer numberOfGuests,

        List<RoomAvailabilityResponseDto> roomAvailability

) {

    public static Room toEntity(RoomResponseDto dto) {
        Room room = new Room();
        room.setId(dto.id());
        room.setType(dto.type());
        room.setPricePerNight(dto.pricePerNight());
        return room;
    }

}
