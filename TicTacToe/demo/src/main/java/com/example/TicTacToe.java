package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    JLabel statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
    JLabel scoreLabel = new JLabel("Score - X: 0 | O: 0", SwingConstants.CENTER);
    JButton[] buttons = new JButton[9];
    JButton restartButton = new JButton("Restart");
    boolean isXTurn = true;
    int scoreX = 0, scoreO = 0;

    TicTacToe() {
        JFrame frame = new JFrame("Tic Tac Toe");
        JPanel panel = new JPanel(new GridLayout(3, 3));
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel bottomPanel = new JPanel(new FlowLayout());

        frame.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.WHITE);
            panel.add(buttons[i]);
        }

        // Status and Score
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        topPanel.add(statusLabel);
        topPanel.add(scoreLabel);

        // Restart Button
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.addActionListener(e -> resetGame());
        bottomPanel.add(restartButton);

        // Frame setup
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Optional: Dark mode background
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        statusLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.LIGHT_GRAY);
        bottomPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBackground(Color.DARK_GRAY);
        panel.setBackground(Color.DARK_GRAY);

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("")) {
            buttonClicked.setText(isXTurn ? "X" : "O");
            buttonClicked.setEnabled(false);
            buttonClicked.setForeground(isXTurn ? Color.CYAN : Color.PINK);

            if (checkWin()) {
                return;
            }
            if (checkDraw()) {
                return;
            }

            isXTurn = !isXTurn;
            statusLabel.setText("Player " + (isXTurn ? "X" : "O") + "'s Turn");
        }
    }

    boolean checkWin() {
        String[][] XO = new String[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                XO[i][j] = buttons[i * 3 + j].getText();
            }
        }

        // Rows, Columns, Diagonals
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6},{1,4,7},{2,5,8}, // columns
            {0,4,8},{2,4,6}          // diagonals
        };

        for (int[] line : lines) {
            String a = buttons[line[0]].getText();
            String b = buttons[line[1]].getText();
            String c = buttons[line[2]].getText();

            if (!a.equals("") && a.equals(b) && a.equals(c)) {
                buttons[line[0]].setBackground(Color.GREEN);
                buttons[line[1]].setBackground(Color.GREEN);
                buttons[line[2]].setBackground(Color.GREEN);

                for (JButton button : buttons) button.setEnabled(false);

                if (a.equals("X")) scoreX++;
                else scoreO++;

                scoreLabel.setText("Score - X: " + scoreX + " | O: " + scoreO);
                JOptionPane.showMessageDialog(null, a + " wins!");
                resetGame();
                return true;
            }
        }
        return false;
    }

    boolean checkDraw() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) return false;
        }

        JOptionPane.showMessageDialog(null, "It's a draw!");
        resetGame();
        return true;
    }

    void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
            button.setBackground(Color.WHITE);
        }
        isXTurn = true;
        statusLabel.setText("Player X's Turn");
    }

}