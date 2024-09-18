package br.com.ntconsult.desafio.hotel_management.model.dto.customer;

import java.util.UUID;

public record CustomerResponse(UUID id, String name, String email, String phone) {}
