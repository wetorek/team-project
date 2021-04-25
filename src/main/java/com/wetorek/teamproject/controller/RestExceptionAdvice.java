package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.exceptions.ErrorResponse;
import com.wetorek.teamproject.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionAdvice {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalStateException.class)
    ErrorResponse handleConflict(IllegalStateException ex) {
        return ErrorResponse.builder()
                .errorCode("Illegal operation performed")
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ErrorResponse handleConflict(IllegalArgumentException ex) {
        return ErrorResponse.builder()
                .errorCode("Illegal argument given")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    ErrorResponse handleVisitNotFound(ResourceNotFoundException ex) {
        return ErrorResponse.builder()
                .errorCode("Resource not found")
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .collect(
                        HashMap::new,
                        (m, v) -> m.put(((FieldError) v).getField(), v.getDefaultMessage()),
                        HashMap::putAll
                );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    ErrorResponse handleBadLogin() {
        return ErrorResponse.builder()
                .errorCode("Authentication error")
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now())
                .errorMessage("Bad credentials given")
                .build();
    }
}
