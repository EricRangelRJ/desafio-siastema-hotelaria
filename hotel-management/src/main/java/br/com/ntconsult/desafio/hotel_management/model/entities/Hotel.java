package br.com.ntconsult.desafio.hotel_management.model.entities;

import br.com.ntconsult.desafio.hotel_management.model.dto.hotel.HotelResponseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String location;
    private double rating;
    private String description;
    private String amenities;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Room> rooms;

    public HotelResponseDto toDto() {
        return HotelResponseDto.builder()
                .id(this.getId())
                .name(this.getName())
                .location(this.getLocation())
                .rating(this.getRating())
                .description(this.getDescription())
                .amenities(this.getAmenities())
                .rooms(this.getRooms() != null ? this.getRooms().stream()
                        .map(Room::toDto)
                        .collect(Collectors.toList()) : null)
                .build();
    }


}