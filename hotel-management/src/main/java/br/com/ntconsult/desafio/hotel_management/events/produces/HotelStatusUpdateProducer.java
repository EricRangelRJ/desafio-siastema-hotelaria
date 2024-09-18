package br.com.ntconsult.desafio.hotel_management.events.produces;

import br.com.ntconsult.desafio.hotel_management.events.HotelEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HotelStatusUpdateProducer {

    private static final String TOPIC = "hotel.room.status.update";

    private final KafkaTemplate<String, HotelEvent> kafkaTemplate;

    public HotelStatusUpdateProducer(KafkaTemplate<String, HotelEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendHotelEvent(HotelEvent hotelEvent) {
        kafkaTemplate.send(TOPIC, hotelEvent);
    }
}
