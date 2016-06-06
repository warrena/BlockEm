/**
 * Created by Sam, Allie and Josh
 * Each cell in the game board from the view has
 * a x and y coordinate and a pane
 */

package blockem;

import javafx.scene.layout.Pane;


public class GridCell {
    private int x;
    private int y;
    private Pane cell;

    public GridCell(int x, int y, Pane cell) {
        this.x = x;
        this.y = y;
        this.cell = cell;

    }
    /**
     * @return returns the x coordinate of the GridCell
     */
    public int getX() {
        return x;
    }

    /**
     * @return returns the y coordinate of the GridCell
     */
    public int getY() {
        return y;
    }

    /**
     * @return returns the pane of the GridCell
     */
    public Pane getPane() {
        return cell;
    }
}
