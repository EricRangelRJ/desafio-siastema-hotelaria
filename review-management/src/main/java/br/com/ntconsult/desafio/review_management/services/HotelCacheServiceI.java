package br.com.ntconsult.desafio.review_management.services;

import br.com.ntconsult.desafio.review_management.event.HotelEvent;
import br.com.ntconsult.desafio.review_management.models.entity.HotelCacheEntity;

import java.util.Optional;

public interface HotelCacheServiceI {
    public HotelCacheEntity save(HotelEvent event);

    public Optional<HotelCacheEntity> findById(String id);

}
