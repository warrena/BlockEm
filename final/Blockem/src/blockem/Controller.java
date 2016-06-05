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
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.application.Platform.exit;

public class Controller implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 20.0;

    ArrayList<Player> players = new ArrayList<Player>();
    Player currentPlayer;
    int numberPassed = 0;
    Board board = new Board();
    ArrayList<GridCell> clicks = new ArrayList<GridCell>();


    @FXML private GridPane grid;
    private ArrayList<Pane> paneList = new ArrayList<Pane>();
    @FXML private GridPane pieceView;
    private PieceViewManager pieceViewManager;

    @FXML private Label playerOneScore;
    @FXML private Label playerTwoScore;
    @FXML private Label playerThreeScore;
    @FXML private Label playerFourScore;

    @FXML private Label message;
    @FXML private Label playerTurn;

    @FXML private Label playerWon;

    private boolean gameOver = false;
    String backgroundString = "-fx-background-color: rgb(50,50,50); -fx-border-color: gray;";

    public void initialize() {
        // Add four players
        players.add(new Player("Player1", "/pic.jpg", 255, 255, 0));
        players.add(new Player("Player2", "/pic.jpg", 255, 0, 0));
        players.add(new Player("Player3", "/pic.jpg", 0, 0, 255));
        players.add(new Player("Player4", "/pic.jpg", 0, 204, 0));
        currentPlayer = players.get(0);
        playerOneScore.setStyle("-fx-border-color: yellow;");
        message.setFont(Font.font("Arial Black", 20.0));
        playerOneScore.setStyle("-fx-border-color: black; -fx-background-color: yellow;");



        //pieceViewManager = new PieceViewManager(100, 50, pieceView);
        //pieceViewManager.debugDrawAllRotations(currentPlayer);

        //pieceViewManager = new PieceViewManager(100, 50, pieceView);
        //pieceViewManager.debugDrawAllRotations2(currentPlayer);

        pieceViewManager = new PieceViewManager(43, 10, pieceView);
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
                cell.setStyle(backgroundString);
                grid.add(cell, i, j);
                paneList.add(cell);

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
        if(!gameOver) {
            int y = grid.getRowIndex(pane);
            int x = grid.getColumnIndex(pane);
            System.out.println("Row: " + x + " Column: " + y);
            GridCell removeClick = null;
            if (board.checkClick(x, y, currentPlayer)) {
                // -------- ADD -------  Now check to make sure haven't already clicked there
                pane.setStyle(currentPlayer.getMutedColorString());
                boolean notClicked = true;
                for (GridCell currentClicks : clicks) {
                    if (currentClicks.getX() == x && currentClicks.getY() == y) {
                        notClicked = false;
                        removeClick = currentClicks;
                    }
                }
                if (notClicked) {
                    clicks.add(new GridCell(x, y, pane));
                }
            }
            else {
                clicks.remove(removeClick);
                removeClick.getPane().setStyle("-fx-background-color:none;");
            }
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
        System.out.println(numberPassed);
        if (allPassed()) {
            gameOver();
        } else {
            int curIndex = players.indexOf(currentPlayer);
            if (curIndex == 3) {
                currentPlayer = players.get(0);
                playerOneScore.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
                playerFourScore.setStyle("-fx-border-color: none; -fx-background-color: green;");
            } else {
                currentPlayer = players.get(curIndex + 1);
                if(curIndex == 0) {
                    playerTwoScore.setStyle("-fx-border-color: black; -fx-background-color: red;");
                    playerOneScore.setStyle("-fx-border-color: none; -fx-background-color: yellow;");
                }
                if(curIndex == 1) {
                    playerThreeScore.setStyle("-fx-border-color: black; -fx-background-color: blue;");
                    playerTwoScore.setStyle("-fx-border-color: none; -fx-background-color: red;");
                }
                if(curIndex == 2) {
                    playerFourScore.setStyle("-fx-border-color: black; -fx-background-color: green;");
                    playerThreeScore.setStyle("-fx-border-color: none; -fx-background-color: blue;");
                }
            }
            if (currentPlayer.passed) {
                getNextPlayer();
            }
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
        boolean validPlacement = board.checkValidPlacement(clicks, currentPlayer);
        if (validPlacement && currentPlayer.hasPiece(clicks)) {
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
            message.setText("");
        } else {
            for(GridCell click: clicks) {
                click.getPane().setStyle(backgroundString);
            }
            if (!validPlacement) {
                message.setText("Invalid Placement");
            } else {
                message.setText("Invalid Piece");
            }
            clicks.clear();
        }
    }

    @FXML
    public void playerPass() {
        currentPlayer.passed = true;
        numberPassed +=1;
        getNextPlayer();
        pieceViewManager.resetPieces(currentPlayer);
        for(GridCell click: clicks) {
            click.getPane().setStyle(backgroundString);
        }
        clicks.clear();
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
    public void gameOver() {
        gameOver = true;
        ArrayList<Player> winningPlayers = new ArrayList<Player>();
        int winningScore = 100;
        for (Player player : players) {
            if (player.getScore() <= winningScore) {
                System.out.println(player.getName());
                winningPlayers.add(player);
                winningScore = player.getScore();
            }
        }
        int counter = 0;
        String winningMessage = "";
        for (Player winners:winningPlayers) {
            if (counter == 0) {
                winningMessage = winners.getName();
            }
            else if(counter == winningPlayers.size() -1) {
                winningMessage += " and " + winners.getName();
            }
            else {
                winningMessage += ", " + winners.getName();
            }
            counter++;

        }

        message.setText(winningMessage + " won!");
    }

    @FXML
    private void resetGame() {

        for(Pane pane: paneList) {
            pane.setStyle(backgroundString);
        }

        currentPlayer = players.get(0);
        for(Player player: players) {
            player.resetPlayer();
        }
        numberPassed = 0;
        updateAllScores();
        pieceViewManager.resetPieces(currentPlayer);
        board.resetBoard();
        playerOneScore.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
        playerTwoScore.setStyle("-fx-border-color: none; -fx-background-color: red;");
        playerThreeScore.setStyle("-fx-border-color: none; -fx-background-color: blue;");
        playerFourScore.setStyle("-fx-border-color: none; -fx-background-color: green;");
        message.setText("");


        gameOver = false;

    }
}
