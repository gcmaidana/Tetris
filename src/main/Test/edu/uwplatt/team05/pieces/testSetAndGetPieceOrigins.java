package edu.uwplatt.team05.pieces;

import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Sean Maidana Dollanarte
 * This class tests the pieces by setting and retrieving the X and Y origins
 * of different pieces
 */

public class testSetAndGetPieceOrigins {

    @Test
    void setAndGetPieceOrigins()
    {
        Piece pieceO = new Piece(5, 3, 0, PieceTypes.O);
        Assertions.assertEquals(5, pieceO.getOriginX());
        Assertions.assertEquals(3, pieceO.getOriginY());

        pieceO.setOriginX(6);
        pieceO.setOriginY(8);
        Assertions.assertNotEquals(5, pieceO.getOriginX());
        Assertions.assertNotEquals(3, pieceO.getOriginY());
        Assertions.assertEquals(6, pieceO.getOriginX());
        Assertions.assertEquals(8, pieceO.getOriginY());

    }

}
