package blockem;

/**
 * Created by team_blockem on 6/2/16.
 */
public class Coordinate extends Object {
    int x;
    int y;
    Player player;

    public Coordinate(int x, int y) {
        x = x;
        y = y;
    }

    @Override
    public boolean equals(Object someObject) {
        if (someObject instanceof Coordinate) {
            Coordinate coordinate = (Coordinate)someObject;
            if(coordinate.x == x && coordinate.x == y) {
                return true;
            }
        }
        return false;
    }
}

