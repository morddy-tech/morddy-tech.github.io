import java.util.*;

class Book {
    String title;
    String author;
    int quantity;

    // Constructor
    Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}

public class LibrarySystem {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Book> library = new ArrayList<>();

    public static void main(String[] args) {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n====== Library Menu ======");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addBooks();
                        break;
                    case 2:
                        borrowBooks();
                        break;
                    case 3:
                        returnBooks();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Method to add books
    static void addBooks() {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            for (Book book : library) {
                if (book.title.equalsIgnoreCase(title)) {
                    book.quantity += quantity;
                    System.out.println("Book already exists. Quantity updated to " + book.quantity);
                    return;
                }
            }

            library.add(new Book(title, author, quantity));
            System.out.println("Book added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Quantity must be a valid number.");
        }
    }

    // Method to borrow books
    static void borrowBooks() {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter quantity to borrow: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            for (Book book : library) {
                if (book.title.equalsIgnoreCase(title)) {

                    if (book.quantity >= quantity) {
                        book.quantity -= quantity;
                        System.out.println("Successfully borrowed " + quantity + " copy/copies.");
                        System.out.println("Remaining copies: " + book.quantity);
                    } else {
                        System.out.println("Error: Not enough copies available.");
                    }
                    return;
                }
            }

            System.out.println("Error: Book not found in library.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
        }
    }

    // Method to return books
    static void returnBooks() {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter quantity to return: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            for (Book book : library) {
                if (book.title.equalsIgnoreCase(title)) {
                    book.quantity += quantity;
                    System.out.println("Successfully returned " + quantity + " copy/copies.");
                    System.out.println("Updated quantity: " + book.quantity);
                    return;
                }
            }

            System.out.println("Error: This book does not belong to the library.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
        }
    }
}