package com.company;

public class Administrator extends RegisteredUser {
    void deleteMessage(Forum F, int number){
        F.deleteMessage(number);
    }
}
