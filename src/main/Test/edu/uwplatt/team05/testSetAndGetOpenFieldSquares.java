package edu.uwplatt.team05;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Sean Maidana Dollanarte
 * This class tests setting and retrieving field squares as open or not open
 */

// Keep in mind that while the getIsOpen() method for the FieldSquare
// class is similar to IsSquareOpen() in GameController, these set and get
// methods in FieldSquare class are still worth testing because here we are
// testing the set and get methods while in IsSquareOpen in GameController
// we are only using the getIsOpen method (along with checking for out of
// bounds). However, in the IsSquareOpen() method we are not using the set
// method. So we test the set and get methods of FieldSquare here.

public class testSetAndGetOpenFieldSquares {

    Piece pieceZ = new Piece(5, 3, 0, PieceTypes.Z);

    // By default, all the board spaces are open when the game state objected
    // is created. This is important to keep in mind since we are going to
    // set spots to not open to test whether our getIsOpen();
    GameState gs = new GameState(10, 20, pieceZ);


    @Test
    void testSetAndGet()
    {
        Assertions.assertTrue(gs.getFieldSquares()[0][5].getIsOpen());
        gs.getFieldSquares()[0][5].setIsOpen(false);
        Assertions.assertFalse(gs.getFieldSquares()[0][5].getIsOpen());

        Assertions.assertTrue(gs.getFieldSquares()[4][7].getIsOpen());
        gs.getFieldSquares()[4][7].setIsOpen(false);
        Assertions.assertFalse(gs.getFieldSquares()[4][7].getIsOpen());

        Assertions.assertTrue(gs.getFieldSquares()[0][0].getIsOpen());
        gs.getFieldSquares()[0][0].setIsOpen(false);
        Assertions.assertFalse(gs.getFieldSquares()[0][0].getIsOpen());

    }
}
