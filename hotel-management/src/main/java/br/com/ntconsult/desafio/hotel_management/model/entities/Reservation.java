package br.com.ntconsult.desafio.hotel_management.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonManagedReference
    private Room room;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonManagedReference
    private Hotel hotel;

    @Column(name = "guest_id", nullable = false)
    private UUID guestId;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "reservation_status", nullable = false)
    private String reservationStatus;

    @Column(name = "payment_details")
    private String paymentDetails;

    @Column(name = "customer_id")
    private UUID customerId;

    public BigDecimal calculateTotalPrice(BigDecimal pricePerNight, Integer numberOfNights) {
        return pricePerNight.multiply(BigDecimal.valueOf(numberOfNights));
    }


}
