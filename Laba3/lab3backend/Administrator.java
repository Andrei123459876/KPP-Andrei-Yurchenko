package lab3backend;

public class Administrator extends RegisteredUser {
    public void deleteMessage(Forum F, int number){
        F.deleteMessage(number);
    }
    
    public Integer deleteUser(DatabaseLogins db, String nick){
        return db.deleteRequest(nick);
    }
    
    public Integer addUser(DatabaseLogins db, String nick, Integer type){
        return db.addRequest(nick, type);
    }
}
