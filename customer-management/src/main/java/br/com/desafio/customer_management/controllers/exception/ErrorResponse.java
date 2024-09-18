package br.com.desafio.customer_management.controllers.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String detail;
    private String message;
}
