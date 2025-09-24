package User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class UserRepository {
    private ArrayList<User> users = new ArrayList<>();
    private int Nid = 140001;

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
        if(user.getUserID()==null){
            String newID = String.format("%03d", Nid++) ;
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

    public ArrayList<User> getAllUsers() {
        ArrayList<User> set = new ArrayList<>();
        HashSet<String> SeeUser= new HashSet<>();

        for (User u : users) {
            String keyID = "ID:" + u.getUserID();
            String keyName = "NAME:" + u.getUserName().toLowerCase();

            if (SeeUser.contains(keyID) || SeeUser.contains(keyName) ) {
                continue;
            }
        
            set.add(u);
            SeeUser.add(keyID); 
            SeeUser.add(keyName);
        }
        return set;
    }

    // บันทึกผู้ใช้เป็น CSV
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

    //โหลดผู้ใช้จากไฟล์ CSV
    public void loadFromFile() {
        File F = null;
        FileReader FR = null;
        BufferedReader BR = null;
        try {
            F = new File("./File & Image/UserCatalog.csv");
            FR = new FileReader(F);
            BR = new BufferedReader(FR);
            String line = BR.readLine();
            while ((line = BR.readLine()) != null) {
                String Data[] = line.split(",");
                if(Data.length == 4){
                    String ID = Data[0];
                    String Name = Data[1];
                    String Password = Data[2];
                    users.add(new User( Name, Password));
                }
            }
            System.out.println("Loaded User File.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
