package br.com.ntconsult.desafio.review_management.services;

import br.com.ntconsult.desafio.review_management.models.dtos.ReviewRequestDto;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
public class ReviewServiceTest {

    //TODO - Ajustar TUs

//    @Autowired
//    private ReviewService reviewService;
//
//    @MockBean
//    private ReviewRepository reviewRepository;
//
//    @Test
//    public void testSaveReview() {
//        ReviewRequestDto reviewDto = new ReviewRequestDto("hotel123", "user123", "Great stay!", 5);
//        Review review = new Review();
//        review.setId(1L);
//        review.setHotelId("hotel123");
//        review.setUserId("user123");
//        review.setComment("Great stay!");
//        review.setRating(5);
//
//        Mockito.when(reviewRepository.save(any(Review.class))).thenReturn(review);
//
//        Review savedReview = reviewService.saveReview(reviewDto);
//        assertEquals(5, savedReview.getRating());
//    }
}
