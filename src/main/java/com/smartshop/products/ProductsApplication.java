package com.smartshop.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marks this as the main Spring Boot application class.
// Spring uses this class to start the application and configure its components.
@SpringBootApplication
public class ProductsApplication {

	// Standard Java entry point - starts the Spring Boot application.
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

}
