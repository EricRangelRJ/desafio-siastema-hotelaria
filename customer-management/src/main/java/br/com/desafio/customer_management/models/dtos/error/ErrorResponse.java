package br.com.desafio.customer_management.models.dtos.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String detail;
    private String message;
}
