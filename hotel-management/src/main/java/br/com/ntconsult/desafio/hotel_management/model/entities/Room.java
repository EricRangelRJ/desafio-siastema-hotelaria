package br.com.ntconsult.desafio.hotel_management.model.entities;

import br.com.ntconsult.desafio.hotel_management.model.dto.room.RoomResponseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "amenities")
    private String amenities;

    @Column(name = "number_of_guests")
    private Integer numberOfGuests;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<RoomAvailability> availability;

    public static RoomResponseDto toDto(Room room) {
        return RoomResponseDto.builder()
                .id(room.getId())
                .type(room.getType())
                .pricePerNight(room.getPricePerNight())
                .amenities(room.getAmenities())
                .numberOfGuests(room.getNumberOfGuests())
                .roomAvailability(room.getAvailability() != null ? room.getAvailability().stream()
                        .map(RoomAvailability::toDto)
                        .collect(Collectors.toList()) : null)
                .build();
    }


}

