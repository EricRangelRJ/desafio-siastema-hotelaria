package br.com.ntconsult.desafio.hotel_management.repositories;

import br.com.ntconsult.desafio.hotel_management.model.entities.Hotel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    @Query("SELECT h FROM Hotel h " +
            "JOIN h.rooms r " +
            "JOIN r.availability a " +
            "WHERE LOWER(h.location) LIKE LOWER(:location) " +
            "AND a.date BETWEEN :checkInDate AND :checkOutDate " +
            "AND r.numberOfGuests >= :numberOfGuests " +
            "AND a.available = true " +
            "GROUP BY h " +
            "HAVING COUNT(DISTINCT r) >= :numberOfRooms")
    List<Hotel> findHotels(
            @Param("location") String location,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("numberOfGuests") Integer numberOfGuests,
            @Param("numberOfRooms") Integer numberOfRooms);


    @Query("SELECT h FROM Hotel h " +
            "WHERE (:location IS NULL OR LOWER(h.location) LIKE LOWER(:location)) " +
            "  AND (:rating IS NULL OR h.rating >= :rating) " +
            "  AND (:amenities IS NULL OR LOWER(h.amenities) LIKE LOWER(:amenities)) " +
            "  AND (:maxPrice IS NULL OR EXISTS (SELECT r FROM h.rooms r WHERE r.pricePerNight <= :maxPrice))")
    List<Hotel> findHotelsForComparison(@Param("location") String location,
                                        @Param("rating") Double minRating,
                                        @Param("amenities") String amenities,
                                        @Param("maxPrice") BigDecimal maxPrice);


}


