package com.smartshop.products.exception;

// A custom exception thrown when a product cannot be found.
// GlobalExceptionHandler catches this exception and returns a 404 Not Found response.
public class ProductNotFoundException extends RuntimeException {

    // Creates the exception with a custom error message.
    public ProductNotFoundException(String message) {
        super(message);
    }

}