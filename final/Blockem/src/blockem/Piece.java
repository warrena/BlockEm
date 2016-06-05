package blockem;


import java.util.ArrayList;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by Sam on 5/26/16.
 */
public class Piece {
    // a list of all the possible coordinates (for different rotations)
    ArrayList<ArrayList<Coordinate>> rotations;

    // number of blocks in piece
    int size;
    String name;


    /**
     * Sole constructor to initialize piece. Initializes
     * the coodinates and calculates the size of the piecesg
     * @param passedCoordinates a list of lists of coordinates for the various rotations of a piece
     */
    public Piece(ArrayList<ArrayList<Coordinate>> passedCoordinates, String name) {
        rotations = passedCoordinates;
        size = passedCoordinates.get(0).size();
        this.name = name;
    }

    /**
     * Takes in a list of coordinates and checks if it contains a match for a single passed coordinate
     * @param list a list of lists of coordinates for the various rotations of a piece
     * @param coordinate a list of lists of coordinates for the various rotations of a piece
     * @return a boolean: true if contains, false otherwise
     */
    private boolean containsCoordinate(ArrayList<Coordinate> list, Coordinate coordinate) {
        for(Coordinate listItem: list) {
            if(listItem.equalsCoordinate(coordinate)) {
                return true;
            }
        }
        return false;
    }


    /**
     * checks whether the passed Coordinates match the piece, for all rotations
     * @param clicks coordinates of a players click
     * @return a boolean: true if matches the piece, false otherwise
     */
    public boolean isPiece(ArrayList<Coordinate> clicks) {
        // if the piece is the right size
        if (clicks.size() == size) {
            // for each rotation
            for (ArrayList<Coordinate> rotation : rotations) {
                // check if all blocks match, if so true, if not repeat for next rotation
                boolean matches = true;
                for (Coordinate click : clicks) {
                    // If the rotation doesn't contain the coordinate
                    if (!containsCoordinate(rotation, click)) {
                        matches = false;
                    }
                }
                if (matches) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns the size of the piece
     * @return  the number of blocks in the piece
     */
    public int getSize() {
        return size;
    }

    public ArrayList<ArrayList<Coordinate>> getRotations() { return rotations; }

}
