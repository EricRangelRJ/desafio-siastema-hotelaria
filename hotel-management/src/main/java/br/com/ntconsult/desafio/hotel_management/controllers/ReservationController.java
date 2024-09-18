package br.com.ntconsult.desafio.hotel_management.controllers;

import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationConfirmationDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.reservation.ReservationResponseDto;
import br.com.ntconsult.desafio.hotel_management.services.impl.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationServiceImpl service;

    @PostMapping
    public ResponseEntity<ReservationConfirmationDto> reserveRoom(@RequestBody ReservationRequestDto reservationRequest) {
        logger.info("::: Criando reserva de quarto com id {} ::: ",reservationRequest.roomId());
        return ResponseEntity.ok(service.reserveRoom(reservationRequest));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        logger.info("::: Listando todas as reservas cadastradas :::");
        return ResponseEntity.ok(service.getAllReservations());
    }
}

