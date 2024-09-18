package br.com.ntconsult.desafio.review_management.services.impl;

import br.com.ntconsult.desafio.review_management.event.HotelEvent;
import br.com.ntconsult.desafio.review_management.models.entity.HotelCacheEntity;
import br.com.ntconsult.desafio.review_management.repository.HotelCacheRepository;
import br.com.ntconsult.desafio.review_management.services.HotelCacheServiceI;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelCacheService implements HotelCacheServiceI {

    private HotelCacheRepository repository;

    public HotelCacheService(HotelCacheRepository repository) {
        this.repository = repository;
    }

    @Override
    public HotelCacheEntity save(HotelEvent event) {
        return repository.save(
                new HotelCacheEntity(event.getHotelId(), event.getHotelName(), event.getEventType()));
    }

    @Override
    public Optional<HotelCacheEntity> findById(String id){
        return repository.findById(id);
    }

}
