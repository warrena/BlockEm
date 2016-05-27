package blockem;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by pitkofskyj on 5/26/16.
 */
public class Player {

    ArrayList<Piece> myPieces = new ArrayList<Piece>();
    int score;
    String name;
    private ImageView avatar;
    boolean passed;


    public Player(String name, ImageView avatar) {
        score = 89;
        passed = false;
        this.name = name;
        this.avatar = avatar;
        myPieces = createAllPieces();

    }


    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean pass) {
        passed = true;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public ArrayList<Piece> getPieces() {
        return myPieces;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int pieceSize) {
        score = score - pieceSize;
    }

    public boolean hasPiece(Piece playedPiece) {
        if (myPieces.contains(playedPiece)) {
            return true;
        } else {
            return false;
        }
    }

    public void removePiece(Piece piecePassed) {
        myPieces.remove(piecePassed);
    }


    public ArrayList<Piece> createAllPieces() {
        //Piece l4 = new Piece([0, 0],[1, 1],[1, 2]);
        //n, v5, t5, u, l5, i5, z5, y, w, p, x, f, z4, i4, l4, o, t4, i3, v3, 2, 1
        ArrayList<Piece> allPieces = new ArrayList<Piece>();
        return allPieces;
    }


}


