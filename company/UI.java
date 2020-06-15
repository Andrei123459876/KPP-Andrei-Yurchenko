package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class UI extends JFrame{
    private JButton buttonLogin = new JButton("Войти");
    private JButton buttonCreateMsg = new JButton("Написать сообщение");
    private JButton buttonDeleteMsg = new JButton("Удалить сообщение");
    private JLabel lablePlaceHolder = new JLabel("");
    private JLabel lableLogin = new JLabel("");
    private JLabel lableText = new JLabel("Чат: ");
    //private JLabel lableRes = new JLabel("Результат: ");
    Forum forum;
    RegisteredUser user;
    Administrator admin;
    RegisteredUser curr;

    public UI(){
        super("Вариант 28");
        this.setBounds(100, 100, 640, 360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Локализация кнопок
        UIManager.put("OptionPane.yesButtonText"   , "user"    );
        UIManager.put("OptionPane.noButtonText"    , "admin"   );
        UIManager.put("OptionPane.cancelButtonText", "Отмена");

        forum = new Forum();
        user = new RegisteredUser();
        admin = new Administrator();
        
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
        
        //ButtonGroup group = new ButtonGroup();
        //group.add(button);

        buttonLogin.addActionListener(new ButtonLoginEventListener());
        buttonCreateMsg.addActionListener(new ButtonCreateMsgEventListener());
        buttonDeleteMsg.addActionListener(new ButtonDeleteMsgEventListener());
                
        container.add(buttonLogin);
        container.add(buttonCreateMsg);
        buttonDeleteMsg.setVisible(false);
        container.add(buttonDeleteMsg);
        

    }

    class ButtonLoginEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                // Диалоговое окно ввода данных : родитель, HTML сообщение
                String result = JOptionPane.showInputDialog(UI.this, "Добро пожаловать, введите логин:");
                if (result != null){
                    int choice = JOptionPane.showConfirmDialog(UI.this, "Как вы хотите войти?", "Вход", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION){
                        curr = user;
                        user.changeLogin(result);
                        buttonDeleteMsg.setVisible(false);
                        lableLogin.setText(user.getLogin());
                    }
                    else if (choice == JOptionPane.NO_OPTION){
                        curr = admin;
                        admin.changeLogin(result);
                        lableLogin.setText(admin.getLogin());
                        buttonDeleteMsg.setVisible(true);
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
}
