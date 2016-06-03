package pong;

import javafx.scene.layout.Pane;

import java.awt.*;

/**
 * Created by team_blockem on 6/2/16.
 */
public class GridCell {
    private int x;
    private int y;
    private Pane cell;

    public GridCell(int x, int y, Pane cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pane getPane() {
        return cell;
    }
}
