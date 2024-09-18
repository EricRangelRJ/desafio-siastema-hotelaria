package br.com.ntconsult.desafio.hotel_management.model.dto.room;

import java.time.LocalDate;

public record RoomAvailabilityRequestDto(
        LocalDate date,
        Boolean available
) {
}