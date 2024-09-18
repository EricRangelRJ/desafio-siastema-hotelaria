package br.com.ntconsult.desafio.hotel_management.model.dto.room;

import br.com.ntconsult.desafio.hotel_management.model.entities.Room;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record RoomRequestDto(
        String type,
        BigDecimal pricePerNight,

        String amenities,

        Integer numberOfGuests,

        List<RoomAvailabilityRequestDto> roomAvailabilityRequestDtoList

) {
    public RoomRequestDto {
        Objects.requireNonNull(type, "Type must not be null");
        Objects.requireNonNull(pricePerNight, "Price per night must not be null");
        if (pricePerNight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price per night must not be negative");
        }
    }

    public static Room toEntity(RoomRequestDto dto) {
        return Room.builder()
                .amenities(dto.amenities)
                .type(dto.type())
                .pricePerNight(dto.pricePerNight)
                .build();
    }
}
