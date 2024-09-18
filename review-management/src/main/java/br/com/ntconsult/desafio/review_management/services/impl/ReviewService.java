package br.com.ntconsult.desafio.review_management.services.impl;

import br.com.ntconsult.desafio.review_management.feign.CustomerClient;
import br.com.ntconsult.desafio.review_management.models.dtos.ReviewRequestDto;
import br.com.ntconsult.desafio.review_management.models.entity.ReviewEntity;
import br.com.ntconsult.desafio.review_management.repository.ReviewRepository;
import br.com.ntconsult.desafio.review_management.services.ReviewServiceI;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class ReviewService implements ReviewServiceI {

    private ReviewRepository reviewRepository;

    private HotelCacheService hotelCacheService;

    private CustomerClient customerClient;

    public ReviewService(ReviewRepository reviewRepository, HotelCacheService hotelCacheService, CustomerClient customerClient) {
        this.reviewRepository = reviewRepository;
        this.hotelCacheService = hotelCacheService;
        this.customerClient = customerClient;
    }

    @Override
    public ReviewEntity saveReview(ReviewRequestDto reviewDto) {

        var result = hotelCacheService.findById(reviewDto.hotelId());

        if (result.isEmpty()) {
            throw new IllegalArgumentException("O identicador do Hotel informado não foi encontrado!");
        }

        var idClient = customerClient.getCustomerById(UUID.fromString(reviewDto.userId()));
        if (isNull(idClient)) {
            throw new IllegalArgumentException("O cliente informado não foi encontrado!");
        }

        // Cria e salva a avaliação se o hotel existir
        ReviewEntity review = new ReviewEntity();
        review.setId(UUID.randomUUID().toString());
        review.setHotelId(reviewDto.hotelId());
        review.setUserId(reviewDto.userId());
        review.setComment(reviewDto.comment());
        review.setRating(reviewDto.rating());
        review.setReviewDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<ReviewEntity> getReviewsByHotelId(UUID hotelId) {
        return reviewRepository.findByHotelId(hotelId.toString());
    }

    public List<ReviewEntity> findAll() {
        return reviewRepository.findAll();
    }

}