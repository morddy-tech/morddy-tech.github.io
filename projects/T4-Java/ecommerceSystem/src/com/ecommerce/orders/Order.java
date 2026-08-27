package com.ecommerce.orders;

import com.ecommerce.Product;
import com.ecommerce.Customer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }
    
    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double total;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private String shippingAddress;
    private static int orderCounter = 10000;

    public Order(Customer customer, List<Product> products) {
        this(customer, products, customer.getAddress());
    }
    
    public Order(Customer customer, List<Product> products, String shippingAddress) {
        this.orderID = ++orderCounter;
        this.customer = customer;
        this.products = new ArrayList<>(products);
        this.total = calculateTotal();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.shippingAddress = shippingAddress.isEmpty() ? "Address not provided" : shippingAddress;
    }

    private double calculateTotal() {
        return products.stream()
                      .mapToDouble(Product::getPrice)
                      .sum();
    }

    // Getters
    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return new ArrayList<>(products); }
    public double getTotal() { return total; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public OrderStatus getStatus() { return status; }
    public String getShippingAddress() { return shippingAddress; }
    
    // Setters
    public void setOrderID(int orderID) { this.orderID = orderID; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setProducts(List<Product> products) { 
        this.products = new ArrayList<>(products);
        this.total = calculateTotal();
    }
    public void setShippingAddress(String shippingAddress) { 
        this.shippingAddress = shippingAddress; 
    }

    // Order status management
    public void updateOrderStatus(OrderStatus newStatus) {
        if (this.status != OrderStatus.CANCELLED && this.status != OrderStatus.DELIVERED) {
            this.status = newStatus;
            System.out.println("✅ Order #" + orderID + " status updated to: " + newStatus);
        } else {
            System.out.println("❌ Cannot update status. Order is already " + this.status);
        }
    }
    
    public void cancelOrder() {
        if (status == OrderStatus.PENDING || status == OrderStatus.PROCESSING) {
            this.status = OrderStatus.CANCELLED;
            System.out.println("✅ Order #" + orderID + " has been cancelled.");
        } else {
            System.out.println("❌ Cannot cancel order. Current status: " + status);
        }
    }
    
    public void manageOrderInfo(String newAddress, String specialInstructions) {
        if (status == OrderStatus.PENDING) {
            if (newAddress != null && !newAddress.isEmpty()) {
                this.shippingAddress = newAddress;
            }
            System.out.println("✅ Order information updated.");
        } else {
            System.out.println("❌ Cannot modify order. Status: " + status);
        }
    }

    // Generate order summary
    public String generateOrderSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder summary = new StringBuilder();
        
        summary.append("\n").append("=".repeat(60)).append("\n");
        summary.append("                    ORDER SUMMARY\n");
        summary.append("=".repeat(60)).append("\n");
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Date: ").append(orderDate.format(formatter)).append("\n");
        summary.append("Status: ").append(status).append("\n");
        summary.append("Customer: ").append(customer.getName())
               .append(" (ID: ").append(customer.getCustomerID()).append(")\n");
        summary.append("Email: ").append(customer.getEmail()).append("\n");
        summary.append("Shipping Address: ").append(shippingAddress).append("\n");
        summary.append("\n📦 Products Ordered:\n");
        summary.append("-".repeat(60)).append("\n");
        
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            summary.append(String.format("%d. %-25s $%8.2f\n", 
                          i + 1, p.getName(), p.getPrice()));
        }
        
        summary.append("-".repeat(60)).append("\n");
        summary.append(String.format("%-37s $%8.2f\n", "Subtotal:", total));
        summary.append(String.format("%-37s $%8.2f\n", "Tax (10%):", total * 0.10));
        summary.append(String.format("%-37s $%8.2f\n", "TOTAL:", total * 1.10));
        summary.append("=".repeat(60));
        
        return summary.toString();
    }
    
    public void displayOrderSummary() {
        System.out.println(generateOrderSummary());
    }
    
    @Override
    public String toString() {
        return String.format("Order{id=%d, customer='%s', total=$%.2f, status=%s}", 
                           orderID, customer.getName(), total, status);
    }
}