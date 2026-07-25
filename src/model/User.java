package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String name;
    private String contactInfo;
    private List<String> borrowedBooks;

    public User() {
        borrowedBooks = new ArrayList<>();
    }

    public User(String userId, String name, String contactInfo) {
        this.userId = userId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setBorrowedBooks(List<String> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void borrowBook(String isbn) {
        if (!borrowedBooks.contains(isbn)) {
            borrowedBooks.add(isbn);
        }
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }

    @Override
    public String toString() {
        return "\n----------------------------" +
               "\nUser ID       : " + userId +
               "\nName          : " + name +
               "\nContact Info  : " + contactInfo +
               "\nBorrowed Books: " +
               (borrowedBooks.isEmpty() ? "None" : borrowedBooks) +
               "\n----------------------------";
    }
}