package br.com.ntconsult.desafio.hotel_management.model.dto.room;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record RoomAvailabilityResponseDto(

        LocalDate dateAvailability,

        Boolean isAvailability
) {}
