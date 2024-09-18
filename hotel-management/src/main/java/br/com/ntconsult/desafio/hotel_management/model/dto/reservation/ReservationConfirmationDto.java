package br.com.ntconsult.desafio.hotel_management.model.dto.reservation;

import java.util.UUID;

public record ReservationConfirmationDto(
        UUID reservationId,
        String status,
        String confirmationMessage
) {}
