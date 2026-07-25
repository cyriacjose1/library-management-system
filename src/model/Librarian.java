package model;

public class Librarian extends User {

    private static final long serialVersionUID = 1L;

    private String employeeId;

    public Librarian() {
        super();
    }

    public Librarian(String userId, String name, String contactInfo, String employeeId) {
        super(userId, name, contactInfo);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void addBook() {
        System.out.println("Librarian is authorized to add books.");
    }

    public void removeBook() {
        System.out.println("Librarian is authorized to remove books.");
    }

    public void manageUsers() {
        System.out.println("Librarian is authorized to manage users.");
    }

    @Override
    public String toString() {
        return "\n========== Librarian ==========" +
               "\nEmployee ID : " + employeeId +
               "\nUser ID     : " + getUserId() +
               "\nName        : " + getName() +
               "\nContact     : " + getContactInfo() +
               "\nBorrowed Books : " +
               (getBorrowedBooks().isEmpty() ? "None" : getBorrowedBooks()) +
               "\n===============================";
    }
}