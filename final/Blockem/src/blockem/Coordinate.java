package blockem;

/**
 * Created by team_blockem on 6/2/16.
 */
public class Coordinate extends Object {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public boolean equalsCoordinate(Coordinate passedCoordinate) {
        if(passedCoordinate.x == x && passedCoordinate.y == y) {
            return true;
        }
        return false;
    }
}

