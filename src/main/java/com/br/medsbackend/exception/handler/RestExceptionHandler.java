package com.br.medsbackend.exception.handler;

import com.br.medsbackend.exception.AlreadyExistsException;
import com.br.medsbackend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestExceptionHandlerResponse> NotFoundHandler (
            NotFoundException exception) {
        RestExceptionHandlerResponse treatedException = new RestExceptionHandlerResponse(
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatedException);
    }


    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<RestExceptionHandlerResponse> AlreadyExistsHandler(
            AlreadyExistsException exception) {
        RestExceptionHandlerResponse treatedException = new RestExceptionHandlerResponse(
                HttpStatus.CONFLICT.name(),
                HttpStatus.CONFLICT.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(treatedException);
    }

    @ExceptionHandler(DateTimeException.class)
    private ResponseEntity<RestExceptionHandlerResponse> dateTimeHandler(
            DateTimeException exception) {
        RestExceptionHandlerResponse treatedException = new RestExceptionHandlerResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(treatedException);
    }
}
