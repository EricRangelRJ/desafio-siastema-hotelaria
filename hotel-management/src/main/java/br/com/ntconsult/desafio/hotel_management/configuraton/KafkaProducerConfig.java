package br.com.ntconsult.desafio.hotel_management.configuraton;

import br.com.ntconsult.desafio.hotel_management.events.HotelEvent;
import br.com.ntconsult.desafio.hotel_management.events.ReservationEvent;
import br.com.ntconsult.desafio.hotel_management.model.entities.Reservation;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaAddress;

    @Bean
    public ProducerFactory<String, HotelEvent> hotelEventProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, ReservationEvent> reservationEventProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public NewTopic reservationEventTopic() {
        return new NewTopic("notification.email.sent", 1, (short) 1);
    }

    @Bean
    public NewTopic anotherTopic() {
        return new NewTopic("hotel.room.status.update", 1, (short) 1);
    }

    @Bean
    public KafkaTemplate<String, HotelEvent> hotelEventKafkaTemplate() {
        return new KafkaTemplate<>(hotelEventProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, ReservationEvent> reservationEventKafkaTemplate() {
        return new KafkaTemplate<>(reservationEventProducerFactory());
    }
}
