/**
 * Created by Sam, Allie and Josh
 * This is the a Coordinate class that represents x,y coordinates
 * in the game board matrix (found in Board).
 */
package blockem;

public class Coordinate extends Object {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if the coordinates of another object
     * matches the x,y coordinates of this Coordinate
     * @param someObject Coordinate which is compared to this Coordinate
     * @return returns true if the coordinates are the same
     */
    @Override
    public boolean equals(Object someObject) {
        if (someObject instanceof Coordinate) {
            Coordinate coordinate = (Coordinate)someObject;
            if(coordinate.x == x && coordinate.y == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the coordinates of another Coordinate
     * matches the x,y coordinates of this Coordinate
     * @param passedCoordinate Coordinate which is compared to this Coordinate
     * @return returns true if the coordinates are the same
     */
    public boolean equalsCoordinate(Coordinate passedCoordinate) {
        if(passedCoordinate.x == x && passedCoordinate.y == y) {
            return true;
        }
        return false;
    }
}

