package com.company;

public class RegisteredUser extends User {
    private String login;
    
    public RegisteredUser(){
        login = "NA";
    }
    
    public void enterSystem(String login){        
        this.login = login;
    }
    
    public void changeLogin(String login){
        if (!this.login.equals(login) ) this.login=login;
    }
    
    public boolean logCheck(String login){
       if (login.equals("NA"))
           return false;
       if (this.login.equals(login))
           return false;
       return true;
    }
    
    public String getLogin(){
        return this.login;
    }
}

