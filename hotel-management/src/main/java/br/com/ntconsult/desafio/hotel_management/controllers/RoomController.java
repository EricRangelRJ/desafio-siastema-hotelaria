package br.com.ntconsult.desafio.hotel_management.controllers;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomRequestDto;
import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import br.com.ntconsult.desafio.hotel_management.services.impl.RoomServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomServiceImpl roomService;

    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(@RequestBody RoomRequestDto roomRequestDto, @RequestParam UUID hotelId) {
        return roomService.addRoom(roomRequestDto, hotelId);
    }

    @PutMapping("/{roomId}")
    public Room updateRoom(@PathVariable UUID roomId, @RequestBody RoomRequestDto roomRequestDto) {
        return roomService.updateRoom(roomId, roomRequestDto);
    }

}

