package br.com.ntconsult.desafio.hotel_management.services;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;

import java.util.UUID;

public interface RoomService {

    Room addRoom(RoomRequestDto roomRequestDto, UUID hotelId);

    Room updateRoom(UUID roomId, RoomRequestDto roomRequestDto);

}
