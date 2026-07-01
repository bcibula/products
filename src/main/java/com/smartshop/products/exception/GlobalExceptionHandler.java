package com.smartshop.products.exception;
// The global exception handler intercepts errors thrown anywhere in the app
// and converts them into clean and structured JSON responses.
// Without this, Spring would return its own default error page or expose a stack trace.
// It sits outside the normal request flow and activates only when something goes wrong.

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Listens for exceptions thrown anywhere in the app
// and handles them before they reach the client
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Catches ProductNotFoundException and returns a 404 response
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {

        // Build a map to use as the JSON response body
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());   // ex. "Product not found with id: 9999"

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Catches validation failures from @Valid and returns a 400 response
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value()); // 400
        body.put("error", "Bad Request");
        // Gets the first validation error message, ex. "Price must be greater than zero"
        body.put("message", ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Catches anything else that wasn't handled above and returns a 500 response
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());   // 500
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}