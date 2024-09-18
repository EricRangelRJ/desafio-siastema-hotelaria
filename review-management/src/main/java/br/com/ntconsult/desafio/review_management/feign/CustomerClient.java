package br.com.ntconsult.desafio.review_management.feign;

import br.com.ntconsult.desafio.review_management.models.dtos.feign.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "${customer.service.name}",
        url = "${customer.service.url}")
public interface CustomerClient {

    @GetMapping("/{customerId}")
    CustomerResponseDto getCustomerById(@PathVariable("customerId") UUID customerId);
}



