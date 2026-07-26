package service;

import model.Book;
import repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        bookRepository = new BookRepository();
    }

    public void addBook(String isbn, String title, String author, String genre) {

        if (bookRepository.findByISBN(isbn) != null) {
            System.out.println("Book with this ISBN already exists.");
            return;
        }

        Book book = new Book(isbn, title, author, genre);
        bookRepository.addBook(book);

        System.out.println("Book added successfully.");
    }

    public void viewAllBooks() {

        List<Book> books = bookRepository.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book searchByISBN(String isbn) {

        Book book = bookRepository.findByISBN(isbn);

        if (book == null) {
            System.out.println("Book not found.");
        }

        return book;
    }

    public void searchByTitle(String title) {

        List<Book> books = bookRepository.searchByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }

        books.forEach(System.out::println);
    }

    public void searchByAuthor(String author) {

        List<Book> books = bookRepository.searchByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }

        books.forEach(System.out::println);
    }

    public void searchByGenre(String genre) {

        List<Book> books = bookRepository.searchByGenre(genre);

        if (books.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }

        books.forEach(System.out::println);
    }

    public void removeBook(String isbn) {

        if (bookRepository.removeBook(isbn)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void updateBook(Book book) {
        bookRepository.updateBooks();
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }
}