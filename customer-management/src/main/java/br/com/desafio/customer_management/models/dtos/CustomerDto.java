package br.com.desafio.customer_management.models.dtos;

import br.com.desafio.customer_management.models.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CustomerDto(

        UUID idCustomer,
        String name,
        @Email(message = "Deve ser um email válido.") String email,
        @NotNull(message = "Digite seu número de telefone.")
        @NotEmpty(message = "Digite seu número de telefone.")
        String phone
) {

    public Customer toEntity() {
        return Customer.builder()
                .customerId(UUID.randomUUID().toString())
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .build();
    }
    public Customer toEntity(String idCustomer) {
        return Customer.builder()
                .customerId(idCustomer)
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .build();
    }

}
