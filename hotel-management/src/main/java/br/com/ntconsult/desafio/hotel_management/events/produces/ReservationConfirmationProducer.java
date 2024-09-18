package br.com.ntconsult.desafio.hotel_management.events.produces;

import br.com.ntconsult.desafio.hotel_management.events.ReservationEvent;
import br.com.ntconsult.desafio.hotel_management.model.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationConfirmationProducer {

    private static final String TOPIC = "notification.email.sent"; // Nome do tópico Kafka
    private final KafkaTemplate<String, ReservationEvent> kafkaTemplate;

    @Autowired
    public ReservationConfirmationProducer(KafkaTemplate<String, ReservationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendConfirmation(ReservationEvent reservation) {
        kafkaTemplate.send(TOPIC, reservation);
    }
}
