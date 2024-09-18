package br.com.ntconsult.desafio.hotel_management.model.dto.reservation;

import br.com.ntconsult.desafio.hotel_management.model.dto.payment.PaymentDetailsDto;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationRequestDto(
        UUID hotelId,
        UUID roomId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        UUID customerId,
        PaymentDetailsDto paymentDetails
) {}


