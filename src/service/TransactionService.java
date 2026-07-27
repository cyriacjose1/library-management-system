package service;

import model.Book;
import model.Transaction;
import model.User;
import repository.BookRepository;
import repository.TransactionRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TransactionService {

    private TransactionRepository transactionRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void borrowBook(String userId, String isbn) {

        User user = userRepository.findById(userId);
        Book book = bookRepository.findByISBN(isbn);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
            return;
        }

        book.setAvailable(false);
        user.borrowBook(isbn);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                book,
                user,
                LocalDate.now()
        );

        transactionRepository.addTransaction(transaction);

        bookRepository.updateBooks();
        userRepository.updateUsers();

        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(String userId, String isbn) {

        User user = userRepository.findById(userId);
        Book book = bookRepository.findByISBN(isbn);

        if (user == null || book == null) {
            System.out.println("User or Book not found.");
            return;
        }

        Transaction transaction =
                transactionRepository.findActiveTransactionByBook(isbn);

        if (transaction == null) {
            System.out.println("No active transaction found.");
            return;
        }

        transaction.setReturnDate(LocalDate.now());

        book.setAvailable(true);
        user.returnBook(isbn);

        transactionRepository.updateTransactions();
        bookRepository.updateBooks();
        userRepository.updateUsers();

        System.out.println("Book returned successfully.");
    }

    public void viewAllTransactions() {

        List<Transaction> transactions =
                transactionRepository.getAllTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void viewTransactionsByUser(String userId) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }
}