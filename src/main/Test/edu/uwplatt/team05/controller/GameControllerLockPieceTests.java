package edu.uwplatt.team05.controller;

import edu.uwplatt.team05.GameState;
import edu.uwplatt.team05.TetrisController;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the lockPiece method in GameController.
 * @author Reese Wunderlich
 */
class GameControllerLockPieceTests {

    /**
     * This tests whether lockPiece successfully fills in the squares in fieldSquares that correspond to the squares in
     * the piece. This includes setting open to false and setting the color of the field square.
     */
    @Test
    void testLockPieceSuccess() {
        // Scenario
        // __0
        // _00
        // _0_

        // Setup (includes constructors and accessors)
        int originalX = 4;
        int originalY = 3;
        int originalOrientation = 2;
        Piece piece = new Piece(originalX, originalY, originalOrientation, PieceTypes.Z);
        Color color = piece.getColor();
        GameState gameState = new GameState(10, 20, piece);
        TetrisController mockedTetrisController = Mockito.mock(TetrisController.class);
        GameController gameController = new GameController(mockedTetrisController, gameState);

        // Execution
        gameController.lockPiece();

        // Assertion
        assertEquals(true, gameState.getFieldSquares()[4][3].getIsOpen());
        assertEquals(true, gameState.getFieldSquares()[3][6].getIsOpen());
        assertEquals(false, gameState.getFieldSquares()[6][3].getIsOpen());
        assertEquals(false, gameState.getFieldSquares()[5][4].getIsOpen());
        assertEquals(false, gameState.getFieldSquares()[6][4].getIsOpen());
        assertEquals(false, gameState.getFieldSquares()[5][5].getIsOpen());
        assertEquals(color, gameState.getFieldSquares()[6][3].getColor());
        assertEquals(color, gameState.getFieldSquares()[5][4].getColor());
        assertEquals(color, gameState.getFieldSquares()[6][4].getColor());
        assertEquals(color, gameState.getFieldSquares()[5][5].getColor());
    }
}