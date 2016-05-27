package blockem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import blockem.Player;

import java.util.ArrayList;

/**
 * Created by pitkofskyj on 5/26/16.
 */
public class Game extends Application {
    ArrayList<Player> players = new ArrayList<Player>();
    Player currentPlayer;
    int numberPassed = 0;
    Board board;


    public Game(ArrayList<Player> players) {
        currentPlayer = players.get(0);
    }

    public Player getNextPlayer() {
        //getNextPlayer - returns the next player, checks allPassed and calls gameOver if valid

        return null;
    }

    public boolean checkValidPiece(Piece piece) {
        if (board.checkValidPlacement() && currentPlayer.hasPiece(piece)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean checkValidClick(int x, int y) {
        if (board.checkClick(x,y, currentPlayer)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean allPassed() {
        if (numberPassed < 4) {
            return false;
        }
        return true;
    }

    public void gameOver() {
        //still need to figure out ties
        Player winningPlayer;
        int winningScore = 0;
        for (Player player : players) {
            if (player.getScore() > winningScore) {
                winningPlayer = player;
            }
        }
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

