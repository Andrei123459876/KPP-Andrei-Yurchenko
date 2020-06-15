package com.laba4backend;

import java.util.*;

public class StringCompairing {
    String a;
    String b;
    
    public StringCompairing(String a,String b){
        this.a = a;
        this.b = b;
    }
    
    public ArrayList<Integer> result(){
        if(a.length() != b.length())
            return null;
        
        ArrayList<Integer> res = new ArrayList<>();
        List<Thread> list = new ArrayList<>();
        
        for(int i = 0; i < a.length(); i++){
            res.add(0);
        }
        
        for(int i = 0; i < a.length(); i++){
            Thread thread = new Thread(new CharacterByCharacterComparison(a.charAt(i), b.charAt(i), res, i));
            thread.start();
            list.add(thread);
        }
        
        try{
            for (Thread thread : list) {
                thread.join();
            }
        }
        catch(InterruptedException ex){System.err.println("InterruptedException");}
        
        return res;
    }
}
