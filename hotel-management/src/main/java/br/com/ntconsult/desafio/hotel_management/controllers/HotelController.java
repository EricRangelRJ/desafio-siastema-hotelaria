package br.com.ntconsult.desafio.hotel_management.controllers;

import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelResponseDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import br.com.ntconsult.desafio.hotel_management.services.impl.HotelServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    private final HotelServiceImpl service;

    public HotelController(HotelServiceImpl hotelService) {
        this.service = hotelService;
    }

    @PostMapping
    public Hotel addHotel(@RequestBody HotelRequestDto hotelRequestDto) {
        logger.info("::: Adicionando novo hotel :::");
        return service.addHotel(hotelRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponseDto>> findAll() {
        logger.info("::: Buscando todos os hoteis cadastrados :::");
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDto> findById(@PathVariable UUID id) {
        logger.info("::: Buscando hotel por id {} :::S ", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponseDto>> searchHotels(
            @RequestParam String destination,
            @RequestParam String checkInDate,
            @RequestParam String checkOutDate,
            @RequestParam Integer rooms,
            @RequestParam Integer guests
    ) {
        logger.info("::: Pesquisando lista de hoteis :::");
        return ResponseEntity.ok(service.searchHotels(destination, checkInDate, checkOutDate, rooms, guests));
    }

    @GetMapping("/compare")
    public ResponseEntity<List<HotelResponseDto>> compareHotels(
            @RequestParam String location,
            @RequestParam BigDecimal maxPrice,
            @RequestParam Double minRating,
            @RequestParam String amenities) {

        logger.info("::: Comparando hoteis :::");
        List<HotelResponseDto> comparisonResult = service.compareHotels(location, maxPrice, minRating, amenities);
        return ResponseEntity.ok(comparisonResult);
    }
}

