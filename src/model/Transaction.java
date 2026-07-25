package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionId;
    private Book book;
    private User user;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Transaction() {
    }

    public Transaction(String transactionId, Book book, User user, LocalDate borrowDate) {
        this.transactionId = transactionId;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = null; 
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "\n==============================" +
               "\nTransaction ID : " + transactionId +
               "\nBook           : " + book.getTitle() +
               "\nISBN           : " + book.getIsbn() +
               "\nUser           : " + user.getName() +
               "\nUser ID        : " + user.getUserId() +
               "\nBorrow Date    : " + borrowDate +
               "\nReturn Date    : " +
               (returnDate == null ? "Not Returned" : returnDate) +
               "\nStatus         : " +
               (isReturned() ? "Returned" : "Borrowed") +
               "\n==============================";
    }
}