package br.com.ntconsult.desafio.hotel_management.services;

import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationConfirmationDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationResponseDto;

import java.util.List;

public interface ReservationService {
    ReservationConfirmationDto reserveRoom(ReservationRequestDto request);

    public List<ReservationResponseDto> getAllReservations();

}
