package com.example;
// import libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; // swing gui
// import java.util.*;

public class TicTacToe implements ActionListener {
    // Jlabel to declare turn
    JLabel statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
    //buttons initialization
    JButton[] buttons = new JButton[9];
    // x starts the game
    boolean isXTurn = true;
    //constructor
    TicTacToe() {
        JFrame frame = new JFrame("Tic Tac Toe");//main frame with title
        JPanel panel = new JPanel();//new panel
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(3, 3)); //panel layout set to 3*3
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // set border for panel
        // button loop to set font, add action listner, ...
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.WHITE); // set background color
            panel.add(buttons[i]); // add buttons to panel
        }
        frame.add(panel); // add panel to frame
        frame.setSize(400, 400); // set frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation
        frame.setLocationRelativeTo(null); // center the frame
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set font for status label
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // set font and border for status label
        frame.add(statusLabel, BorderLayout.NORTH); // add status label to frame
        frame.setVisible(true); // make frame visible
    }
    @Override
    // actionPerformed method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource(); // get the button that was clicked
        // if the button is empty, set it to X or O based on the turn
        if (buttonClicked.getText().equals("")) {
            buttonClicked.setText(isXTurn ? "X" : "O");
            buttonClicked.setEnabled(false);//disable the button
            buttonClicked.setForeground(isXTurn ? Color.BLUE : Color.RED); // set color based on turn
            
            checkWin(); // check for win after each move
            checkDraw(); // check for draw after each move
            isXTurn = !isXTurn; //toggle the turn
            statusLabel.setText("Player " + (isXTurn ? "X" : "O") + "'s Turn"); // update status label
        }
    }
    void checkWin() {
        // Create a 2D array to hold the values of the buttons
        String[][] XO = new String[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                XO[i][j] = buttons[i * 3 + j].getText();
            }
        }
        // Check rows
        for (int i=0; i<3; i++) {
            if(XO[i][0].equals(XO[i][1]) && XO[i][0].equals(XO[i][2]) && !XO[i][0].equals("")) {
                buttons[i * 3 + 0].setBackground(Color.GREEN);
                buttons[i * 3 + 1].setBackground(Color.GREEN);
                buttons[i * 3 + 2].setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(null, XO[i][0] + " wins!");// show message dialog with winner
                // Disable all buttons
                for (JButton button : buttons) {
                    button.setEnabled(false);
                }
                resetGame(); // reset the game
                return;
            }
        }
        // Check columns
        for (int j=0; j<3; j++) {
            if(XO[0][j].equals(XO[1][j]) && XO[0][j].equals(XO[2][j]) && !XO[0][j].equals("")) {
                buttons[0 + j].setBackground(Color.GREEN);
                buttons[3 + j].setBackground(Color.GREEN);
                buttons[6 + j].setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(null, XO[0][j] + " wins!");
                for (JButton button : buttons) {
                    button.setEnabled(false);
                }
                resetGame(); // reset the game
                return;
            }
        }
        // Check diagonals
        if(XO[0][0].equals(XO[1][1]) && XO[0][0].equals(XO[2][2]) && !XO[0][0].equals("")) {
            buttons[0].setBackground(Color.GREEN);
            buttons[4].setBackground(Color.GREEN);
            buttons[8].setBackground(Color.GREEN);
            JOptionPane.showMessageDialog(null, XO[0][0] + " wins!");
            for (JButton button : buttons) {
                    button.setEnabled(false);
                }
            resetGame(); // reset the game
            return;
        }
        if(XO[0][2].equals(XO[1][1]) && XO[0][2].equals(XO[2][0]) && !XO[0][2].equals("")) {
            buttons[2].setBackground(Color.GREEN);
            buttons[4].setBackground(Color.GREEN);
            buttons[6].setBackground(Color.GREEN);
            JOptionPane.showMessageDialog(null, XO[0][2] + " wins!");
            for (JButton button : buttons) {
                    button.setEnabled(false);
                }
            resetGame(); // reset the game
            return;
        }
    }
    void checkDraw() {
        boolean draw = true; // assume draw
        for (JButton button : buttons) {
            if (button.getText().equals("")) { // if any button is empty, it's not a draw
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(null, "It's a draw!"); // show draw message
            for (JButton button : buttons) {
                button.setEnabled(false); // disable all buttons
            }
            resetGame(); // reset the game
        }
    }

    void resetGame() {
        for (JButton button : buttons) {
            button.setText(""); // reset text
            button.setEnabled(true); // enable buttons
            button.setBackground(Color.WHITE);
        }
        isXTurn = true; // reset turn to X
        statusLabel.setText("Player X's Turn");
    }

}