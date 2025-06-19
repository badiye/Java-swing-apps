package com.example.com.example;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JPanel panel;
    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JTextField textField;

    class RoundedButton extends JButton {
        private int corner;
        public RoundedButton(String label, int rad) {
            super(label);
            this.corner = rad;
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public Calculator() {
        setTitle("Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.LIGHT_GRAY);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(panel, BorderLayout.CENTER);
        add(container, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setPreferredSize(new Dimension(350, 100));
        textField.setBackground(Color.lightGray);
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));
        panel.add(textField, gbc);

        addButton = new RoundedButton("+", 50);
        subButton = new RoundedButton("-", 50);
        mulButton = new RoundedButton("*", 50);
        divButton = new RoundedButton("/", 50);
        decButton = new RoundedButton(".", 50);
        equButton = new RoundedButton("=", 50);
        delButton = new RoundedButton("X", 50);
        clrButton = new RoundedButton("C", 50);

        JButton[] operationButtons = {
            addButton, subButton, mulButton, divButton};
        for (JButton button : operationButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
        }

        JButton[] specialButtons = {
            decButton, equButton, delButton, clrButton};
        for (JButton button : specialButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            button.setForeground(Color.WHITE);
        }

        clrButton.setBackground(Color.GRAY);
        delButton.setBackground(Color.GRAY);
        Color co = new Color(0, 0, 128); // blue color
        equButton.setBackground(co);
        decButton.setBackground(Color.BLACK);

        for (int i = 1; i < 10; i++) {
            numberButtons[i] = new RoundedButton(String.valueOf(i), 80);
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 24));
            numberButtons[i].setBackground(Color.BLACK);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].addActionListener(this);
        }
        numberButtons[0] = new RoundedButton("0", 50);
        numberButtons[0].setFont(new Font("Arial", Font.PLAIN, 24));
        numberButtons[0].setBackground(Color.BLACK);
        numberButtons[0].setForeground(Color.WHITE);
        numberButtons[0].addActionListener(this);

        // Row 0: C X /
        gbc.gridy = 0;

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        panel.add(clrButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 2;
        panel.add(delButton, gbc);
        gbc.gridx = 3;
        panel.add(divButton, gbc);

        // Row 1: 7 8 9 *
        gbc.gridy = 1;

        gbc.gridx = 0;
        panel.add(numberButtons[7], gbc);
        gbc.gridx = 1;
        panel.add(numberButtons[8], gbc);
        gbc.gridx = 2;
        panel.add(numberButtons[9], gbc);
        gbc.gridx = 3;
        panel.add(mulButton, gbc);

        // Row 2: 4 5 6 -
        gbc.gridy = 2;
        
        gbc.gridx = 0;
        panel.add(numberButtons[4], gbc);
        gbc.gridx = 1;
        panel.add(numberButtons[5], gbc);
        gbc.gridx = 2;
        panel.add(numberButtons[6], gbc);
        gbc.gridx = 3;
        panel.add(subButton, gbc);

        // Row 3: 1 2 3 +
        gbc.gridy = 3;

        gbc.gridx = 0;
        panel.add(numberButtons[1], gbc);
        gbc.gridx = 1;
        panel.add(numberButtons[2], gbc);
        gbc.gridx = 2;
        panel.add(numberButtons[3], gbc);
        gbc.gridx = 3;
        panel.add(addButton, gbc);

        // Row 4: . 0 =
        gbc.gridy = 4;
        
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        panel.add(numberButtons[0], gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        panel.add(decButton, gbc);
        
        gbc.gridx = 3;
        panel.add(equButton, gbc);

        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        for(int i=0; i<10; i++) {
            if(btn == numberButtons[i]) {
                textField.setText(textField.getText() + i);
                return;
            }
        }
         // Handle operations and special buttons
        if(btn == decButton) {
            textField.setText(textField.getText() + ".");
        } else if(btn == equButton) {
            String expression = textField.getText();
            try {
                double result = operation(expression);
                textField.setText(String.valueOf(result));
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else if(btn == clrButton) {
            textField.setText("");
        } else if(btn == delButton) {
            String currentText = textField.getText();
            if (!currentText.isEmpty()) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else {
            textField.setText(textField.getText() + btn.getText());
        }
    }

    private double operation(String exp) {
        String[] fields = exp.split("[-+*/]");
        if (fields.length < 2) {
            throw new IllegalArgumentException("Invalid expression");
        } else {
            double first = Double.parseDouble(fields[0]);
            double second = Double.parseDouble(fields[1]);
            char operator = exp.charAt(fields[0].length());
            switch (operator) {
                case '+':
                    return first + second;
                case '-':
                    return first - second;
                case '*':
                    return first * second;
                case '/':
                    if (second == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return first / second;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        }
    }
}