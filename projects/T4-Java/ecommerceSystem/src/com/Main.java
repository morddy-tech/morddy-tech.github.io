package com;

import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Product> productCatalog = new ArrayList<>();
    private static Customer currentCustomer = null;
    private static List<Order> orderHistory = new ArrayList<>();

    public static void main(String[] args) {
        initializeProducts();
        runLandingPage();
    }

    private static void initializeProducts() {
        productCatalog.add(new Product(1, "MacBook Pro", 1299.99, "Electronics"));
        productCatalog.add(new Product(2, "iPhone 15", 999.99, "Electronics"));
        productCatalog.add(new Product(3, "Sony Headphones", 249.99, "Audio"));
        productCatalog.add(new Product(4, "Mechanical Keyboard", 159.99, "Accessories"));
        productCatalog.add(new Product(5, "Gaming Mouse", 79.99, "Accessories"));
    }

    private static void runLandingPage() {
        while (true) {
            displayHeader();
            displayMenu();
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    loginCustomer();
                    break;
                case 3:
                    browseProducts();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    if (currentCustomer != null) {
                        logout();
                    }
                    System.out.println("\n👋 Thank you for visiting! Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            
            if (choice != 6) {
                pressEnterToContinue();
            }
        }
    }

    private static void displayHeader() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           🛍️  WELCOME TO E-COMMERCE STORE  🛍️");
        System.out.println("=".repeat(60));
        
        if (currentCustomer != null) {
            System.out.println("👤 Logged in as: " + currentCustomer.getName() + 
                             " | Cart Items: " + currentCustomer.getCart().size());
            System.out.println("=".repeat(60));
        }
    }

    private static void displayMenu() {
        System.out.println("\n📋 MAIN MENU:");
        System.out.println("-".repeat(60));
        System.out.println("1. 📝 Register New Account");
        System.out.println("2. 🔐 Login to Existing Account");
        System.out.println("3. 🛒 Browse Products");
        System.out.println("4. 🛍️ View Shopping Cart");
        System.out.println("5. 📦 Place Order");
        System.out.println("6. 🚪 Exit");
        System.out.println("-".repeat(60));
    }

    private static void registerCustomer() {
        System.out.println("\n📝 CUSTOMER REGISTRATION");
        System.out.println("-".repeat(40));
        
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim().toLowerCase();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("❌ All fields are required!");
            return;
        }
        
        Customer newCustomer = Customer.register(name, email, password);
        if (newCustomer != null) {
            currentCustomer = newCustomer;
        }
    }

    private static void loginCustomer() {
        System.out.println("\n🔐 CUSTOMER LOGIN");
        System.out.println("-".repeat(40));
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim().toLowerCase();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        Customer customer = Customer.login(email, password);
        if (customer != null) {
            currentCustomer = customer;
        }
    }

    private static void logout() {
        currentCustomer = null;
        System.out.println("✅ Successfully logged out!");
    }

    private static void browseProducts() {
        System.out.println("\n🛒 PRODUCT CATALOG");
        System.out.println("=".repeat(60));
        System.out.printf("%-5s %-25s %-10s %-15s%n", "ID", "Product", "Price", "Category");
        System.out.println("-".repeat(60));
        
        for (Product p : productCatalog) {
            System.out.printf("%-5d %-25s $%-9.2f %-15s%n", 
                            p.getProductID(), p.getName(), p.getPrice(), p.getCategory());
        }
        System.out.println("=".repeat(60));
        
        if (currentCustomer == null) {
            System.out.println("\n⚠️  Please login to add items to cart!");
            return;
        }
        
        System.out.print("\nEnter Product ID to add to cart (0 to cancel): ");
        int productID = getIntInput("");
        
        if (productID == 0) return;
        
        Product selectedProduct = findProductByID(productID);
        if (selectedProduct != null) {
            currentCustomer.addToCart(selectedProduct);
        } else {
            System.out.println("❌ Invalid Product ID!");
        }
    }

    private static void viewCart() {
        if (currentCustomer == null) {
            System.out.println("\n⚠️  Please login to view your cart!");
            return;
        }
        
        currentCustomer.displayCart();
        
        if (!currentCustomer.getCart().isEmpty()) {
            System.out.println("\n📋 Cart Options:");
            System.out.println("1. Remove item from cart");
            System.out.println("2. Clear entire cart");
            System.out.println("3. Continue shopping");
            System.out.print("Enter choice: ");
            
            int choice = getIntInput("");
            if (choice == 1) {
                System.out.print("Enter product number to remove: ");
                int index = getIntInput("") - 1;
                List<Product> cart = currentCustomer.getCart();
                if (index >= 0 && index < cart.size()) {
                    currentCustomer.removeFromCart(cart.get(index));
                } else {
                    System.out.println("❌ Invalid product number!");
                }
            } else if (choice == 2) {
                currentCustomer.clearCart();
                System.out.println("✅ Cart cleared!");
            }
        }
    }

    private static void placeOrder() {
        if (currentCustomer == null) {
            System.out.println("\n⚠️  Please login to place an order!");
            return;
        }
        
        if (currentCustomer.getCart().isEmpty()) {
            System.out.println("\n❌ Your cart is empty! Add some products first.");
            return;
        }
        
        currentCustomer.displayCart();
        System.out.println("\n💳 CHECKOUT");
        System.out.println("-".repeat(40));
        System.out.print("Confirm order placement? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("yes") || confirm.equals("y")) {
            Order newOrder = new Order(currentCustomer, currentCustomer.getCart());
            orderHistory.add(newOrder);
            newOrder.displayOrderSummary();
            currentCustomer.clearCart();
        } else {
            System.out.println("❌ Order cancelled.");
        }
    }

    private static Product findProductByID(int id) {
        return productCatalog.stream()
                .filter(p -> p.getProductID() == id)
                .findFirst()
                .orElse(null);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Please enter a valid number: ");
            }
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}