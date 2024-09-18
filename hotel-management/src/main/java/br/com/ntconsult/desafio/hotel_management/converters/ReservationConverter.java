package br.com.ntconsult.desafio.hotel_management.converters;

import br.com.ntconsult.desafio.hotel_management.events.ReservationEvent;
import br.com.ntconsult.desafio.hotel_management.model.entities.Reservation;

public class ReservationConverter {

    public static ReservationEvent toReservationEvent(Reservation reservation) {
        return new ReservationEvent(
                reservation.getId(),
                reservation.getCustomerId(),
                reservation.getHotel().getName(),
                reservation.getRoom().getAmenities(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getPaymentStatus(),
                reservation.getTotalPrice()
        );
    }
}

