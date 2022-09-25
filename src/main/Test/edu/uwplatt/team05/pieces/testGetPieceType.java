package edu.uwplatt.team05.pieces;
import edu.uwplatt.team05.GameState;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Sean Maidana Dollanarte
 * This class tests the piece types and whether they are being set and
 * retrieved properly
 */

public class testGetPieceType {
    Piece pieceO = new Piece(5, 3, 0, PieceTypes.O);
    Piece pieceJ = new Piece(5, 3, 0, PieceTypes.J);
    Piece pieceL = new Piece(5, 3, 0, PieceTypes.L);
    Piece pieceI = new Piece(5, 3, 0, PieceTypes.I);
    Piece pieceS = new Piece(5, 3, 0, PieceTypes.S);
    Piece pieceT = new Piece(5, 3, 0, PieceTypes.T);
    Piece pieceZ = new Piece(5, 3, 0, PieceTypes.Z);

    // We will pass a Piece object with PieceType J into our GameState
    // constructor to create it, but it does not really matter since we are
    // testing the set and get methods for the current piece to see if our
    // piece type was set properly.
    GameState gs = new GameState(10, 20, pieceJ);

    @Test
    void testGetPieceTypeO()
    {
        gs.setCurrentPiece(pieceO);
        Assertions.assertEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type O
        Assertions.assertNotEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());

    }

    @Test
    void testGetPieceTypeJ()
    {
        gs.setCurrentPiece(pieceJ);
        Assertions.assertEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type J
        Assertions.assertNotEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());
    }

    @Test
    void testGetPieceTypeL()
    {
        gs.setCurrentPiece(pieceL);
        Assertions.assertEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type L
        Assertions.assertNotEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());
    }

    @Test
    void testGetPieceTypeI()
    {
        gs.setCurrentPiece(pieceI);
        Assertions.assertEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type I
        Assertions.assertNotEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());

    }

    @Test
    void testGetPieceTypeS()
    {
        gs.setCurrentPiece(pieceS);
        Assertions.assertEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type S
        Assertions.assertNotEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());

    }

    @Test
    void testGetPieceTypeT()
    {
        gs.setCurrentPiece(pieceT);
        Assertions.assertEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type T
        Assertions.assertNotEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());
    }

    @Test
    void testGetPieceTypeZ()
    {
        gs.setCurrentPiece(pieceZ);
        Assertions.assertEquals(PieceTypes.Z, gs.getCurrentPiece().getPieceType());

        // We will test now with expected piece types that are not piece type Z
        Assertions.assertNotEquals(PieceTypes.O, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.J, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.L, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.I, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.S, gs.getCurrentPiece().getPieceType());
        Assertions.assertNotEquals(PieceTypes.T, gs.getCurrentPiece().getPieceType());
    }

}
