package blockem;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 20.0;

    ArrayList<Player> players = new ArrayList<Player>();
    Player currentPlayer;
    int numberPassed = 0;
    Board board = new Board();
    ArrayList<GridCell> clicks = new ArrayList<GridCell>();


    @FXML private GridPane grid;
    @FXML private FlowPane playersView;
    @FXML private GridPane pieceView;
    private PieceViewManager pieceViewManager;

    @FXML private Label playerOneScore;
    @FXML private Label playerTwoScore;
    @FXML private Label playerThreeScore;
    @FXML private Label playerFourScore;
//    @FXML private ImageView playerOneAvatar;
//    @FXML private ImageView playerTwoAvatar;
//    @FXML private ImageView playerThreeAvatar;
//    @FXML private ImageView playerFourAvatar;

    public void initialize() {
        // Add four players
        players.add(new Player("Jeremiah", "/pic.jpg", 255, 255, 0));
        players.add(new Player("Allie", "/pic.jpg", 255, 0, 0));
        players.add(new Player("Jeremiah", "/pic.jpg", 0, 0, 255));
        players.add(new Player("Allie", "/pic.jpg", 0, 204, 0));
        currentPlayer = players.get(0);

        Image red = new Image("file:@res/red.jpg");
        Image blue = new Image("file:@res/blue.jpg");
        Image green = new Image("file:@res/green.jpg");
        Image purple = new Image("file:@res/purple.jpg");



        pieceViewManager = new PieceViewManager(30, 15, pieceView);
        pieceViewManager.resetPieces(currentPlayer);

        // INITIALIZES THE BOARD
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
                        Pane src = (Pane)arg0.getSource();
                        handleClick(src);
                    }
                });
            }
        }


    }

    /**
     * Does all that needs to be done when a cell is clicked
     */
    public void handleClick(Pane pane) {
        int y = grid.getRowIndex(pane);
        int x = grid.getColumnIndex(pane);
        System.out.println("Row: " + x + " Column: " + y);
        if (board.checkClick(x, y, currentPlayer)) {
            // -------- ADD -------  Now check to make sure haven't already clicked there
            pane.setStyle(currentPlayer.getMutedColorString());
            clicks.add(new GridCell(x, y, pane));
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
        int curIndex = players.indexOf(currentPlayer);
        if(curIndex == 3) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(curIndex + 1);
        }
        return null;
    }

    /**
     * Checks whether the piece that the player played
     * can be played at that location on the board (that the piece connects to the
     * corner of another piece from that player) and is a piece that the player has.
     * If both of these conditions are true, it returns true.
     * @return true if placement of piece is valid
     */
    @FXML
    public void checkValidPiece() {
        if (board.checkValidPlacement(clicks, currentPlayer) && currentPlayer.hasPiece(clicks)) {
            // changes color
            for(GridCell click: clicks) {
                click.getPane().setStyle(currentPlayer.getColorString());
            }
            board.addClicksToBoard(clicks, currentPlayer);
            clicks.clear();
            // ----- ADD ------  updateScore();
            updateAllScores();
            getNextPlayer();
            pieceViewManager.resetPieces(currentPlayer);
        } else {
            for(GridCell click: clicks) {
                click.getPane().setStyle("-fx-background-color:none;");
            }
            clicks.clear();
        }
    }

    private void updateAllScores() {
        playerOneScore.setText("Player One Score: " + String.valueOf(players.get(0).getScore()));
        playerTwoScore.setText("Player Two Score: " + String.valueOf(players.get(1).getScore()));
        playerThreeScore.setText("Player Three Score: " + String.valueOf(players.get(2).getScore()));
        playerFourScore.setText("Player Four Score: " + String.valueOf(players.get(3).getScore()));
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
