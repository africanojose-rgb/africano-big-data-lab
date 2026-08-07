package com.africano.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleCustomerNotFound(
            CustomerNotFoundException ex) {

        return Map.of(
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage()
        );
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleIllegalArgument(
            IllegalArgumentException ex) {

        return Map.of(
                "status", 409,
                "error", "Conflict",
                "message", ex.getMessage()
        );
    }
}