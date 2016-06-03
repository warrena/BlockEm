package pong;

/**
 * Created by team_blockem on 6/2/16.
 */
public class Coordinate {
    int x;
    int y;
    Player player;

    public Coordinate(int x, int y) {
        x = x;
        y = y;
    }

    public boolean equals(Coordinate coordinate) {
        if(coordinate.x == x && coordinate.y == y) {
            return true;
        }
        return false;
    }
}

