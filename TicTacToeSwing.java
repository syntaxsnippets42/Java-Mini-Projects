import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing a Tic-Tac-Toe game using Swing
public class TicTacToeSwing extends JFrame {
    private JButton[][] buttons; // 2D array to store the buttons in the game grid
    private char currentPlayer = 'X'; // Represents the current player ('X' or 'O')

    // Constructor to initialize the Tic-Tac-Toe game window
    public TicTacToeSwing() {
        setTitle("Tic-Tac-Toe"); // Set the window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        setSize(300, 300); // Set window size
        setLayout(new GridLayout(3, 3)); // Set layout to a 3x3 grid
        initializeButtons(); // Initialize the game buttons
        setVisible(true); // Make the window visible
    }

    // Initialize the game buttons in a 3x3 grid
    private void initializeButtons() {
        buttons = new JButton[3][3]; // Initialize the button array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(""); // Create a new button
                buttons[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 40)); // Set font and size
                buttons[i][j].setFocusPainted(false); // Disable focus painting
                final int row = i;
                final int col = j;
                // Add ActionListener to handle button click
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(row, col); // Call method when button is clicked
                    }
                });
                add(buttons[i][j]); // Add the button to the grid layout
            }
        }
    }

    // Method to handle button click
    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("")) { // Check if the button is not already clicked
            buttons[row][col].setText(String.valueOf(currentPlayer)); // Set button text to current player symbol
            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!"); // Display winner message
                resetGame(); // Reset the game after a win
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!"); // Display draw message
                resetGame(); // Reset the game after a draw
            } else {
                switchPlayer(); // Switch to the other player
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Move. Try Again!"); // Display invalid move message
        }
    }

    // Method to switch the current player ('X' to 'O' or vice versa)
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to check if there is a winner
    private boolean checkWinner() {
        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                return true; // Row
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
                return true; // Column
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal 2
        }
        return false; // No winner
    }

    // Method to check if the board is full (draw)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // If any button is empty, the board is not full
                }
            }
        }
        return true; // Board is full
    }

    // Method to reset the game
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear all button texts
            }
        }
        currentPlayer = 'X'; // Reset to the starting player
    }

    // Main method to start the application
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeSwing(); // Create an instance of the TicTacToeSwing class
            }
        });
    }
}
