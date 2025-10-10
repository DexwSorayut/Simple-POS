package User;

public  class User {
    private String UserID;
    private String UserName;
    private String Password;

    public void checkRep(){
        if( UserName == null || Password == null){
            throw new RuntimeException("User data not be null");
        }
        
        if (Password.length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters long");
        }

        // ตรวจสอบว่ารหัสทั้งหมดเป็นตัวเลข
        for (int i = 0; i < Password.length(); i++) {
            if (!Character.isDigit(Password.charAt(i))) {
                throw new RuntimeException("Password must contain only digits");
            }
        }
    }

    public User(String UserName , String Password){
        this.UserName = UserName;
        this.Password = Password;
        checkRep();
    }

    User(String userID, String userName, String password) {
        this.UserID = userID;
        this.UserName = userName;
        this.Password = password;
        checkRep();
    }

    public String getUserID(){
        return UserID;
    }

    public String getUserName(){
        return UserName;
    }
    
    public String getPassword(){
        return Password;
    }

    //set password ใหม่
    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setUserID(String ID){
        this.UserID = ID;
    }

    public String toString(){
        return UserID + "," + UserName + "," + Password;
    }
}
