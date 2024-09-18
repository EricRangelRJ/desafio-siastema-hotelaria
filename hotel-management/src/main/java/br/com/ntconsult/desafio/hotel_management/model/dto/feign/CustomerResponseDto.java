package br.com.ntconsult.desafio.hotel_management.model.dto.feign;


import lombok.Builder;
@Builder
public record CustomerResponseDto(String customerId, String name, String email, String phone) {
}