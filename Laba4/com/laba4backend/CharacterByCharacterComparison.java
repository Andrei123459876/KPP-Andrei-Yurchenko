package com.laba4backend;

import java.util.ArrayList;
import java.util.*;

public class CharacterByCharacterComparison extends Thread{
   String a;
   String b;
   List<Integer> res;
   int i;
   
   public CharacterByCharacterComparison(char c, char d, ArrayList<Integer> p, int j){
       a ="" + c;
       b ="" + d;
       i = j;
       res = p;
       start();
   }
   
   @Override
   public void run(){
        synchronized (res){
            if(!a.equals(b))
                res.set(i, 1);            
        }
   }
        
}
