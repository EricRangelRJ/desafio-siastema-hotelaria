package br.com.ntconsult.desafio.hotel_management.model.entities;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomAvailabilityResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate date;

    private Boolean available;

    public static RoomAvailabilityResponseDto toDto(RoomAvailability roomAvailability) {
        return RoomAvailabilityResponseDto.builder()
                .isAvailability(roomAvailability.available)
                .dateAvailability(roomAvailability.date)
                .build();
    }

}
