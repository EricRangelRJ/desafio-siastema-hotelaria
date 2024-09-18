package br.com.ntconsult.desafio.review_management.repository;

import br.com.ntconsult.desafio.review_management.models.entity.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<ReviewEntity, UUID> {

    List<ReviewEntity> findByHotelId(String hotelId);

}
