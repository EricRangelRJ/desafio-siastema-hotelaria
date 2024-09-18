package br.com.desafio.customer_management.controllers;

import br.com.desafio.customer_management.models.dtos.CustomerDto;
import br.com.desafio.customer_management.models.dtos.CustomerResponseDto;
import br.com.desafio.customer_management.services.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerServiceImpl service;

    public CustomerController(CustomerServiceImpl userService) {
        this.service = userService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@Valid @RequestBody CustomerDto request) {
        var result = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAll() {
        var result = service.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @PutMapping("/{idCustomer}")
    public ResponseEntity<CustomerResponseDto> update(@Valid @RequestBody CustomerDto request) {
        var result = service.update(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable UUID customerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(service.findById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> delete(@PathVariable UUID customerId) {
        service.delete(customerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Cliente " + customerId + " excluído com sucesso!");
    }
}
