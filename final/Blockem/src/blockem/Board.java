
/**
 * Created by Sam, Allie and Josh
 * This is the game board for Blockem, it contains a matrix that
 * represents the board (each cell either has a player or is null).
 * This class is used to check whether a placement on the board is valid,
 * and to record where players have played.
 */

package blockem;


import java.util.ArrayList;


public class Board {
    // A two dimensional array to represent the board
    private Player[][] board = new Player[20][20];

    /**
     * adds the location a player clicks onto the board
     * @param clicks the coordinates of a players clicks
     * @param currentPlayer the currentPlayer
     */
    public void addClicksToBoard(ArrayList<GridCell> clicks, Player currentPlayer) {
        for(GridCell click: clicks) {
            board[click.getX()][click.getY()] = currentPlayer;
        }
    }

    /**
     * resets the board to start a new game
     */
    public void resetBoard() {
        board = new Player[20][20];
    }

    /**
     * checks if a set of clicks makes a valid placement
     * @param clicks the coordinates of a players clicks
     * @param currentPlayer the currentPlayer
     * @return a boolean: true if clicks are possible, false otherwise
     */
    public boolean checkValidPlacement(ArrayList<GridCell> clicks, Player currentPlayer) {
        for (GridCell square: clicks) {
            int x = square.getX();
            int y = square.getY();
            // checks if has diagonal
            if (isPlayer(x + 1, y + 1, currentPlayer) || isPlayer(x - 1, y + 1, currentPlayer)
                    || isPlayer(x - 1, y - 1, currentPlayer) || isPlayer(x + 1, y - 1, currentPlayer)) {
                return true;
            //checks if starting piece (aka corner)
            } else if ((x==0 && y == 0) || (x == 19 && y == 19) || (x == 19 && y == 0) || (x == 0 && y == 19)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if coordinate location on the board has the player
     * @param x the coordinates of a click
     * @param y the coordinates of a click
     * @param player the currentPlayer
     * @return a boolean: true if player is at location, false otherwise
     */
    private boolean isPlayer(int x, int y, Player player){
        if(x < 0 || x > 19 || y < 0 || y > 19 ) {
            return false;
        } else if(board[x][y] == player) {
            return true;
        }
        return false;
    }

    /**
     * checks if a click is “valid” aka not adjacent to another of that player's pieces
     * @param x the coordinates of a click
     * @param y the coordinates of a click
     * @param currentPlayer the currentPlayer
     * @return a boolean: true if click is possible, false otherwise
     */
    public boolean checkClick(int x, int y, Player currentPlayer) {

        if(board[x][y] == null && !isPlayer(x+1, y, currentPlayer) && !isPlayer(x-1, y, currentPlayer)
                && !isPlayer(x, y+1, currentPlayer) && !isPlayer(x, y-1, currentPlayer)) {
            return true;
        }
        return false;
    }


}
