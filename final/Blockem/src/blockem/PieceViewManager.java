package blockem;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

/**
 * Created by Sam on 6/5/16.
 */
public class PieceViewManager {
    private Pane[][] pieceViewTracker;
    private GridPane pieceView;
    private int width;
    private int height;


    public PieceViewManager(int width, int height, GridPane pieceView) {
        pieceViewTracker = new Pane[width][height];
        this.pieceView = pieceView;
        this.width = width;
        this.height = height;

        // Creates all of the rows and columns
        for(int i=0; i<width; i++){
            pieceView.getColumnConstraints().add(new ColumnConstraints(15));

        }

        for(int i=0; i<height; i++) {
            pieceView.getRowConstraints().add(new RowConstraints(15));
        }

        // creates all of the panes in the gridpane
        for(int i=0; i<width; i++) {
            for (int j = 0; j < height; j++) {
                final Pane cell = new Pane();
                pieceView.add(cell, i, j);
                pieceViewTracker[i][j] = cell;
            }
        }

    }

    private void drawPiece(ArrayList<Coordinate> rotation, int x, int y, Player player) {
        String colorString = player.getColorString();

        for(Coordinate coordinate: rotation) {
            pieceViewTracker[x+coordinate.x][y+coordinate.y].setStyle(colorString);
        }

    }

    public void resetPieces(Player player){
        for(int i=0; i<width; i++) {
            for (int j = 0; j < height; j++) {
                // BACKGROUND
                pieceViewTracker[i][j].setStyle("-fx-background-color:rgb(100,100,100); -fx-border-color: gray;");
            }
        }


        int x = 0;
        int y = 0;
        for(Piece piece: player.getPieces()) {
            drawPiece(piece.getRotations().get(0), x, y, player);
            x+=4;
            if (x > 41) {
                x = 0;
                y +=6;
            }
        }

    }


    public void debugDrawAllRotations(Player player) {
        int x = 0;
        int y = 0;
        for(Piece piece: player.getPieces()) {
            for(ArrayList<Coordinate> rotation: piece.getRotations()) {
                drawPiece(rotation, x, y, player);
                x += 4;
                if (x > 80) {
                    x = 0;
                    y += 6;
                }
            }
        }

    }

    public void debugDrawAllRotations2(Player player) {
        int delay = 0;
        int x = 0;
        int y = 0;
        for(Piece piece: player.getPieces()) {
            delay += 1;
            if(delay > 9) {
                for (ArrayList<Coordinate> rotation : piece.getRotations()) {
                    drawPiece(rotation, x, y, player);
                    x += 4;
                    if (x > 80) {
                        x = 0;
                        y += 6;
                    }
                }
            }
        }

    }

}
