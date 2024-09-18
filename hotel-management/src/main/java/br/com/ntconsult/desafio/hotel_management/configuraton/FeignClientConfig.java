package br.com.ntconsult.desafio.hotel_management.configuraton;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        // Define o nível de log do FeignClient. Pode ser FULL, BASIC, HEADERS ou NONE.
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options requestOptions() {
        // Define o timeout para as requisições Feign
        return new Request.Options(5000, 10000); // 5 segundos de connect timeout e 10 segundos de read timeout
    }
}
