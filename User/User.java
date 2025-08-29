package User;

public  class User {
    private String UserID;
    private String UserName;
    private String Password;

    public void checkRep(){
        if(UserID == null || UserName == null || Password == null){
            throw new RuntimeException("User data not be null");
        }
    }

    public User(String UserID , String UserName , String Password){
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
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

    public String toString(){
        return UserID + "," + UserName + "," + Password;
    }
}
