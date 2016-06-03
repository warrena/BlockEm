package pong;

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
    private String[][] board = new String[20][20];

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
    public void addClicksToBoard(ArrayList<ArrayList<Integer>> clicks, Player currentPlayer) {
        for(ArrayList<Integer> click: clicks) {
            board[click.get(0)][click.get(1)] = currentPlayer.getName();
        }
    }

    /**
     * checks if a set of clicks makes a valid placement (has diagonal of proper color)
     * @param clicks the coordinates of a players clicks
     * @param currentPlayer the currentPlayer
     * @return a boolean: true if clicks are possible, false otherwise
     */
    public boolean checkValidPlacement(ArrayList<ArrayList<Integer>> clicks, Player currentPlayer) {
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

        return true;
    }


    public static void main(String[] args) {
        Board b1 = new Board();

    }

}
