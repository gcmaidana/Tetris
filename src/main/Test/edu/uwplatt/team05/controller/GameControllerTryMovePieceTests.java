package edu.uwplatt.team05.controller;

import edu.uwplatt.team05.GameState;
import edu.uwplatt.team05.TetrisController;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the tryMovePiece method in GameController.
 * @author Reese Wunderlich
 */
class GameControllerTryMovePieceTests {
    // FOR ALL TESTS:
    // Inputs (things that are used by the method)
    // originX, originY, orientation, deltaX, deltaY, deltaOrientation, pieceType, groundTime, grounded,
    // moveResetAvailable, fieldSquares

    // Outputs (things that might be changed, and thus are values that should be tested)
    // open, originX, originY, orientation, groundTime, moveResetAvailable

    /**
     * This tests the tryMovePiece method for the case of successfully moving a J piece to the left. The move reset
     * should not be used.
     */
    @Test
    void testTryMovePieceLeftNoResetOpenTrue() {
        // Scenario (piece moves)
        // __0    _0_
        // X_0 -> X0_
        // _00    00_

        // Setup (includes constructors and accessors)
        int originalX = 4;
        int originalY = 6;
        int originalOrientation = 0;
        Piece piece = new Piece(originalX, originalY, originalOrientation, PieceTypes.J);
        piece.setGroundTime(2);
        piece.setGrounded(false);
        piece.setMoveResetAvailable(true);
        GameState gameState = new GameState(10, 20, piece);
        gameState.getFieldSquares()[3][7].setIsOpen(false); // This square doesn't get in the way
        TetrisController mockedTetrisController = Mockito.mock(TetrisController.class);
        GameController gameController = new GameController(mockedTetrisController, gameState);

        // Execution
        boolean open = gameController.tryMovePiece(-1, 0, 0);

        // Assertion
        assertEquals(true, open);
        assertEquals(3, piece.getOriginX());
        assertEquals(6, piece.getOriginY());
        assertEquals(0, piece.getOrientation());
        assertEquals(2, piece.getGroundTime());
        assertEquals(true, piece.isMoveResetAvailable());
    }

    /**
     * This tests the tryMovePiece method for the case of trying to move an O piece down and failing due to a square
     * that is not open. The piece does not move, and the move reset should not be used.
     */
    @Test
    void testTryMovePieceDownNoResetOpenFalse() {
        // Scenario (piece doesn't move)
        // 00_    ___
        // 00_ -> 00_
        // _X_    0*_

        // Setup (includes constructors and accessors)
        int originalX = 3;
        int originalY = 5;
        int originalOrientation = 3;
        Piece piece = new Piece(originalX, originalY, originalOrientation, PieceTypes.O);
        piece.setGroundTime(2);
        piece.setGrounded(false);
        piece.setMoveResetAvailable(true);
        GameState gameState = new GameState(10, 20, piece);
        gameState.getFieldSquares()[4][7].setIsOpen(false); // This square gets in the way
        TetrisController mockedTetrisController = Mockito.mock(TetrisController.class);
        GameController gameController = new GameController(mockedTetrisController, gameState);

        // Execution
        boolean open = gameController.tryMovePiece(0, 1, 0);

        // Assertion
        assertEquals(false, open);
        assertEquals(3, piece.getOriginX());
        assertEquals(5, piece.getOriginY());
        assertEquals(3, piece.getOrientation());
        assertEquals(2, piece.getGroundTime());
        assertEquals(true, piece.isMoveResetAvailable());
    }

    /**
     * This tests the tryMovePiece method for the case of successfully rotating a T piece clockwise. The piece is
     * grounded and the move reset is available, so the move reset is used.
     */
    @Test
    void testTryMovePieceRotateCWUseResetOpenTrue() {
        // Scenario (piece moves and move reset is used)
        // ___    _0_
        // 000 -> 00_
        // X0_    X0_

        // Setup (includes constructors and accessors)
        int originalX = 2;
        int originalY = 3;
        int originalOrientation = 0;
        Piece piece = new Piece(originalX, originalY, originalOrientation, PieceTypes.T);
        piece.setGroundTime(2);
        piece.setGrounded(true);
        piece.setMoveResetAvailable(true);
        GameState gameState = new GameState(10, 20, piece);
        gameState.getFieldSquares()[2][5].setIsOpen(false); // This square doesn't get in the way
        TetrisController mockedTetrisController = Mockito.mock(TetrisController.class);
        GameController gameController = new GameController(mockedTetrisController, gameState);

        // Execution
        boolean open = gameController.tryMovePiece(0, 0, 1);

        // Assertion
        assertEquals(true, open);
        assertEquals(2, piece.getOriginX());
        assertEquals(3, piece.getOriginY());
        assertEquals(1, piece.getOrientation());
        assertEquals(8, piece.getGroundTime());
        assertEquals(false, piece.isMoveResetAvailable());
    }

    /**
     * This tests the tryMovePiece method for the case of trying to rotate an S piece counterclockwise and failing due
     * to a square that is not open. The piece does not rotate, and the move reset should not be used.
     */
    @Test
    void testTryMovePieceRotateCCWNoResetOpenFalse() {
        // Scenario (piece doesn't move)
        // 0__    ___
        // 00X -> _0*
        // _0_    00_

        // Setup (includes constructors and accessors)
        int originalX = 3;
        int originalY = 4;
        int originalOrientation = 0;
        Piece piece = new Piece(originalX, originalY, originalOrientation, PieceTypes.S);
        piece.setGroundTime(2);
        piece.setGrounded(true);
        piece.setMoveResetAvailable(true);
        GameState gameState = new GameState(10, 20, piece);
        gameState.getFieldSquares()[5][5].setIsOpen(false); // This square gets in the way
        TetrisController mockedTetrisController = Mockito.mock(TetrisController.class);
        GameController gameController = new GameController(mockedTetrisController, gameState);

        // Execution
        boolean open = gameController.tryMovePiece(0, 0, -1);

        // Assertion
        assertEquals(false, open);
        assertEquals(3, piece.getOriginX());
        assertEquals(4, piece.getOriginY());
        assertEquals(0, piece.getOrientation());
        assertEquals(2, piece.getGroundTime());
        assertEquals(true, piece.isMoveResetAvailable());
    }
}