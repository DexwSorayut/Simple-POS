package User;

import java.io.*;

public class AuthService {
    private UserRepository userRepo;
    private User currentUser;

    public AuthService(UserRepository reuser) {
        this.userRepo = reuser;
    }

    public boolean login(String userid, String password) {

        System.out.println("Input userid: [" + userid + "] , password: [" + password + "]");
        File F = new File("./File & Image/UserCatalog.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(F))) {
            String line = br.readLine(); // ข้าม header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String ID = data[0].trim();
                    String Name = data[1].trim();
                    String Password = data[2].trim();
                    if(ID.equals(userid.trim()) && Password.equals(password.trim())) {
                        System.out.println("Login success. Welcome " + Name);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        System.out.println("Login failed.");
        return false;

    }

    public boolean register(String username, String password , String Cpassword) {
        // 1. ตรวจสอบ password ซ้ำ
        if (!password.equals(Cpassword)) {
            System.out.println("Passwords do not match!");
            return false;
        }

        // 2. โหลดข้อมูลเก่าจากไฟล์
        userRepo.getAllUsers().clear();   // เคลียร์ก่อน กันข้อมูลซ้ำซ้อน
        userRepo.loadFromFile();

        
        // 3. ตรวจสอบว่ามี username อยู่แล้วหรือยัง
        for (User u : userRepo.getAllUsers()) {
            if (u.getUserName().equals(username)) {
                System.out.println("User already exists!");
                return false;
            }
        }

        // 4. เพิ่ม users
        int maxID = 140000; 
        for (User u : userRepo.getAllUsers()) {
            try {
                int idNum = Integer.parseInt(u.getUserID());
                if (idNum > maxID) maxID = idNum;
            } catch (NumberFormatException e) {}
        }

        User newUser = new User(username, password);
        newUser.setUserID(String.format("%06d", maxID + 1));
        userRepo.getAllUsers().add(newUser);

        // 5. เขียน users ทั้งหมดกลับลงไฟล์
        File f = new File("./File & Image/UserCatalog.csv");
        try (PrintWriter out = new PrintWriter(new FileWriter(f))) {
            out.println("ID,Name,Password"); // header
            for (User u : userRepo.getAllUsers()) {
                out.println(u.getUserID() + "," + u.getUserName() + "," + u.getPassword());
            }
            System.out.println("Register success: " + username);
            return true;
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUserName() + " logged out.");
            currentUser = null;
        }
    }

    public boolean changePassword(String userID, String oldPwd, String newPwd) {
        User u = userRepo.getUserByID(userID);
        if (u != null && u.getPassword().equals(oldPwd)){
            if (u.getUserID().equals(userID)) {
                u.setPassword(newPwd);
                userRepo.saveToFile();
                System.out.println("Password changed successfully.");
                return true;
            }
            return true;
        }
        System.out.println("Password change failed.");
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

