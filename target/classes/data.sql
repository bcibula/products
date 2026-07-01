-- Seeds the database with the 10 required SmartShop products.
-- Spring Boot runs this file automatically on startup because
-- spring.sql.init.mode=always is enabled in application.properties.
-- The table is reset first so any test data is removed before the
-- required sample products are inserted.

-- Remove all existing products.
DELETE FROM products;

-- Reset the auto-increment counter so seed products always receive IDs 1-10.
ALTER TABLE products AUTO_INCREMENT = 1;

-- Insert the required sample products.
-- product_id is generated automatically.
-- discounted_price is calculated by the application and is not stored.
INSERT INTO products (title, description, price, inventory_quantity, image_url, category) VALUES
                                                                                              ('Wireless Mouse', 'Ergonomic wireless mouse', 29.99, 120, 'mouse.jpg', 'Electronics'),
                                                                                              ('Mechanical Keyboard', 'RGB gaming keyboard', 89.99, 45, 'keyboard.jpg', 'Electronics'),
                                                                                              ('Office Chair', 'Adjustable ergonomic chair', 249.99, 15, 'chair.jpg', 'Furniture'),
                                                                                              ('Laptop Stand', 'Aluminum laptop stand', 39.99, 80, 'stand.jpg', 'Accessories'),
                                                                                              ('USB-C Hub', 'Multiport USB-C hub', 59.99, 60, 'hub.jpg', 'Electronics'),
                                                                                              ('Monitor 27 Inch', '4K UHD monitor', 399.99, 22, 'monitor.jpg', 'Electronics'),
                                                                                              ('Desk Lamp', 'LED desk lamp', 34.99, 75, 'lamp.jpg', 'Furniture'),
                                                                                              ('Webcam', 'HD Full HD webcam', 69.99, 50, 'webcam.jpg', 'Electronics'),
                                                                                              ('Headphones', 'Noise-cancelling headphones', 149.99, 35, 'headphones.jpg', 'Electronics'),
                                                                                              ('Portable SSD', '1TB portable SSD', 129.99, 40, 'ssd.jpg', 'Storage');