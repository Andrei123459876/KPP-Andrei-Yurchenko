package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import com.laba4backend.*;

public class UI extends JFrame{
    private JButton button = new JButton("Сравнить");
    private JTextField word1 = new JTextField("Слово1", 5);
    private JTextField word2 = new JTextField("Слово2", 5);
    private JLabel lableRes = new JLabel("Результат: ");

    

    public UI(){
        super("Вариант 28");
        this.setBounds(100, 100, 640, 360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                

       
        
       
        
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 2, 2, 2));
        container.add(word1);
        container.add(word2);
        container.add(lableRes);
        
        
        


        button.addActionListener(new ButtonEventListener());

                
        container.add(button);       
        

    }

    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String a = word1.getText();
                String b = word2.getText();
                StringCompairing task = new StringCompairing(a, b);
                ArrayList<Integer> res;
                res = task.result();
                if (res == null){
                    JOptionPane.showMessageDialog(UI.this, "Слова разной длинны");
                }
                else{
                    StringBuilder str = new StringBuilder();
                    int k = 0;
                    Integer j = 1;
                    for(Integer i: res){
                        if (i.equals(1)){
                            if (k == 0){
                                 str.append("<html> Не совподают символы, имеющие номера: <br> ");
                                 str.append(j.toString());
                                 k += 1;
                             } else{
                                 k += 1;
                                 str.append(", " + j.toString());
                             }
                        }
                        j += 1;
                    }
                    if (k == 0)
                        lableRes.setText("Строки совпали");
                    else {
                        str.append("  </html>");
                        lableRes.setText(str.toString());
                    }
                    
                }
            }
            catch (Exception ex){
                System.err.println("Ex at: \"ButtonLoginEventListener\"");
            }
        }

    }
    
}
