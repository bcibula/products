package com.smartshop.products.repository;

import com.smartshop.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Extends JpaRepository which gives us all the standard CRUDS methods
// The whole point of JpaRepository is that Spring generates the implementation at runtime, so there's nothing else to write
// The two type parameters tell Spring that we are managing Product objects with Long as the ID type
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived query method - Spring Data reads the method name and generate the SQL automatically
    List<Product> findByCategory(String category);

    // Another derived query method - searches for a keyword anywhere within the Title field
    // the match will be case-insensitive
    List<Product> findByTitleContainingIgnoreCase(String keyword);

}