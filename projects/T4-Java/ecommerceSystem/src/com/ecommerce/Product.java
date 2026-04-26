package com.ecommerce;

public class Product {
    private int productID;
    private String name;
    private double price;
    private String category;
    private int stockQuantity;

    public Product(int productID, String name, double price, String category) {
        this(productID, name, price, category, 100); // Default stock
    }

    public Product(int productID, String name, double price, String category, int stockQuantity) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public int getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters
    public void setProductID(int productID) { 
        this.productID = productID; 
    }
    
    public void setName(String name) { 
        if (name != null && !name.trim().isEmpty()) {
            this.name = name; 
        } else {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
    }
    
    public void setPrice(double price) { 
        if (price > 0) {
            this.price = price; 
        } else {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }
    
    public void setCategory(String category) { 
        this.category = category; 
    }
    
    public void setStockQuantity(int stockQuantity) { 
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity; 
        } else {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
    }

    // Additional product-related operations
    public void displayProduct() {
        System.out.printf("ID: %d | %-20s | $%.2f | %s | Stock: %d%n", 
                         productID, name, price, category, stockQuantity);
    }
    
    public boolean isInStock() {
        return stockQuantity > 0;
    }
    
    public boolean reduceStock(int quantity) {
        if (quantity > 0 && stockQuantity >= quantity) {
            stockQuantity -= quantity;
            return true;
        }
        return false;
    }
    
    public void increaseStock(int quantity) {
        if (quantity > 0) {
            stockQuantity += quantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productID == product.productID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(productID);
    }
    
    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=$%.2f, category='%s', stock=%d}", 
                           productID, name, price, category, stockQuantity);
    }
}