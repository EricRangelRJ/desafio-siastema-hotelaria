package br.com.ntconsult.desafio.hotel_management.services;

import br.com.ntconsult.desafio.hotel_management.model.dto.payment.PaymentDetailsDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.payment.PaymentResponseDTO;

public interface PaymentSimulationService {
    PaymentResponseDTO processPaymentSimulation(PaymentDetailsDto paymentDetailsDto);

}
