package br.com.ntconsult.desafio.hotel_management.controllers.exceptions;

import br.com.ntconsult.desafio.hotel_management.model.error.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException runtimeException) {
        ErrorDto errorDto = new ErrorDto(
                "Ocorreu um erro interno",
                runtimeException.getMessage(),
                "500", null);
        logger.error("Ocorreu um erro interno ::::", runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<ErrorDto> handleRuntimeException(IllegalArgumentException argumentException) {
        ErrorDto errorDto = new ErrorDto(
                "Solicitação inválida",
                argumentException.getMessage(),
                "400", null);
        logger.error("Requisição Inválida :::: ", argumentException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<ErrorDto> handleRuntimeException(NoSuchElementException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Sem dados de retorno",
                exception.getMessage(),
                "204", null);
        logger.info("::: Requisição Inválida ::: ", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorDto);
    }


    @ExceptionHandler({ NoResourceFoundException.class })
    public ResponseEntity<ErrorDto> handleNotFoundException(NoResourceFoundException ex) {
        ErrorDto errorDto = new ErrorDto(
                "Erro interno",
                "Recurso não encontrado.",
                "404", null);
        logger.info("::: Recurso não encontrado ::: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

}
