package br.com.ntconsult.desafio.hotel_management.services.impl;

import br.com.ntconsult.desafio.hotel_management.model.dto.payment.PaymentDetailsDto;
import br.com.ntconsult.desafio.hotel_management.model.dto.payment.PaymentResponseDTO;
import br.com.ntconsult.desafio.hotel_management.services.PaymentSimulationService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentSimulationServiceImpl implements PaymentSimulationService {

    @Override
    public PaymentResponseDTO processPaymentSimulation(PaymentDetailsDto paymentDetailsDto) {

        final Random random = new Random();

        boolean isApproved = random.nextBoolean();

        if (isApproved) {
            return new PaymentResponseDTO(
                    UUID.randomUUID().toString(),  // ID da transação gerado
                    "APPROVED",
                    "Payment approved successfully."
            );
        } else {
            return new PaymentResponseDTO(
                    UUID.randomUUID().toString(),
                    "DECLINED",
                    "Payment was declined by the gateway."
            );
        }

    }

}
