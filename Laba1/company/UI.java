package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class UI extends JFrame{
    private JButton button = new JButton("Получить");
    private JTextField input = new JTextField("", 5);
    private JLabel lableInp = new JLabel("Количество блужданий:");
    private JLabel lableRes = new JLabel("Результат: ");

    public UI(){
        super("Вариант 28");
        this.setBounds(100, 100, 350, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(lableInp);
        container.add(input);
        container.add(lableRes);

        //ButtonGroup group = new ButtonGroup();
        //group.add(button);

        button.addActionListener(new ButtonEventListener());
        container.add(button);

    }

    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "";
            message += input.getText();
            Integer amout = 0;
            try{
                amout = Integer.parseInt(message);

                if (amout <= 0){
                    JOptionPane.showMessageDialog(null, "Не то число", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }

                final Random random = new Random();
                amout = random.nextInt(amout+1);

                message = "Результат: ";
                message += amout.toString();

                lableRes.setText(message);
            }
            catch (Exception ex){}
        }

    }
}
