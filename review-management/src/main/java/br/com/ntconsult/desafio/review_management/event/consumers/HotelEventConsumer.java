package br.com.ntconsult.desafio.review_management.event.consumers;

import br.com.ntconsult.desafio.review_management.event.HotelEvent;
import br.com.ntconsult.desafio.review_management.services.impl.HotelCacheService;
import br.com.ntconsult.desafio.review_management.services.impl.ReviewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HotelEventConsumer {

    private ReviewService reviewService;

    private HotelCacheService hotelCacheService;

    public HotelEventConsumer(ReviewService reviewService, HotelCacheService hotelCacheService) {
        this.reviewService = reviewService;
        this.hotelCacheService = hotelCacheService;
    }

    @Value("${kafka.listener.topic}")
    private String topic;

    @Value("${kafka.groupId}")
    private String groupId;

    @KafkaListener(topics = "${kafka.listener.topic}", groupId = "${kafka.groupId}")
    public void consumeHotelEvent(HotelEvent event) {
             hotelCacheService.save(event);
        }
}
