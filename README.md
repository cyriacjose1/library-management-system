# Library Management System

A Java console-based Library Management System developed using Object-Oriented Programming (OOP) principles and file handling. The application enables librarians to manage books, users, and borrowing transactions with persistent data storage using Java Serialization.

## Features

- Add, view, search, and remove books
- Register, view, search, and remove users
- Borrow and return books
- View transaction history
- Persistent data storage using `.dat` files

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- File Handling
- Java Serialization

## Project Architecture

```text
LibraryManagementSystem/
│
├── src/
│   ├── model/
│   │   ├── Book.java
│   │   ├── User.java
│   │   ├── Librarian.java
│   │   └── Transaction.java
│   │
│   ├── repository/
│   │   ├── BookRepository.java
│   │   ├── UserRepository.java
│   │   └── TransactionRepository.java
│   │
│   ├── service/
│   │   ├── BookService.java
│   │   ├── UserService.java
│   │   └── TransactionService.java
│   │
│   └── Main.java
│
├── data/
├── README.md
└── .gitignore
```

## Data Storage

The application uses Java Serialization for persistent data storage. The following files are created automatically in the `data/` directory when data is first saved:

- `books.dat`
- `users.dat`
- `transactions.dat`

## Running the Project

1. Clone the repository.
2. Open the project in your preferred Java IDE.
3. Compile and run `Main.java`.