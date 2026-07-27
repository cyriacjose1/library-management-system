package service;

import model.User;
import repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String userId, String name, String contactInfo) {

        if (userRepository.findById(userId) != null) {
            System.out.println("User ID already exists.");
            return;
        }

        User user = new User(userId, name, contactInfo);
        userRepository.addUser(user);

        System.out.println("User registered successfully.");
    }

    public void viewAllUsers() {

        List<User> users = userRepository.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            System.out.println(user);
        }
    }

    public User searchUserById(String userId) {

        User user = userRepository.findById(userId);

        if (user == null) {
            System.out.println("User not found.");
        }

        return user;
    }

    public void searchUserByName(String name) {

        List<User> users = userRepository.searchByName(name);

        if (users.isEmpty()) {
            System.out.println("No matching users found.");
            return;
        }

        users.forEach(System.out::println);
    }

    public void removeUser(String userId) {

        if (userRepository.removeUser(userId)) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void updateUser(User user) {
        userRepository.updateUsers();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
