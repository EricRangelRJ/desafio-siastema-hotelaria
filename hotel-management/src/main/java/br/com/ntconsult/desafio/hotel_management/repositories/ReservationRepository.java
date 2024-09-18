package br.com.ntconsult.desafio.hotel_management.repositories;

import br.com.ntconsult.desafio.hotel_management.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}

