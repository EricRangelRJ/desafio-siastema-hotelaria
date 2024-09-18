package br.com.ntconsult.desafio.hotel_management.repositories;

import br.com.ntconsult.desafio.hotel_management.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}

