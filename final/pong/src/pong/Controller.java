package pong;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 20.0;

    ArrayList<Player> players = new ArrayList<Player>();
    Player currentPlayer;
    int numberPassed = 0;
    Board board;
    ArrayList<GridCell> clicks = new ArrayList<GridCell>();


    @FXML private GridPane grid;

    private int score;
    private boolean paused;
    private Timer timer;

    public Controller() {
        this.paused = false;
        this.score = 0;

    }

    public void initialize() {
        // Creates all of the rows and columns
        for(int i=0; i<20; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(20));
            grid.getRowConstraints().add(new RowConstraints(20));
        }

        // creates all of the panes in the gridpane
        for(int i=0; i<21; i++){
            for(int j=0; j<21; j++){
                final Pane cell = new Pane();
                //cell.setStyle("-fx-background-color:yellow;");
                grid.add(cell, i, j);

                cell.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent arg0)
                    {
                        Node src = (Node)arg0.getSource();
                        int x = grid.getRowIndex(src);
                        int y = grid.getColumnIndex(src);
                        handleClick(x, y, cell);
                    }
                });
            }
        }
    }

    /**
     * Does all that needs to be done when a cell is clicked
     */
    public void handleClick(int x, int y, Pane pane) {
        if (board.checkClick(x, y, currentPlayer)) {
            clicks.add(new GridCell(x, y, pane, currentPlayer));
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {

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
}
