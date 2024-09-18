package br.com.ntconsult.desafio.review_management.services;

import br.com.ntconsult.desafio.review_management.models.dtos.ReviewRequestDto;
import br.com.ntconsult.desafio.review_management.models.entity.ReviewEntity;

import java.util.List;
import java.util.UUID;

public interface ReviewServiceI {

    public ReviewEntity saveReview(ReviewRequestDto reviewDto);

    public List<ReviewEntity> getReviewsByHotelId(UUID hotelId);

}
