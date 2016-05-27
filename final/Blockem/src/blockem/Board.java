package blockem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sam on 5/26/16.
 */
public class Board {
    // A two dimensional array to represent the board
    private String[][] board = new String[20][20];


    public Board() {
        System.out.println(board[0][0]);
    }

    // checks if a click is “valid” aka not adjacent to another of players pieces
    // returns true is valid, false otherwise
    public boolean checkValidPlacement() {
        return true;
    }

    // checks if a set of clicks makes a valid placement (has diagonal of proper color)
    // returns true if valid, false otherwise
    public boolean checkClick(int x, int y, Player currentPlayer) {

        return true;
    }


    public static void main(String[] args) {
        Board b1 = new Board();


    }

}