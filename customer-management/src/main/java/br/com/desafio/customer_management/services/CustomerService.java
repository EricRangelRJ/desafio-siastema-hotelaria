package br.com.desafio.customer_management.services;

import br.com.desafio.customer_management.models.dtos.CustomerDto;
import br.com.desafio.customer_management.models.dtos.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    public CustomerResponseDto create(CustomerDto dto);

    public List<CustomerResponseDto> findAll();

    public CustomerResponseDto findById(UUID idCustomer);

    public CustomerResponseDto update(CustomerDto customerDto);
    public void delete(UUID idCustomer);
}
