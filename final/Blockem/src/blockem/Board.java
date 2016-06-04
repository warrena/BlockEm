package blockem;

import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sam on 5/26/16.
 */
public class Board {
    // A two dimensional array to represent the board
    private Player[][] board = new Player[20][20];

    /**
     * constructor for the board, does nothing
     */

    public Board() {
    }

    /**
     * adds if a set of clicks to the board
     * @param clicks the coordinates of a players clicks
     * @param currentPlayer the currentPlayer
     */
    public void addClicksToBoard(ArrayList<GridCell> clicks, Player currentPlayer) {
        for(GridCell click: clicks) {
            board[click.getX()][click.getY()] = currentPlayer;
        }
    }

    /**
     * checks if a set of clicks makes a valid placement (has diagonal of proper color)
     * @param clicks the coordinates of a players clicks
     * @param currentPlayer the currentPlayer
     * @return a boolean: true if clicks are possible, false otherwise
     */
    public boolean checkValidPlacement(ArrayList<GridCell> clicks, Player currentPlayer) {
        boolean diagonal = false;
        for (GridCell square: clicks) {
            int x = square.getX();
            int y = square.getY();
            // checks if has diagonal
            if (!isPlayer(x + 1, y + 1, currentPlayer) && !isPlayer(x - 1, y + 1, currentPlayer)
                    && !isPlayer(x - 1, y - 1, currentPlayer) && !isPlayer(x + 1, y - 1, currentPlayer)) {
                diagonal = true;
            //checks if starting piece (aka corner)
            } else if ((x==0 && y == 0) || (x == 19 && y == 19) || (x == 19 && y == 0) || (x == 0 && y == 19)) {
                diagonal = true;
            }
        }
        return diagonal;
    }


    private boolean isPlayer(int x, int y, Player player){
        if(x < 0 || x > 19 || y < 0 || y > 19 ) {
            return true;
        } else if(board[x][y] == player) {
            return false;
        }
        return true;
    }
    /**
     *  checks if a click is “valid” aka not adjacent to another of that players pieces
     * @param x the coordinates of a click
     * @param y the coordinates of a click
     * @param currentPlayer the currentPlayer
     * @return a boolean: true if click is possible, false otherwise
     */
    public boolean checkClick(int x, int y, Player currentPlayer) {

        if(board[x][y] == null && isPlayer(x+1, y, currentPlayer) && isPlayer(x-1, y, currentPlayer)
                && isPlayer(x, y+1, currentPlayer) && isPlayer(x, y-1, currentPlayer)) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Board b1 = new Board();

    }

}
