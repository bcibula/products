package com.smartshop.products.controller;
// The controller is the entry point for all HTTP requests.
// It maps incoming requests to the right service method and returns the response.
// It sits between the client (Postman, browser, etc.) and the service layer.
// It does not contain business logic — it just receives, delegates, and responds.

import com.smartshop.products.model.Product;
import com.smartshop.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller
// Return values from methods are automatically converted to JSON or XML
@RestController

// All endpoints in this class start with /api/products
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Spring automatically injects the ProductService when this class is created
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    // Returns all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /api/products/{id}
    // {id} in the URL gets passed in as the id parameter
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // GET /api/products/category/{category}
    // Returns all products matching the given category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // GET /api/products/search?keyword=mouse
    // The keyword comes from the query string after the ?
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    // POST /api/products
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @RequestBody converts the incoming JSON into a Product object
    // @Valid runs the validation rules defined on the Product fields
    // Returns 201 Created instead of the default 200 OK
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    // PUT /api/products/{id}
    // Updates an existing product by ID
    // Returns the updated product with 200 OK
    @PutMapping("/{id}")
        public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // DELETE /api/products/{id}
    // Deletes a product by ID
    // Returns 204 No Content - successful delete with no response body
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}