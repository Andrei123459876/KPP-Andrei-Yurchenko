package com.company;

public class User {
    public Message createMessage(String text, String image)
    {
       Message msg = new Message(text, image);
       return msg;
    }
}
