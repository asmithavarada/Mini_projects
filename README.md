# Library Management System

A Java-based CLI application for managing library operations including book inventory, user registration, and book issuing/returning.

## 📚 Features

### Book Management
- Add new books with title and author
- Remove books from inventory
- Update book details
- View all books

### User Management
- Register new users
- Remove users (only if no books borrowed)
- View all registered users

### Transaction Management
- Issue books to users (14-day due date)
- Return books with automatic fine calculation
- View currently issued books

### Search Functionality
- Search books by title
- Search books by author

### Fine Calculation
- $5 per day after 14-day due date
- Automatic calculation on return

## 🛠 Tools & Technologies

| Category | Technology |
|----------|-------------|
| **Language** | Java 17+ |
| **Paradigm** | Object-Oriented Programming (OOP) |
| **Data Structures** | ArrayList, HashMap, Stream API |
| **Date Handling** | java.time (LocalDate, ChronoUnit) |
| **Build Tool** | Manual compilation (javac) |

## 🏗 OOP Concepts Used

- **Encapsulation**: Private fields with public getters/setters
- **Abstraction**: Separate model, manager, and UI layers
- **Collections**: ArrayList for books/users, HashMap for issue dates
- **Lambda Expressions**: Stream API for filtering and searching
- **Date Handling**: LocalDate for issue/return dates

## 📁 Project Structure

```
LibraryManagement/
├── src/
│   └── library/
│       ├── Main.java           # Entry point & CLI menu
│       ├── LibraryManager.java # Business logic
│       ├── Book.java          # Book model
│       └── User.java          # User model
├── README.md
└── .gitignore
```

## 🚀 How to Run

### Compile
```bash
cd src
javac library/*.java
```

### Run
```bash
java library.Main
```

## 📖 Sample Output

```
╔════════════════════════════════════════╗
║     LIBRARY MANAGEMENT SYSTEM          ║
╚════════════════════════════════════════╝

┌────────────── MAIN MENU ──────────────┐
│  1. Book Management                   │
│  2. User Management                   │
│  3. Issue/Return Books                 │
│  4. Search Books                       │
│  5. Display Reports                    │
│  0. Exit                               │
└────────────────────────────────────────┘
```

## 🔧 SQL Join Concepts (Future Enhancement)

When implementing JDBC + MySQL:
- **INNER JOIN**: Get all issued books with user details
- **LEFT JOIN**: Get all books with their issue status
- **GROUP BY**: Count books per user

## 📝 Key Classes

| Class | Purpose |
|-------|---------|
| `Book` | Model class for book data |
| `User` | Model class for user data |
| `LibraryManager` | Core business logic |
| `Main` | CLI interface and menu system |

## 📅 Due Date System

- Books issued for 14 days
- Fine: $5/day for late returns
- Automatic calculation on book return

---

**Created**: April 2026  
**Author**: Java Developer