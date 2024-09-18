package br.com.ntconsult.desafio.hotel_management.model.dto.reservation;

import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponseDto {

    private UUID id;
    private Room room;
    private Hotel hotel;
    private UUID guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private String paymentStatus;
    private String reservationStatus;
    private String paymentDetails;
    private UUID customerId;

}