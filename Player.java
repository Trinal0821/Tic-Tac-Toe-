package tic.tac.toe;

import java.awt.Font;

public class Player
{
    //class attributes
    private String playerName = "";
    private int playerTotal = 0; //board total while playing each game
    private char playerMarker; //X or O
    private int playerScore = 0; //overall wins by player 

    
    public Player(String name)
    {
        playerName=name; // set player name
    }

    public void addPlayerTotal(int total)
    {
        playerTotal=playerTotal + total; //count player's total
    }

    public void setPlayerMarker(char marker)
    {
        playerMarker=marker; //assign player's marker
    }

    public String getPlayerName()
    {
        return playerName; //display player's name
    }

    public int getPlayerTotal()
    {
        return playerTotal; //display player's total
    }

    public char getPlayerMarker()
    {
        return playerMarker; //display player's marker
    }   
    
    public String getPlayerScore() // games won
    {
        return (playerScore + "");
    }
    
    public void addPlayerScore()
    {
         this.playerScore++; //display player's score
    }  
    
    public void resetPlayerTotal() 
    {
        playerTotal = 0; // reset players total when they start a new game 
    }
}
