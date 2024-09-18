package br.com.ntconsult.desafio.hotel_management.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReservationEvent(
        UUID id,
        UUID idCustomer,
        String nameHotel,
        String amenitiesRoom,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        String paymentStatus,
        BigDecimal totalPrice
        ) {}
