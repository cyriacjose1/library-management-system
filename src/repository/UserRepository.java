package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String FILE_PATH = "data/users.dat";
    private List<User> users;

    public UserRepository() {
        users = loadUsers();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User findById(String userId) {

        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            }
        }

        return null;
    }

    public List<User> searchByName(String name) {

        List<User> result = new ArrayList<>();

        for (User user : users) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(user);
            }
        }

        return result;
    }

    public boolean removeUser(String userId) {

        User user = findById(userId);

        if (user != null) {
            users.remove(user);
            saveUsers();
            return true;
        }

        return false;
    }

    public void updateUsers() {
        saveUsers();
    }

    private void saveUsers() {

        try {

            File file = new File(FILE_PATH);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(file));

            out.writeObject(users);

            out.close();

        } catch (IOException e) {

            System.out.println("Error saving users: " + e.getMessage());

        }
    }

    @SuppressWarnings("unchecked")
    private List<User> loadUsers() {

        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));

            List<User> loadedUsers =
                    (List<User>) in.readObject();

            in.close();

            return loadedUsers;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading users: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}