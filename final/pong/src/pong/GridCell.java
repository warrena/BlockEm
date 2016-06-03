package pong;

import javafx.scene.layout.Pane;

import java.awt.*;

/**
 * Created by Sam on 6/2/16.
 */
public class GridCell {
    int x;
    int y;
    Player player;
    Pane cell;

    public GridCell(int x, int y, Pane cell, Player player) {
        x = x;
        y = y;
        cell = cell;
        player = player;

    }
}
