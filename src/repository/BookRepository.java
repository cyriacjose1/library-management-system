package repository;

import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static final String FILE_PATH = "data/books.dat";
    private List<Book> books;

    public BookRepository() {
        books = loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book findByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public List<Book> searchByGenre(String genre) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public boolean removeBook(String isbn) {
        Book book = findByISBN(isbn);

        if (book != null) {
            books.remove(book);
            saveBooks();
            return true;
        }

        return false;
    }

    public void updateBooks() {
        saveBooks();
    }

    private void saveBooks() {

        try {

            File file = new File(FILE_PATH);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(file));

            out.writeObject(books);
            out.close();

        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Book> loadBooks() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));

            List<Book> loadedBooks =
                    (List<Book>) in.readObject();

            in.close();

            return loadedBooks;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading books: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}