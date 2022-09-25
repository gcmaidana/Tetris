package edu.uwplatt.team05;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Sean Maidana Dollanarte
 * This class tests the field square sizes and verifies the field square is
 * being set and gotten properly since it is a 2D array
 */

public class testFieldSquareSize {

    Piece pieceZ = new Piece(5, 3, 0, PieceTypes.Z);
    GameState gs = new GameState(10, 20, pieceZ);

    @Test
    void testFieldSizeXLength()
    {

        Assertions.assertEquals(10, gs.getFieldSquares().length);

        Assertions.assertNotEquals(20, gs.getFieldSquares().length);
        Assertions.assertNotEquals(7, gs.getFieldSquares().length);
    }

    @Test
    void testFieldSquareSizeYLength()
    {
        // We do gs.getFieldSquares()[0] to find the length of the 2nd dimension
        // of our array.
        Assertions.assertEquals(20, gs.getFieldSquares()[0].length);

        Assertions.assertNotEquals(10, gs.getFieldSquares()[0].length);
        Assertions.assertNotEquals(7, gs.getFieldSquares()[0].length);
    }

}
