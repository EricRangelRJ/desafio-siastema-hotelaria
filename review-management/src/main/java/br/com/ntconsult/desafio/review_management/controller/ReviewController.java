package br.com.ntconsult.desafio.review_management.controller;

import br.com.ntconsult.desafio.review_management.models.dtos.ReviewRequestDto;
import br.com.ntconsult.desafio.review_management.models.entity.ReviewEntity;
import br.com.ntconsult.desafio.review_management.services.impl.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewEntity> submitReview(@RequestBody ReviewRequestDto reviewDto) {
        ReviewEntity savedReview = reviewService.saveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedReview);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<ReviewEntity>> findReviewsByIdHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(reviewService.getReviewsByHotelId(hotelId));
    }

    @GetMapping()
    public ResponseEntity<List<ReviewEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(reviewService.findAll());
    }
}

