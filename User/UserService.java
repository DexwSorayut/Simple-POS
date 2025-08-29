package User;

public class UserService {
    public UserService() {
        UserRepository reuser = new UserRepository();
        AuthService auth = new AuthService(reuser);

        reuser.loadFromFile();

        try {
            reuser.addUser(new User("001", "SRYTH", "STTT222"));
            reuser.addUser(new User("002", "NUT", "NuT555"));
            reuser.addUser(new User("003", "Tle", "Tleza001"));
            reuser.addUser(new User("001", "Dewwy", "Dewwww"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for(User u : reuser.getAllUsers()){
            System.out.println(u.getUserID() + " - " + u.getUserName() + " : " + u.getPassword());
        }

        reuser.saveToFile();

        auth.changePassword("001", "STTT222", "STTTH123");
        auth.logout();
    }
}
