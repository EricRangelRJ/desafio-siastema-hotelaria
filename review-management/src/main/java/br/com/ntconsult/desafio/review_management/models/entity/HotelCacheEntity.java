package br.com.ntconsult.desafio.review_management.models.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotel_cache")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelCacheEntity {

    @Id
    private String hotelId;
    private String name;
    private String eventType;

}
