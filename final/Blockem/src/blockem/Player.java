package blockem;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Player Class
 * contains the different attributes of the players
 * Created on 5/26/16.
 */
public class Player {

    //List of Piece objects that player has
    ArrayList<Piece> myPieces = new ArrayList<Piece>();

    //Current score of the player, equal to the area of remaining blocks that have yet to be played by player
    int score;

    //RGB values of player
    int r;
    int g;
    int b;


    //Name of player
    String name;

    //ImageView to hold png or jpg avatar
    private String avatar;

    //Whether or not the player has passed, a player passes when they think they have no more moves left to play
    boolean passed;


    /**
     * Sole constructor to initialize player. Initializes
     * the starting score of the game and creates the player's initial
     * list/repo of pieces
     * @param name the String name of the player
     * @param avatar_filepath the avatar image for the player
     * @param red the red value for player color
     * @param green the green value for player color
     * @param blue the blue value for player color
     */
    public Player(String name, String avatar_filepath, int red, int green, int blue) {
        score = 89;
        passed = false;
        this.name = name;
        this.r = red;
        this.g = green;
        this.b = blue;
        this.avatar = avatar_filepath;
        myPieces = createAllPieces();

    }

    public String getColorString() {
        return "rgb(" + r + "," + g + "," + b +")";
    }

    public String getMutedColorString() {
        return "rgb(" + Math.round(r/1.5) + "," + Math.round(g/1.5) + "," + Math.round(b/1.5) +")";
    }


    /**
     * Returns a Boolean that is true if the player has
     * passed at any point in the game, and is false
     * if the player has never passed in this game.
     * @return true or false
     */
    public boolean getPassed() {
        return passed;
    }

    /**
     * Sets the player’s passed Boolean to true once
     * the player passes in the game
     */
    public void setPassed() {
        passed = true;
    }

    /**
     * Returns the avatar image that refers to this player
     * @return  image of the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Returns the list of pieces that the player currently has in
     * their repository. This list includes all of the pieces which the
     * player has not yet played in the game.
     * @return ArrayList of Pieces
     */
    public ArrayList<Piece> getPieces() {
        return myPieces;
    }

    /**
     * Returns the name of this player
     * @return a string of the player’s name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current score of the player
     * @return an int score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the current score of the player
     * @return an int score
     */
    public void updateScore(int pieceSize) {
        score = score - pieceSize;
    }


    private ArrayList<Coordinate> transformCoordinates(ArrayList<GridCell> clicks) {
        ArrayList<Coordinate> transformed_coordinates = new ArrayList<Coordinate>();
        for(GridCell click: clicks) {
            transformed_coordinates.add(new Coordinate(click.getX(), click.getY()));
        }
        return  transformed_coordinates;
    }

    /**
     * Returns true if the piece that the player wants to play
     * is in their list/repository of pieces, meaning the player
     * has not already played that piece
     * @param clicks a set of all the coordinates a player just placed
     * @return true or false
     */
    public boolean hasPiece(ArrayList<GridCell> clicks) {
        ArrayList<Coordinate> transformed_coordinates = transformCoordinates(clicks);
        for (Piece piece: myPieces) {
            if(piece.isPiece(transformed_coordinates)) {
                myPieces.remove(piece);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the piece that the player just played
     * from their list/repository of pieces.
     * @param piecePassed the Piece object that the player just played
     */
    public void removePiece(Piece piecePassed) {
        myPieces.remove(piecePassed);
    }

    /**
     * Returns the initial list of all the pieces that the player
     * starts with at the beginning of the game. This method
     * initialized the 21 Piece objects with their relative coordinates
     * (which describe the shape of each piece)
     * @return ArrayList containing 21 Pieces
     */
    public ArrayList<Piece> createAllPieces() {
        //Piece l4 = new Piece([0, 0],[1, 1],[1, 2]);
        //n, v5, t5, u, l5, i5, z5, y, w, p, x, f, z4, i4, l4, o, t4, i3, v3, 2, 1
        ArrayList<Piece> allPieces = new ArrayList<Piece>();
        return allPieces;
    }


}


