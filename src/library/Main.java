package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// ============================================================
// FILE 2: MAIN APPLICATION - Simple CLI
// ============================================================

public class Main {
    
    static List<Book> books = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static Map<String, LocalDate> issueDates = new HashMap<>();
    static int bookIdCounter = 1;
    static int userIdCounter = 1;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Add sample data
        books.add(new Book("B001", "Java Programming", "James Gosling"));
        books.add(new Book("B002", "Clean Code", "Robert Martin"));
        users.add(new User("U001", "John Doe", "john@email.com"));
        
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1: bookMenu(); break;
                case 2: userMenu(); break;
                case 3: transactionMenu(); break;
                case 4: searchMenu(); break;
                case 5: showAll(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid choice!");
            }
        }
        System.out.println("Goodbye!");
    }

    static void printMenu() {
        System.out.println("\n=== LIBRARY SYSTEM ===");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Issue/Return");
        System.out.println("4. Search");
        System.out.println("5. Show All");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    // ==================== BOOK MENU ====================
    
    static void bookMenu() {
        System.out.println("1. Add 2. Remove 3. Update 4. List 5. Back");
        int c = scanner.nextInt();
        scanner.nextLine();
        
        if (c == 1) {
            System.out.print("Title: ");
            String t = scanner.nextLine();
            System.out.print("Author: ");
            String a = scanner.nextLine();
            String id = "B" + (bookIdCounter++);
            books.add(new Book(id, t, a));
            System.out.println("Added: " + id);
        }
        else if (c == 2) {
            System.out.print("Book ID: ");
            String id = scanner.nextLine();
            books.removeIf(b -> b.id.equals(id));
            System.out.println("Removed!");
        }
        else if (c == 3) {
            System.out.print("Book ID: ");
            String id = scanner.nextLine();
            for (Book b : books) {
                if (b.id.equals(id)) {
                    System.out.print("New Title: ");
                    b.title = scanner.nextLine();
                    System.out.print("New Author: ");
                    b.author = scanner.nextLine();
                    System.out.println("Updated!");
                }
            }
        }
        else if (c == 4) {
            for (Book b : books) {
                System.out.println(b.id + " | " + b.title + " | " + b.author + " | " + (b.isIssued ? "Issued" : "Available"));
            }
        }
    }

    // ==================== USER MENU ====================
    
    static void userMenu() {
        System.out.println("1. Register 2. Remove 3. List 4. Back");
        int c = scanner.nextInt();
        scanner.nextLine();
        
        if (c == 1) {
            System.out.print("Name: ");
            String n = scanner.nextLine();
            System.out.print("Email: ");
            String e = scanner.nextLine();
            String id = "U" + (userIdCounter++);
            users.add(new User(id, n, e));
            System.out.println("Registered: " + id);
        }
        else if (c == 2) {
            System.out.print("User ID: ");
            String id = scanner.nextLine();
            users.removeIf(u -> u.id.equals(id));
            System.out.println("Removed!");
        }
        else if (c == 3) {
            for (User u : users) {
                System.out.println(u.id + " | " + u.name + " | " + u.email + " | Books: " + u.borrowedBooks.size());
            }
        }
    }

    // ==================== TRANSACTION MENU ====================
    
    static void transactionMenu() {
        System.out.println("1. Issue 2. Return 3. View Issued 4. Back");
        int c = scanner.nextInt();
        scanner.nextLine();
        
        if (c == 1) {
            System.out.print("Book ID: ");
            String bid = scanner.nextLine();
            System.out.print("User ID: ");
            String uid = scanner.nextLine();
            
            Book b = findBook(bid);
            User u = findUser(uid);
            
            if (b != null && u != null && !b.isIssued) {
                b.isIssued = true;
                b.issuedTo = uid;
                u.borrowedBooks.add(bid);
                issueDates.put(bid, LocalDate.now());
                System.out.println("Issued! Due: " + LocalDate.now().plusDays(14));
            } else {
                System.out.println("Invalid or already issued!");
            }
        }
        else if (c == 2) {
            System.out.print("Book ID: ");
            String bid = scanner.nextLine();
            
            Book b = findBook(bid);
            if (b != null && b.isIssued) {
                LocalDate issueDate = issueDates.get(bid);
                long days = ChronoUnit.DAYS.between(issueDate, LocalDate.now());
                
                if (days > 14) {
                    System.out.println("Late! Fine: $" + ((days - 14) * 5));
                }
                
                User u = findUser(b.issuedTo);
                b.isIssued = false;
                b.issuedTo = null;
                u.borrowedBooks.remove(bid);
                issueDates.remove(bid);
                System.out.println("Returned!");
            } else {
                System.out.println("Not found or not issued!");
            }
        }
        else if (c == 3) {
            for (Book b : books) {
                if (b.isIssued) {
                    System.out.println(b.id + " | " + b.title + " | Issued to: " + b.issuedTo);
                }
            }
        }
    }

    // ==================== SEARCH ====================
    
    static void searchMenu() {
        System.out.println("1. By Title 2. By Author 3. Back");
        int c = scanner.nextInt();
        scanner.nextLine();
        
        if (c == 1) {
            System.out.print("Title: ");
            String s = scanner.nextLine();
            for (Book b : books) {
                if (b.title.toLowerCase().contains(s.toLowerCase())) {
                    System.out.println(b.id + " | " + b.title + " | " + b.author);
                }
            }
        }
        else if (c == 2) {
            System.out.print("Author: ");
            String s = scanner.nextLine();
            for (Book b : books) {
                if (b.author.toLowerCase().contains(s.toLowerCase())) {
                    System.out.println(b.id + " | " + b.title + " | " + b.author);
                }
            }
        }
    }

    // ==================== SHOW ALL ====================
    
    static void showAll() {
        System.out.println("\n=== BOOKS ===");
        for (Book b : books) {
            System.out.println(b.id + " | " + b.title + " | " + b.author + " | " + (b.isIssued ? "Issued" : "Available"));
        }
        System.out.println("\n=== USERS ===");
        for (User u : users) {
            System.out.println(u.id + " | " + u.name + " | " + u.email);
        }
    }

    // ==================== HELPERS ====================
    
    static Book findBook(String id) {
        for (Book b : books) {
            if (b.id.equals(id)) return b;
        }
        return null;
    }

    static User findUser(String id) {
        for (User u : users) {
            if (u.id.equals(id)) return u;
        }
        return null;
    }
}