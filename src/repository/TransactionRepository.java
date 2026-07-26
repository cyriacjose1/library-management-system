package repository;

import model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private static final String FILE_PATH = "data/transactions.dat";
    private List<Transaction> transactions;

    public TransactionRepository() {
        transactions = loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction findByTransactionId(String transactionId) {

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equalsIgnoreCase(transactionId)) {
                return transaction;
            }
        }

        return null;
    }

    public Transaction findActiveTransactionByBook(String isbn) {

        for (Transaction transaction : transactions) {

            if (transaction.getBook().getIsbn().equalsIgnoreCase(isbn)
                    && !transaction.isReturned()) {

                return transaction;
            }
        }

        return null;
    }

    public List<Transaction> findByUserId(String userId) {

        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getUser().getUserId().equalsIgnoreCase(userId)) {
                result.add(transaction);
            }

        }

        return result;
    }

    public void updateTransactions() {
        saveTransactions();
    }

    private void saveTransactions() {

        try {

            File file = new File(FILE_PATH);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(file));

            out.writeObject(transactions);

            out.close();

        } catch (IOException e) {

            System.out.println("Error saving transactions: " + e.getMessage());

        }

    }

    @SuppressWarnings("unchecked")
    private List<Transaction> loadTransactions() {

        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));

            List<Transaction> loadedTransactions =
                    (List<Transaction>) in.readObject();

            in.close();

            return loadedTransactions;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading transactions: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}