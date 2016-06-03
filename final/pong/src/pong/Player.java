package pong;
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

    //Name of player
    String name;

    //Name of player
    Color color;

    //ImageView to hold png or jpg avatar
    private ImageView avatar;

    //Whether or not the player has passed, a player passes when they think they have no more moves left to play
    boolean passed;


    /**
     * Sole constructor to initialize player. Initializes
     * the starting score of the game and creates the player's initial
     * list/repo of pieces
     * @param name the String name of the player
     * @param avatar the avatar image for the player
     */
    public Player(String name, ImageView avatar, Color color) {
        score = 89;
        passed = false;
        this.name = name;
        this.color = color;
        this.avatar = avatar;
        myPieces = createAllPieces();

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
    public ImageView getAvatar() {
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

    /**
     * Returns true if the piece that the player wants to play
     * is in their list/repository of pieces, meaning the player
     * has not already played that piece
     * @param playedPiece the Piece object that the player just played
     * @return true or false
     */
    public boolean hasPiece(Piece playedPiece) {
        if (myPieces.contains(playedPiece)) {
            return true;
        } else {
            return false;
        }
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


