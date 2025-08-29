package User;

public class AuthService {
    private UserRepository userRepo;
    private User currentUser;

    public AuthService(UserRepository repo) {
        this.userRepo = repo;
    }

    public boolean login(String username, String password) {
        for (User u : userRepo.getAllUsers()) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                System.out.println("Login success. Welcome " + u.getUserName());
                return true;
            }
        }
        System.out.println("Login failed.");
        return false;
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUserName() + " logged out.");
            currentUser = null;
        }
    }

    public boolean changePassword(String userID, String oldPwd, String newPwd) {
        User u = userRepo.getUserByID(userID);
        if (u != null && u.getPassword().equals(oldPwd)) {
            u.setPassword(newPwd);
            userRepo.saveToFile();
            System.out.println("Password changed successfully.");
            return true;
        }
        System.out.println("Password change failed.");
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

