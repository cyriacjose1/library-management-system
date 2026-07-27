import service.BookService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;

import repository.BookRepository;
import repository.TransactionRepository;
import repository.UserRepository;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final BookRepository bookRepository =new BookRepository();
    private static final UserRepository userRepository =new UserRepository();
    private static final TransactionRepository transactionRepository =new TransactionRepository();

    private static final BookService bookService =new BookService(bookRepository);

    private static final UserService userService =new UserService(userRepository);

    private static final TransactionService transactionService =new TransactionService(transactionRepository, bookRepository, userRepository);
    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    removeBook();
                    break;

                case 5:
                    registerUser();
                    break;

                case 6:
                    viewUsers();
                    break;

                case 7:
                    borrowBook();
                    break;

                case 8:
                    returnBook();
                    break;

                case 9:
                    viewTransactions();
                    break;

                case 0:
                    System.out.println("\nThank you for using Library Management System!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n=================================");
        System.out.println(" LIBRARY MANAGEMENT SYSTEM ");
        System.out.println("=================================");
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Search Book");
        System.out.println("4. Remove Book");
        System.out.println("5. Register User");
        System.out.println("6. View Users");
        System.out.println("7. Borrow Book");
        System.out.println("8. Return Book");
        System.out.println("9. View Transactions");
        System.out.println("0. Exit");
        System.out.println("=================================");
    }

    private static void addBook() {

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        bookService.addBook(isbn, title, author, genre);
    }

    private static void viewBooks() {
        bookService.viewAllBooks();
    }

    private static void searchBook() {

        System.out.println("\nSearch By");
        System.out.println("1. ISBN");
        System.out.println("2. Title");
        System.out.println("3. Author");
        System.out.println("4. Genre");

        System.out.print("Choice: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {

            case 1:
                System.out.print("ISBN: ");
                System.out.println(bookService.searchByISBN(scanner.nextLine()));
                break;

            case 2:
                System.out.print("Title: ");
                bookService.searchByTitle(scanner.nextLine());
                break;

            case 3:
                System.out.print("Author: ");
                bookService.searchByAuthor(scanner.nextLine());
                break;

            case 4:
                System.out.print("Genre: ");
                bookService.searchByGenre(scanner.nextLine());
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void removeBook() {

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        bookService.removeBook(isbn);
    }

    private static void registerUser() {

        System.out.print("User ID: ");
        String id = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Contact: ");
        String contact = scanner.nextLine();

        userService.registerUser(id, name, contact);
    }

    private static void viewUsers() {
        userService.viewAllUsers();
    }

    private static void borrowBook() {

        System.out.print("User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine();

        transactionService.borrowBook(userId, isbn);
    }

    private static void returnBook() {

        System.out.print("User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine();

        transactionService.returnBook(userId, isbn);
    }

    private static void viewTransactions() {
        transactionService.viewAllTransactions();
    }
}