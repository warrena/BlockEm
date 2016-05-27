package blockem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import blockem.Player;

import java.util.ArrayList;

/**
 * Game class
 * Created on 5/26/16.
 */
public class Game extends Application {
    ArrayList<Player> players = new ArrayList<Player>();
    Player currentPlayer;
    int numberPassed = 0;
    Board board;


    /**
    * sole constructor for this class,
    * it initializes the players for the game, and set the current
    * player to the first player in the list
    */
    public Game(ArrayList<Player> players) {
        currentPlayer = players.get(0);
        this.players = players;
    }

    /** 
    * Returns the player whose turn is next
    * @return player
    */
    public Player getNextPlayer() {
        //getNextPlayer - returns the next player, checks allPassed and calls gameOver if valid

        return null;
    }

    /** 
    * Checks whether the piece that the player played
    * can be played at that location on the board (that the piece connects to the 
    * corner of another piece from that player) and is a piece that the player has.
    * If both of these conditions are true, it returns true.
    * @param piece the piece that the player added to the board
    * @return true if placement of piece is valid
    */
    public boolean checkValidPiece(Piece piece) {
        ArrayList<ArrayList<Integer>> clicks = new ArrayList<ArrayList<Integer>>();
        if (board.checkValidPlacement(clicks, currentPlayer) && currentPlayer.hasPiece(piece)) {
            return true;
        } else {
            return false;
        }
    }

    /** 
    * Checks whether the current player can select a square on the game board.
    * The player cannot select squares where a piece has already been played
    * or which is adjacent to one of their pieces.
    * @param x, y the coordinates of the board square the player is trying to select
    * @return true or false
    */
    public boolean checkValidClick(int x, int y) {
        if (board.checkClick(x,y, currentPlayer)) {
            return true;
        } else {
            return false;
        }
    }

    /** 
    * Returns true if all of the players have passed; meaning that no
    * player can play another piece and the game is over.
    * @return true or false
    */
    public boolean allPassed() {
        if (numberPassed < 4) {
            return false;
        }
        return true;
    }

    /** 
    * Returns the player that has won the game.
    * This is called once the game is over, and determines the 
    * winner by finding the player with the lowest score.
    * @return winning player
    */
    public Player gameOver() {
        //still need to figure out ties
        Player winningPlayer = null;
        int winningScore = 0;
        for (Player player : players) {
            if (player.getScore() > winningScore) {
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

    }
}

