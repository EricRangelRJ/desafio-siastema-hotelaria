package br.com.ntconsult.desafio.review_management.models.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "review")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {
    @Id
    private String id;
    private String hotelId;
    private String userId;
    private String comment;
    private int rating;
    private LocalDateTime reviewDate;
}
