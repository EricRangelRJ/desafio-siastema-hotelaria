package br.com.desafio.customer_management.repository;

import br.com.desafio.customer_management.models.dtos.CustomerResponseDto;
import br.com.desafio.customer_management.models.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
