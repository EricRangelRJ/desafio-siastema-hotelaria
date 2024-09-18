package br.com.ntconsult.desafio.review_management.repository;

import br.com.ntconsult.desafio.review_management.models.entity.HotelCacheEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelCacheRepository extends MongoRepository<HotelCacheEntity, String> {
}
