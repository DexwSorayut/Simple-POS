package User;

public class UserService {
    public UserService() {
        UserRepository reuser = new UserRepository();
        AuthService auth = new AuthService(reuser);

        reuser.loadFromFile();

        User newUser[] = {
            new User( "SRYTH", "123789") ,
            new User( "Tle", "111111") ,
            new User( "Nut", "987654")
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

        auth.changePassword("140001", "123789", "123456");
        auth.login("140001", "123456");
        auth.logout();
    }
}