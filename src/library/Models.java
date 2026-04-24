package library;

import java.util.ArrayList;
import java.util.List;

// ============================================================
// FILE 1: MODELS - Simple Book and User classes
// ============================================================

class Book {
    // Public fields - no getters/setters/override needed
    String id;
    String title;
    String author;
    boolean isIssued;
    String issuedTo;

    Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }
}

class User {
    String id;
    String name;
    String email;
    List<String> borrowedBooks;

    User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }
}