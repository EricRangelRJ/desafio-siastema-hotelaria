package br.com.ntconsult.desafio.review_management.models.dtos.feign;


import lombok.Builder;

@Builder
public record CustomerResponseDto(String customerId, String name, String email, String phone) {
}