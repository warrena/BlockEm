package blockem;


import java.util.ArrayList;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by Sam on 5/26/16.
 */
public class Piece {
    // a list of all the possible coordinates (for different rotations)
    ArrayList<ArrayList<ArrayList<Integer>>> coordinates;

    // number of blocks in piece
    int size;

    
    /**
    * Sole constructor to initialize piece. Initializes 
    * the coodinates and calculates the size of the piecesg 
    * @param passedCoordinates a list of lists of coordinates for the various rotations of a piece
    */
    public Piece(ArrayList<ArrayList<ArrayList<Integer>>> passedCoordinates) {
        coordinates = passedCoordinates;
        size = passedCoordinates.get(0).size();
    }

    /**
    * checks whether the passed Coordinates match the piece, for all rotations
    * @param passedCoordinates coordinates of a players click
    * @return a boolean: true if matches the piece, false otherwise
    */
    public boolean isPiece(ArrayList<ArrayList<Integer>> passedCoordinates) {
        // if the piece is the right size
        if (passedCoordinates.size() == size) {
            // for each rotation
            for (ArrayList<ArrayList<Integer>> rotation : coordinates) {

                // check if all blocks match, if so true, if not repeat for next rotation
                boolean matches = true;
                for (ArrayList<Integer> coordinate : passedCoordinates) {
                    if (!rotation.contains(coordinate)) {
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

}
