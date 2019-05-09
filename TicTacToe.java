/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.toe;

// imported all of the code that I needed to use in my program
import java.awt.Color;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.CENTER;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class TicTacToe extends JFrame implements ActionListener 
{

    private static boolean xTurn = true;

    //Declare Player Object
    private static Player player1;
    private static Player player2;
    private static Player cat; //declare cat

    //declare lable in scoreboard
    private JLabel player1NameLBL;
    private JLabel player2NameLBL;
    private JLabel catNameLBL;
    private JLabel player1scoreLBL; // declare label in ram
    private JLabel player2scoreLBL;
    private JLabel catScoreLBL;

    //array to hold my buttons for the gameboard
    private static JButton[] button = new JButton[9];

    //array of winning combonations. Decimal values will be compared bitwise to plays total 
    private static int[] winsArray = 
    {
        7, 56, 448, 73, 146, 292, 273, 84
    };

    public TicTacToe() 
    {

        cat = new Player("Cat");
        //create players - HARD CODED VALUES NEED TO BE REPLACED WITH USER INPUT
        player1 = new Player(promptPlayersName("Enter First Player's Name"));
        player2 = new Player(promptPlayersName("Enter Second Player's Name"));

        //set player marker
        player1.setPlayerMarker('X');
        player2.setPlayerMarker('O');

        //design panel
        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(6, 3, 10, 10));

        //made the background color blue
        Color blue = new Color(4, 209, 255);
        gameBoard.setBackground(blue);
        
        
        //load array and panel
        int myBinary = 1;
        for (int index = 0; index < button.length; index++) 
        {
            //create button add to button array
            button[index] = new JButton();
            //add listener to "this" button in the array
            button[index].addActionListener(this);
            //set button atttribute to binary number 
            button[index].setActionCommand("" + myBinary);
            //test number on button
            gameBoard.add(button[index]);
            myBinary = myBinary * 2;

        }// end of for loop
        
        //add player's 1 label and score and add it to the gameboard
        JLabel player01 = new JLabel("Player 1");
        gameBoard.add(player01);
        player1NameLBL = new JLabel(player1.getPlayerName());
        gameBoard.add(player1NameLBL);
        player1scoreLBL = new JLabel("0"); //instantiate
        gameBoard.add(player1scoreLBL); // add to panel

        //add player's 2 label and score and add it to the gambeBoard
        JLabel player02 = new JLabel("Player 2");
        gameBoard.add(player02);
        player2NameLBL = new JLabel(player2.getPlayerName());
        gameBoard.add(player2NameLBL);
        player2scoreLBL = new JLabel("0");
        gameBoard.add(player2scoreLBL);

        //add cat's label and score and add it to the gameboard
        JLabel cat = new JLabel("Tied Game");
        gameBoard.add(cat);
        catNameLBL = new JLabel("Cat");
        gameBoard.add(catNameLBL);
        catScoreLBL = new JLabel("0");
        gameBoard.add(catScoreLBL);

        //change the font
        Font font = new Font("Courier New", Font.BOLD, 18);
        player1NameLBL.setFont(font);
        player2NameLBL.setFont(font);
        catNameLBL.setFont(font);
        player01.setFont(font);
        player02.setFont(font);
        cat.setFont(font);
        player1scoreLBL.setFont(font);
        player2scoreLBL.setFont(font);
        catScoreLBL.setFont(font);

          //set title and window name
        setTitle("Tic Tac Toe");
        TitledBorder border = new TitledBorder("Tic Tac Toe");
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleJustification(TitledBorder.CENTER);
        gameBoard.setBorder(border);
        
        //ADD OTHER PANELS OBJECTS TO GET PLAYERS NAMES AND DISPLAY THEIR OVERALL SCORES
        this.setLocationRelativeTo(null);
        this.add(gameBoard);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

        // create object name for ActionEvent
        JButton pressedButton = (JButton) e.getSource();
        if (pressedButton.getText() == "") {
            //determine if turn is "X" or O with boolean xTurn
            if (xTurn) { //place "X"
                pressedButton.setText("X");
                
                // if player's 1 marker is x then it was place down x, check for a winnder and reset the board
                if (player1.getPlayerMarker() == 'X') 
                {
                    player1.addPlayerTotal(Integer.parseInt(e.getActionCommand()));
                    if (check4Winner(player1.getPlayerTotal())) 
                    {
                        player1.addPlayerScore();
                        player1scoreLBL.setText(player1.getPlayerScore());
                        resetBoard();
                    } 
                    else 
                    {// names will be higlighted based on who's turn it is
                        xTurn = false;
                        player1NameLBL.setForeground(Color.BLACK);
                        player2NameLBL.setForeground(Color.ORANGE);
                    }
                } 
                else 
                {// exact same thing as the first one except its for player 2
                    player2.addPlayerTotal(Integer.parseInt(e.getActionCommand()));
                    if (check4Winner(player2.getPlayerTotal())) 
                    {
                        player2.addPlayerScore();
                        player2scoreLBL.setText(player2.getPlayerScore());
                        resetBoard();
                    } else 
                    {// names will be higlighted based on who's turn it is
                        xTurn = false;
                        player1NameLBL.setForeground(Color.BLACK);
                        player2NameLBL.setForeground(Color.ORANGE);
                    }
                }
            } 
            else 
            {// if player 1 is 0 then it will place o on the gameboard, check for a winner and if its true then it will add 1 to the score and reset board
                pressedButton.setText("O");
                if (player1.getPlayerMarker() == 'O') 
                {
                    player1.addPlayerTotal(Integer.parseInt(e.getActionCommand()));
                    if (check4Winner(player1.getPlayerTotal())) 
                    {
                        player1.addPlayerScore();
                        player1scoreLBL.setText(player1.getPlayerScore());
                        resetBoard();
                    } 
                    else 
                    {// names will be higlighted based on who's turn it is
                        xTurn = true;
                        player2NameLBL.setForeground(Color.BLACK);
                        player1NameLBL.setForeground(Color.ORANGE);
                    }
                } else //player 2 is x and it the same things as above
                {
                    player2.addPlayerTotal(Integer.parseInt(e.getActionCommand()));
                    if (check4Winner(player2.getPlayerTotal())) 
                    {
                        player2.addPlayerScore();
                        player2scoreLBL.setText(player2.getPlayerScore());
                        resetBoard();
                    } 
                    else 
                    {// names will be higlighted based on who's turn it is
                        xTurn = true;
                        player2NameLBL.setForeground(Color.BLACK);
                        player1NameLBL.setForeground(Color.ORANGE);
                    }
                }

            }//end of xTurn
            // check for tie game
            
            if (player1.getPlayerTotal() + player2.getPlayerTotal() == 511) 
            {
                cat.addPlayerScore();
                catScoreLBL.setText(cat.getPlayerScore());
                resetBoard();
            }
        }//end check for blank
    }//end of listener

    public static boolean check4Winner(int total) 
    {
        //loop through the winds array and checking each possible winning combination
        for (int index = 0; index < winsArray.length; index += 1)
        {
            //compare the wins array occurance bitwise to the current total
            if ((winsArray[index] & total) == winsArray[index])
            {
                return true; //bitwise match - we have a winner
            }
        }
        return false;
    }

    public static void resetBoard() {
        for (int index = 0; index < button.length; index++) 
        {
            button[index].setText("");
        }
        player1.resetPlayerTotal();
        player2.resetPlayerTotal();

        //Change Player's Markers
        if (player1.getPlayerMarker() == 'X') 
        {
            player1.setPlayerMarker('O');
            player2.setPlayerMarker('X');
        } 
        else 
        {
            player1.setPlayerMarker('X');
            player2.setPlayerMarker('O');

        }
        xTurn = true;
    }

    public static String promptPlayersName(String prompt) 
    {
        String name = "";
        while (name.length() < 1) 
        {
            name = JOptionPane.showInputDialog(prompt);
        }
        return name;
    }

    public static void main(String[] args) 
    {
        //start a new game and make it visible
        new TicTacToe().setVisible(true);

    }

}
