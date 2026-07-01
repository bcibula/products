package com.smartshop.products.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

// Represents a product in the SmartShop application.
// This class serves multiple purposes:
// - Maps the Product object to the "products" table in MySQL.
// - Validates incoming request data.
// - Supports XML serialization.
@Entity
@Table(name = "products")
@XmlRootElement(name = "product")
public class Product {

    // Primary key for each product.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    // Requires a non-empty title.
    @NotBlank(message = "Title is required")
    private String title;

    // Requires a non-empty description.
    @NotBlank(message = "Description is required")
    private String description;

    // Requires the price to be present and greater than zero.
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private double price;

    // Allows zero inventory but prevents negative values.
    @NotNull(message = "Inventory quantity is required")
    @PositiveOrZero(message = "Inventory quantity cannot be negative")
    @Column(name = "inventory_quantity")
    private int inventoryQuantity;

    // Requires an image URL and maps it to the image_url column.
    @NotBlank(message = "Image URL is required")
    @Column(name = "image_url")
    private String imageUrl;

    // Requires a non-empty category.
    @NotBlank(message = "Category is required")
    private String category;

    // JPA ignores this field because it is calculated at runtime.
    // It is never stored in the database.
    @Transient
    private double discountedPrice;

    // Standard getters and setters used by Spring, Jackson, and JAXB.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Calculates the discounted price each time it is requested.
    // Because Jackson and JAXB serialize getter methods,
    // discountedPrice is included automatically in responses.
    public double getDiscountedPrice() {
        return Math.round(price * 0.90 * 100.0) / 100.0;
    }

    // Required by JAXB so discountedPrice can be serialized.
    // The actual value is still calculated in the getter.
    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

}