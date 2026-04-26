package com.ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Customer {
    private int customerID;
    private String name;
    private String email;
    private String passwordHash;
    private String phone;
    private String address;
    private List<Product> cart;
    
    // Static database for all customers
    private static HashMap<Integer, Customer> customersDB = new HashMap<>();
    private static HashMap<String, Integer> emailToIDMap = new HashMap<>();

    public Customer(int customerID, String name, String email, String password) {
        this(customerID, name, email, password, "", "");
    }
    
    public Customer(int customerID, String name, String email, String password, String phone, String address) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.phone = phone;
        this.address = address;
        this.cart = new ArrayList<>();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(password.hashCode());
        }
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public List<Product> getCart() { return new ArrayList<>(cart); }
    protected String getPasswordHash() { return passwordHash; }
    
    // Setters
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    
    public void setPassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }

    // Registration method
    public static Customer register(String name, String email, String password) {
        return register(name, email, password, "", "");
    }
    
    public static Customer register(String name, String email, String password, String phone, String address) {
        if (emailToIDMap.containsKey(email)) {
            System.out.println("❌ Email already registered!");
            return null;
        }
        
        int newID = customersDB.size() + 1001;
        Customer newCustomer = new Customer(newID, name, email, password, phone, address);
        customersDB.put(newID, newCustomer);
        emailToIDMap.put(email, newID);
        System.out.println("✅ Registration successful! Your Customer ID: " + newID);
        return newCustomer;
    }

    // Login method
    public static Customer login(String email, String password) {
        Integer customerID = emailToIDMap.get(email);
        if (customerID == null) {
            System.out.println("❌ Email not found!");
            return null;
        }
        
        Customer customer = customersDB.get(customerID);
        String inputHash = customer.hashPassword(password);
        
        if (customer.passwordHash.equals(inputHash)) {
            System.out.println("✅ Welcome back, " + customer.name + "!");
            return customer;
        } else {
            System.out.println("❌ Invalid password!");
            return null;
        }
    }

    // Shopping cart operations
    public void addToCart(Product product) {
        if (product != null && product.isInStock()) {
            cart.add(product);
            System.out.println("✓ " + product.getName() + " added to cart.");
        } else if (product != null) {
            System.out.println("❌ Sorry, " + product.getName() + " is out of stock!");
        }
    }

    public boolean removeFromCart(Product product) {
        boolean removed = cart.remove(product);
        if (removed) {
            System.out.println("✓ " + product.getName() + " removed from cart.");
        } else {
            System.out.println("✗ Product not found in cart.");
        }
        return removed;
    }

    public double calculateTotal() {
        return cart.stream()
                   .mapToDouble(Product::getPrice)
                   .sum();
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("\n🛒 Your cart is empty.");
            return;
        }
        
        System.out.println("\n🛒 " + name + "'s Shopping Cart:");
        System.out.println("-".repeat(50));
        for (int i = 0; i < cart.size(); i++) {
            Product p = cart.get(i);
            System.out.printf("%d. %-20s $%.2f%n", i + 1, p.getName(), p.getPrice());
        }
        System.out.println("-".repeat(50));
        System.out.printf("Total: $%.2f%n", calculateTotal());
    }

    public void clearCart() {
        cart.clear();
        System.out.println("✅ Cart cleared!");
    }
    
    // Display customer information
    public void displayCustomerInfo() {
        System.out.println("\n👤 CUSTOMER INFORMATION");
        System.out.println("=".repeat(40));
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + (phone.isEmpty() ? "Not provided" : phone));
        System.out.println("Address: " + (address.isEmpty() ? "Not provided" : address));
        System.out.println("Cart Items: " + cart.size());
        System.out.println("=".repeat(40));
    }
    
    public static Customer getCustomerByID(int customerID) {
        return customersDB.get(customerID);
    }
}