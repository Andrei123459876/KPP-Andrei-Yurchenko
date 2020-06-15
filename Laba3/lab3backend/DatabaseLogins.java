/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3backend;

import java.io.*;
import java.util.*;

/**
 *
 * @author Andrei
 */
public class DatabaseLogins {
    Map<String, Integer> databaseLogins;
    
    public DatabaseLogins(){
        databaseLogins = new HashMap<>();
        
        //<INPUT>
        try{ 
            BufferedReader scan = new BufferedReader(new FileReader(new File("DatabaseLogins.txt")));
            
            String line;            
            String[] parts;
            
            String nick;
            Integer type;
            
            while((line = scan.readLine()) != null){
                parts = line.split(" ");
                
                type = Integer.parseInt(parts[0]);
                nick = parts[1];
                
                databaseLogins.put(nick, type);
            }
        }
        catch(IOException ex) {
            System.err.println("Ex at: \"Database Input (constructor)\"");
        }
    }
    
    private void addUser(String nick, Integer type){
        databaseLogins.put(nick, type);
    }
    
    private void deleteUser(String nick){
        
        databaseLogins.remove(nick);
       
    }
    
    public Integer loginRequest(String nick){
        Integer type = databaseLogins.get(nick);
        return type;
    }
    
    public Integer addRequest(String nick, Integer type){        
        Integer tmp = databaseLogins.get(nick);
        if(tmp == null){
            addUser(nick, type);
            return type;
        }
        else if(tmp.equals(type)){
            return type;
        }
        else{
            deleteUser(nick);
            addUser(nick, type);
            return type;
        }
    }
    
    public Integer deleteRequest(String nick){
        Integer res = databaseLogins.get(nick);
        deleteUser(nick);
        return res;
    }
    
    public void saveBeforeClosing(){
        try{
            FileWriter file = new FileWriter("DatabaseLogins.txt");
            Iterator it = databaseLogins.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                file.write(pair.getValue().toString() + " " + pair.getKey() + "\n");
            }
            file.flush();
        }
        catch (IOException ex) {
            System.err.println("Ex at: \"Database Output (destructor)\"");
        }
    }
}
