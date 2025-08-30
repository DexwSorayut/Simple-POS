package User;

public class UserService {
    public UserService() {
        UserRepository reuser = new UserRepository();
        AuthService auth = new AuthService(reuser);

        reuser.loadFromFile();

        User newUser[] = {
            new User("999001", "SRYTH", "123789") ,
            new User("999002", "Tle", "111111") ,
            new User("999003", "Nut", "987654")
        } ;

        for (User user : newUser) {
            try {
                reuser.addUser(user); // เพิ่มเฉพาะ user ที่ผ่าน checkRep
            } catch (RuntimeException e) {
                System.out.println("Cannot add user " + user.getUserID() + ": " + e.getMessage());
            }
        }
        
        for(User u : reuser.getAllUsers()){
            System.out.println(u.getUserID() + " - " + u.getUserName() + " : " + u.getPassword());
        }

        reuser.saveToFile();

        auth.changePassword("999001", "123789", "123456");
        auth.logout();
    }
}