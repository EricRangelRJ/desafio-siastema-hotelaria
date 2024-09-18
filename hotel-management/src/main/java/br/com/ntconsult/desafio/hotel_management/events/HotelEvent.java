package br.com.ntconsult.desafio.hotel_management.events;

import br.com.ntconsult.desafio.hotel_management.enumerated.EventTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HotelEvent {

    private String hotelId;
    private String eventType;
    private String hotelName;

}

