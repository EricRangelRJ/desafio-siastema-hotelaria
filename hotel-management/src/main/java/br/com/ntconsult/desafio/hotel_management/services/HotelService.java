package br.com.ntconsult.desafio.hotel_management.services;

import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelResponseDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface HotelService {

    List<HotelResponseDto> findAll();

    HotelResponseDto findById(UUID id);

    Hotel addHotel(HotelRequestDto hotelRequestDto);

    List<HotelResponseDto> compareHotels(String location, BigDecimal maxPrice, Double minRating, String amenities);

    List<HotelResponseDto> searchHotels(String location, String checkIn, String checkOut, Integer rooms, Integer guests);

    boolean hasAvailableRooms(Hotel hotelId, LocalDate checkInDate, LocalDate checkOutDate, int requiredRooms);

}
