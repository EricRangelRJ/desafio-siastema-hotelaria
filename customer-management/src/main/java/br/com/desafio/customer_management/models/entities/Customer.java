package br.com.desafio.customer_management.models.entities;

import br.com.desafio.customer_management.models.dtos.CustomerResponseDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String customerId;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String phone;

    public CustomerResponseDto toDto() {
        return CustomerResponseDto.builder()
                .customerId(this.customerId)
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .build();
    }

    public Customer(String customerId) {
        this.customerId = customerId;
    }
}
