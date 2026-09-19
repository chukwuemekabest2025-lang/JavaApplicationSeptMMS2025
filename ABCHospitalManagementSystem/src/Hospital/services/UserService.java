package hospital.services;

import hospital.dao.UserDAO;
import hospital.models.User;
import hospital.models.StaffRole;

import java.util.List;

public class UserService {


private final UserDAO userDAO = new UserDAO();

public void addUser(User user) {

    if (user == null) {
        throw new IllegalArgumentException("User cannot be null.");
    }

    if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
        throw new IllegalArgumentException("Username cannot be empty.");
    }

    if (user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty()) {
        throw new IllegalArgumentException("Password cannot be empty.");
    }

    if (user.getRole() == null) {
        throw new IllegalArgumentException("User role is required.");
    }

    if (user.getStaff() != null && user.getStaff().getStaffId() <= 0) {
        throw new IllegalArgumentException("Invalid Staff ID.");
    }

    userDAO.addUser(user);
}

public List<User> getAllUsers() {
    return userDAO.findAllUsers();
}

public User getUserById(int userId) {

    if (userId <= 0) {
        throw new IllegalArgumentException("Invalid User ID.");
    }

    return userDAO.findUserById(userId);
}

public User getUserByUsername(String username) {

    if (username == null || username.trim().isEmpty()) {
        throw new IllegalArgumentException("Username cannot be empty.");
    }

    return userDAO.findUserByUsername(username);
}

public void updateUser(User user) {

    if (user == null) {
        throw new IllegalArgumentException("User cannot be null.");
    }

    if (user.getId() <= 0) {
        throw new IllegalArgumentException("Invalid User ID.");
    }

    if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
        throw new IllegalArgumentException("Username cannot be empty.");
    }

    if (user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty()) {
        throw new IllegalArgumentException("Password cannot be empty.");
    }

    if (user.getRole() == null) {
        throw new IllegalArgumentException("User role is required.");
    }

    if (user.getStaff() != null && user.getStaff().getStaffId() <= 0) {
        throw new IllegalArgumentException("Invalid Staff ID.");
    }

    userDAO.update(user);
}

public void deleteUser(int userId) {

    if (userId <= 0) {
        throw new IllegalArgumentException("Invalid User ID.");
    }

    userDAO.delete(userId);
}

public void updateUserStatus(int userId, boolean active) {

    if (userId <= 0) {
        throw new IllegalArgumentException("Invalid User ID.");
    }

    userDAO.updateStatus(userId, active);
}
}
