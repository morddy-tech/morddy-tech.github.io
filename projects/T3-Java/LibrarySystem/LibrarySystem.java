import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A simple library management system with add, borrow, return, display, and search.
 * Demonstrates encapsulation, input validation, and user-friendly error handling.
 */
class Book {
    private String title;
    private String author;
    private int quantity;   // must be non-negative

    public Book(String title, String author, int quantity) {
        setTitle(title);
        setAuthor(author);
        setQuantity(quantity);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = author.trim();
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative quantity");
        }
        this.quantity += amount;
        // Optional: cap at 9999 to prevent overflow
        if (this.quantity > 9999) this.quantity = 9999;
    }

    public boolean removeQuantity(int amount) {
        if (amount < 0) return false;
        if (this.quantity >= amount) {
            this.quantity -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("'%s' by %s - %d copies", title, author, quantity);
    }
}

public class LibrarySystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addBooks(); break;
                case "2": borrowBooks(); break;
                case "3": returnBooks(); break;
                case "4": displayBooks(); break;
                case "5": searchBooks(); break;
                case "6": exit = true; System.out.println("Exiting system. Goodbye!"); break;
                default: System.out.println("Invalid option. Please enter 1-6.");
            }
        }
        // scanner not closed because System.in should not be closed in simple console apps
    }

    private static void displayMenu() {
        System.out.println("\n====== Library Menu ======");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Display All Books");
        System.out.println("5. Search Books");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    // Add new books or increase quantity of existing
    private static void addBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("Author cannot be empty.");
            return;
        }

        System.out.print("Enter quantity to add: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please enter a positive integer.");
            return;
        }

        // Find existing book
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.addQuantity(quantity);
                System.out.println("Updated existing book. New quantity: " + book.getQuantity());
                return;
            }
        }

        // New book
        try {
            library.add(new Book(title, author, quantity));
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Borrow books (reduce quantity)
    private static void borrowBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Enter quantity to borrow: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getQuantity() >= quantity) {
                    book.removeQuantity(quantity);
                    System.out.println("Successfully borrowed " + quantity + " copy/copies.");
                    System.out.println("Remaining copies: " + book.getQuantity());
                } else {
                    System.out.println("Error: Not enough copies. Only " + book.getQuantity() + " available.");
                }
                return;
            }
        }
        System.out.println("Error: Book not found in library.");
    }

    // Return books (increase quantity)
    private static void returnBooks() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Enter quantity to return: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.addQuantity(quantity);
                System.out.println("Successfully returned " + quantity + " copy/copies.");
                System.out.println("Updated quantity: " + book.getQuantity());
                return;
            }
        }
        System.out.println("Error: This book does not belong to the library.");
    }

    // Display all books in library
    private static void displayBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n--- Library Collection ---");
        for (Book book : library) {
            System.out.println(book);
        }
    }

    // Search by title or author (case-insensitive, partial match)
    private static void searchBooks() {
        System.out.print("Enter search term (title or author): ");
        String term = scanner.nextLine().trim().toLowerCase();
        if (term.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        ArrayList<Book> results = new ArrayList<>();
        for (Book book : library) {
            if (book.getTitle().toLowerCase().contains(term) ||
                book.getAuthor().toLowerCase().contains(term)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            System.out.println("\nSearch results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }
}