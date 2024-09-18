package br.com.ntconsult.desafio.hotel_management.model.dto.payment;

public record PaymentDetailsDto(
        String cardNumber,
        String cardHolderName,
        String expirationDate,
        String cvv
) {}