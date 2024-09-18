package br.com.ntconsult.desafio.review_management.models.dtos;

public record ReviewRequestDto(
        String hotelId,
        String userId,
        String comment,
        int rating
) {}