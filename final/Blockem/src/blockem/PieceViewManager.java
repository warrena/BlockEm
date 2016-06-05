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
                //cell.setStyle("-fx-background-color:yellow;");
                pieceView.add(cell, i, j);
                pieceViewTracker[i][j] = cell;
            }
        }

    }

    private void drawPiece(Piece piece, int x, int y, Player player) {
        ArrayList<Coordinate> rotation = piece.getRotations().get(0);
        String colorString = player.getColorString();

        for(Coordinate coordinate: rotation) {
            pieceViewTracker[x+coordinate.x][y+coordinate.y].setStyle(colorString);
        }

    }

    public void resetPieces(Player player){
        for(int i=0; i<width; i++) {
            for (int j = 0; j < height; j++) {
                pieceViewTracker[i][j].setStyle("-fx-background-color:none;");
            }
        }


        int x = 0;
        int y = 0;
        for(Piece piece: player.getPieces()) {
            drawPiece(piece, x, y, player);
            x+=4;
            if (x > 25) {
                x = 0;
                y +=6;
            }

            if(y>10) {
                return;
            }
        }

    }

}
