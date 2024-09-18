package br.com.desafio.customer_management.controllers;

import br.com.desafio.customer_management.models.dtos.CustomerDto;
import br.com.desafio.customer_management.models.dtos.CustomerResponseDto;
import br.com.desafio.customer_management.services.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerServiceImpl customerService;

    private CustomerDto customerDto;

    @BeforeEach
    public void setUp() {
        customerDto = new CustomerDto(null, "Jose Peixoto", "email@gmail.com", "1234567891");
    }

    @Test
    public void createCustomerIT() throws Exception {
        mockMvc.perform(post("/customer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void findAllCustomersIT() throws Exception {
        List<CustomerResponseDto> customerList = List.of(
                new CustomerResponseDto("11111111111", "Maria Santos", "email@email.com", "111111111"),
                new CustomerResponseDto("22222222222", "Jose Alves", "email2@email.com", "222222222")
        );
        when(customerService.findAll()).thenReturn(customerList);
        mockMvc.perform(get("/customers"));
    }

    @Test
    public void deleteCustomerIT() throws Exception {
        UUID customerId = UUID.randomUUID();
        doNothing().when(customerService).delete(customerId);
        mockMvc.perform(delete("/customer/{customerId}", customerId))
                .andExpect(status().isAccepted());
    }

}

