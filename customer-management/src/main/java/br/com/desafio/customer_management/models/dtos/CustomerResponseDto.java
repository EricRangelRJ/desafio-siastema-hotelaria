package br.com.desafio.customer_management.models.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponseDto(String customerId, String name, String email, String phone) {
}
