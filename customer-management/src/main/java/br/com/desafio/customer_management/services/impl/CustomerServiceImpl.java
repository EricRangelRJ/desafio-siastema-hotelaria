package br.com.desafio.customer_management.services.impl;


import br.com.desafio.customer_management.models.dtos.CustomerDto;
import br.com.desafio.customer_management.models.dtos.CustomerResponseDto;
import br.com.desafio.customer_management.models.entities.Customer;
import br.com.desafio.customer_management.repository.CustomerRepository;
import br.com.desafio.customer_management.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    @Override
    public CustomerResponseDto create(CustomerDto dto) {
        Customer result =  repository.save(dto.toEntity());
        return result.toDto();
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return repository.findAll().stream()
                .map(Customer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto findById(UUID idCustomer) {
        Optional<Customer> result =  repository.findById(valueOf(idCustomer));
        if(result.isPresent()){
            Customer customer = result.get();
            return customer.toDto();
        }
        return null;
    }

    @Override
    public CustomerResponseDto update(CustomerDto customerDto) {
        var result =  repository.save(customerDto.toEntity(valueOf(customerDto.idCustomer())));
        return result.toDto();
    }

    @Override
    public void delete(UUID idCustomer) {
         repository.deleteById(valueOf(idCustomer));
    }
}
