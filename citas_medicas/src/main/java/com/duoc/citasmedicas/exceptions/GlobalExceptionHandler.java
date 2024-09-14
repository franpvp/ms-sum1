package com.duoc.citasmedicas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejar excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejar excepciones personalizadas
    @ExceptionHandler(CitaMedicaNotFoundException.class)
    public ResponseEntity<?> handleCitaMedicaNotFoundException(CitaMedicaNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Manejar errores de validación
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleValidationException(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
