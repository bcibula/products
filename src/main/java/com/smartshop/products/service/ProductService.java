package com.smartshop.products.service;
// The service layer contains the application's business logic.
// It sits between the controller (HTTP requests) and the repository (database).

import com.smartshop.products.exception.ProductNotFoundException;
import com.smartshop.products.model.Product;
import com.smartshop.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service tells Spring this class contains business logic
// Spring creates and manages this object automatically
 @Service
public class ProductService {

    // References the repository that accesses the database
    // Final means it must be assigned once and cannot be changed
    private final ProductRepository productRepository;

    // Constructor injection
    // Spring automatically provides the ProductRepository object
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Return all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Find a single product by its ID
    // And throw an exception if the product does not exist
    // Note: the lambda () -> creates the exception only if it's actually needed
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    // Return all products in a category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Search for products whose title contains the keyword
    // This search is case-insensitive
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // Save a new product to the database
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(Long id, Product product) {

        // Find the existing product or throw an exception
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Copy the updated values into the existing product
        existing.setTitle(product.getTitle());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setInventoryQuantity(product.getInventoryQuantity());
        existing.setImageUrl(product.getImageUrl());
        existing.setCategory(product.getCategory());

        // Save the updated product
        return productRepository.save(existing);
    }

    // Delete a product by its ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}