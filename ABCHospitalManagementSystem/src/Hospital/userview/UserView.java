package hospital.userview;

import hospital.models.User;
import java.util.List;

public class UserView {


public void displayUser(User user) {

    if (user == null) {
        System.out.println("User not found.");
        return;
    }

    System.out.println("\n========== USER DETAILS ==========");
    System.out.println("User ID: " + user.getId());
    System.out.println("Username: " + user.getUsername());
    System.out.println("Role: " + user.getRole());

    if (user.getStaff() != null) {
        System.out.println("Staff ID: " + user.getStaff().getStaffId());
    } else {
        System.out.println("Staff ID: Not assigned");
    }

    System.out.println("Status: " + (user.isActive() ? "Active" : "Inactive"));
    System.out.println("==================================");
}

public void displayUsers(List<User> users) {

    if (users == null || users.isEmpty()) {
        System.out.println("No users found.");
        return;
    }

    System.out.println("\n================ USER LIST ================");
    System.out.printf("%-8s %-20s %-25s %-10s %-12s%n",
            "User ID", "Username", "Role", "Staff ID", "Status");
    System.out.println("---------------------------------------------------------------");

    for (User user : users) {

        String staffId = user.getStaff() != null
                ? String.valueOf(user.getStaff().getStaffId())
                : "N/A";

        String status = user.isActive() ? "Active" : "Inactive";

        System.out.printf("%-8d %-20s %-25s %-10s %-12s%n",
                user.getId(),
                user.getUsername(),
                user.getRole(),
                staffId,
                status);
    }

    System.out.println("============================================");
}

public void displayMessage(String message) {
    System.out.println(message);
}

public void displayError(String message) {
    System.out.println("Error: " + message);
}

}
