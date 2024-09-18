package br.com.desafio.customer_management.controllers;

import br.com.desafio.customer_management.controllers.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerControllerTestAdvaceTest {

    @Test
    void shouldHandleInternalServerErrorWithSpringException() {

        //Arrange
        var exceptionHandler = new GlobalExceptionHandler();
        var exception = new HttpMessageNotReadableException("Error");

        //Act
        var response = exceptionHandler.handleRuntimeException(exception);

        //Assert
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
