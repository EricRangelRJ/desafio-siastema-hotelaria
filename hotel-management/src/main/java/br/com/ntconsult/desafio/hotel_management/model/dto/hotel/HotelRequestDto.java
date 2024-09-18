package br.com.ntconsult.desafio.hotel_management.model.dto.hotel;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;

import java.util.List;
import java.util.UUID;

public record HotelRequestDto(

        UUID id,
        String name,
        String location,
        String description,
        String amenities,
        Double rating,
        List<RoomRequestDto> rooms

) {

    public Hotel toEntity(HotelRequestDto dto){
        return Hotel.builder()
                .id(null)
                .amenities(dto.amenities)
                .description(dto.description)
                .location(dto.location)
                .build();
    }

}
