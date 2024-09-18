package br.com.ntconsult.desafio.hotel_management.feign;

import br.com.ntconsult.desafio.hotel_management.model.dto.feign.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8082/customer-management/api/customer")
public interface CustomerClient {

    @GetMapping("/{customerId}")
    CustomerResponseDto getCustomerById(@PathVariable("customerId") UUID customerId);

}



