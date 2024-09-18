package br.com.ntconsult.desafio.review_management.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotelEvent {
    private String hotelId;
    private String hotelName;
    private String eventType;

    public HotelEvent() {
    }

    public HotelEvent(String hotelId, String hotelName, String eventType) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.eventType = eventType;
    }
}



