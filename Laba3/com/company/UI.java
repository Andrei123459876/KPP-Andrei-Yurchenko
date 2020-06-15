/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import lab3backend.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class UI extends JFrame{
    private JButton buttonLogin = new JButton("Войти");
    private JButton buttonCreateMsg = new JButton("Написать сообщение");
    private JButton buttonDeleteMsg = new JButton("Удалить сообщение");
    private JButton buttonAddUser = new JButton("Добавить пользователя");
    private JButton buttonDeleteUser = new JButton("Удалить пользователя");
    private JLabel lablePlaceHolder = new JLabel("");
    private JLabel lableLogin = new JLabel("");
    private JLabel lableText = new JLabel("Forum: ");
    //private JLabel lableRes = new JLabel("Результат: ");
    Forum forum;
    RegisteredUser user;
    Administrator admin;
    RegisteredUser curr;
    DatabaseLogins dbase;

    public UI(){
        super("Вариант 28");
        this.setBounds(100, 100, 640, 360);
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Локализация кнопок
        UIManager.put("OptionPane.yesButtonText"   , "user"    );
        UIManager.put("OptionPane.noButtonText"    , "admin"   );
        UIManager.put("OptionPane.cancelButtonText", "Отмена");

        forum = new Forum();
        user = new RegisteredUser();
        admin = new Administrator();
        dbase = new DatabaseLogins();
        
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.yesButtonText"   , "Да"    );
                UIManager.put("OptionPane.noButtonText"    , "Нет"   );
                int confirm = JOptionPane.showOptionDialog(
                     null, "Вы уверены, что хотите выйти?", 
                     "Подтверждение действия", JOptionPane.YES_NO_OPTION, 
                     JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    dbase.saveBeforeClosing();
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);
        
        lableLogin.setHorizontalAlignment(JLabel.CENTER);
        lableText.setVerticalAlignment(JLabel.TOP);
        //JScrollPane scroller = new JScrollPane(lableText);
        
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 3, 2, 2));
        
        container.add(lableLogin);
        container.add(lablePlaceHolder);
        container.add(lableText);
        
        JScrollPane scroller = new JScrollPane(lableText);
        container.add(scroller);
        
        ButtonGroup group = new ButtonGroup();
        
        buttonLogin.addActionListener(new ButtonLoginEventListener());
        buttonCreateMsg.addActionListener(new ButtonCreateMsgEventListener());
        buttonDeleteMsg.addActionListener(new ButtonDeleteMsgEventListener());
        buttonAddUser.addActionListener(new ButtonAddUserEventListener());
        buttonDeleteUser.addActionListener(new ButtonDeleteUserEventListener());
        
        group.add(buttonLogin);
        group.add(buttonCreateMsg);
        group.add(buttonDeleteMsg);
        
        container.add(buttonLogin);
        container.add(buttonCreateMsg);
        buttonDeleteMsg.setVisible(false);
        container.add(buttonDeleteMsg);
        buttonAddUser.setVisible(false);
        container.add(buttonAddUser);
        buttonDeleteUser.setVisible(false);
        container.add(buttonDeleteUser);
        

    }

    private void setButtonsVisible(boolean b){
        buttonDeleteMsg.setVisible(b);
        buttonAddUser.setVisible(b);
        buttonDeleteUser.setVisible(b);
    }
    
    
    class ButtonLoginEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                // Диалоговое окно ввода данных : родитель, HTML сообщение
                String result = JOptionPane.showInputDialog(UI.this, "Добро пожаловать, введите логин:");
                if (result != null){                                        
                    Integer type = dbase.loginRequest(result);
                    if (type != null){
                        if (type == 1){                            
                            curr = user;
                            user.changeLogin(result);
                            setButtonsVisible(false);
                        }
                        else{
                            curr = admin;
                            admin.changeLogin(result);
                            setButtonsVisible(true);
                        }

                        lableLogin.setText(result);
                    }
                }                                       
            }
            catch (Exception ex){
                System.err.println("Ex at: \"ButtonLoginEventListener\"");
            }
        }

    }
    
    class ButtonCreateMsgEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
               String result = JOptionPane.showInputDialog(UI.this, "Введите сообщение:");
               String img = null;
               if (result != null)
                    img = JOptionPane.showInputDialog(UI.this, "Введите код изображения:");               
               if (result != null && img != null){
                   if(result.equals("") && img.equals(""))
                       return;
                   StringBuilder strBuff = new StringBuilder();
                   strBuff.append(curr.getLogin());
                   strBuff.append(" написал:");
                   strBuff.append("<br>");
                   strBuff.append(result);
                   strBuff.append("<br>");                                   
                   result = strBuff.toString();
                   Message tmp = curr.createMessage(result, img);
                   forum.addMessage(tmp);
                   lableText.setText(forum.getAllMsg());
               }
            }
            catch (Exception ex){
                System.err.println("Ex at: \"ButtonCreateMsgEventListener\"");
            }
        }

    }
    
    class ButtonDeleteMsgEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
               String result = JOptionPane.showInputDialog(UI.this, "Введите номер сообщения, которое хотите удалить:");
               Integer num = Integer.parseInt(result);
               admin.deleteMessage(forum, num);
               lableText.setText(forum.getAllMsg());
            }
            catch (Exception ex){
                System.err.println("Ex at: \"ButtonDeleteMsgEventListener\"");
            }
        }

    }
    
    class ButtonAddUserEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                // Диалоговое окно ввода данных : родитель, HTML сообщение
                String result = JOptionPane.showInputDialog(UI.this, "Введите добавляемый логин:", "Добавление");
                if (result != null){
                    int choice = JOptionPane.showConfirmDialog(UI.this, "Кого вы хотите добавить?", "Вход", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION){
                        admin.addUser(dbase, result, 1);//dbase.addRequest(result, 1);  
                        if (curr.getLogin().equals(result))
                            setButtonsVisible(false);
                    }
                    else if (choice == JOptionPane.NO_OPTION){
                        admin.addUser(dbase, result, 0);//dbase.addRequest(result, 0);                        
                    }                    
                }                                     
            }
            catch (Exception ex){
                System.err.println("Ex at: \"ButtonLoginEventListener\"");
            }
        }

    }
    
    class ButtonDeleteUserEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                // Диалоговое окно ввода данных : родитель, HTML сообщение
                String result = JOptionPane.showInputDialog(UI.this, "Введите удаляемый логин:");
                if (result != null){
                    if (result.equals("admin"))
                        JOptionPane.showMessageDialog(UI.this, "Нельзя удалить этого пользователя");
                     else if (result.equals(curr.getLogin()))
                        JOptionPane.showMessageDialog(UI.this, "Нельзя удалить самого себя");
                     else{
                        Integer tmp = admin.deleteUser(dbase, result);//dbase.deleteRequest(result);
                        if (tmp == null)
                            JOptionPane.showMessageDialog(UI.this, "Нет такого пользователя");                        
                     }
                          
                }                                     
            }
            catch (Exception ex){
                System.err.println("Ex at: \"ButtonLoginEventListener\"");
            }
        }

    }    
    
}
