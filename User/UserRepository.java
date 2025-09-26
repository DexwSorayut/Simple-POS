package User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepository {
    private ArrayList<User> users = new ArrayList<>();
    
    private void checkRep() {
        if (users == null){
            throw new RuntimeException("Error: Users is null");
        }
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).equals(users.get(j))) {
                    throw new RuntimeException("Error: Duplicate user detected.");
                }
            }
        }
    }

    public UserRepository() {
        checkRep();
    }
    
    public void addUser(User user) {
        int maxID = 140000;
            for (User u : users) {
                try {
                    int idNum = Integer.parseInt(u.getUserID());
                    if (idNum > maxID) maxID = idNum;
                } catch (NumberFormatException e) {}
            }

        if(user.getUserID()==null){
            String newID = String.format("%03d", maxID) ;
            user.setUserID(newID);
        }
        for (User u : users) {
            if (u.equals(user)) {
                System.out.println("User already exists: " + user.getUserName());
                return;
            }
        }
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void removeUser(String userID) {
        for (User u : users) {
            if(u.getUserID().contains(userID)){
                users.remove(users);
            }
        }
    }

    public User getUserByID(String userID) {
        for (User u : users) {
            if (u.getUserID().equals(userID)){
                return u;
            }
        }
        return null;
    }

    // บันทึกผู้ใช้เป็น CSV
    // ไม่ได้ใช้
    public void saveToFile() {
        File F = null;
        FileWriter FW = null;
        BufferedWriter BW = null;
        
        try {
            F = new File("./File & Image/UserCatalog.csv");
            FW = new FileWriter(F);
            BW = new BufferedWriter(FW);
            BW.write("UserID , Username , Password\n");

           for (User u : getAllUsers()) {
                BW.write(u.getUserID() + " , " + u.getUserName() + " , " + u.getPassword() + "\n");         
            }
            System.out.println("Saved File user.");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
        finally{
            try {
                BW.close(); FW.close(); 
            } catch (Exception e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    // โหลดผู้ใช้จากไฟล์ CSV
    // ไม่ได้ใช้
    public void loadFromFile() {
        users.clear(); // กันข้อมูลซ้ำซ้อน
        File F = new File("./File & Image/UserCatalog.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(F))) {
            String line = br.readLine(); // อ่าน header ทิ้ง
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String ID = data[0].trim();
                    String Name = data[1].trim();
                    String Password = data[2].trim();

                    User u = new User(Name, Password);
                    u.setUserID(ID);        // ✅ เซ็ต ID ที่อ่านมาจากไฟล์
                    users.add(u);
                }
            }
            System.out.println("Loaded User File.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
