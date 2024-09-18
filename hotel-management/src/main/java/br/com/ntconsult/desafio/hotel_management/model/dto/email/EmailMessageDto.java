package br.com.ntconsult.desafio.hotel_management.model.dto.email;

public record EmailMessageDto(
        String to,
        String subject,
        String body
) {}
