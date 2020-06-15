/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3backend;

/**
 *
 * @author Andrei
 */
public class User {
    public Message createMessage(String text, String image)
    {
       Message msg = new Message(text, image);
       return msg;
    }
}

