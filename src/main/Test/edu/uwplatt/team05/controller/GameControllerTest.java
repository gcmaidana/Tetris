package edu.uwplatt.team05.controller;


import edu.uwplatt.team05.FieldSquare;
import edu.uwplatt.team05.TetrisController;
import edu.uwplatt.team05.controller.GameController;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.GameState;
import edu.uwplatt.team05.pieces.PieceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    TetrisController tc;
    GameController gc;
    Piece piece;

    @BeforeEach
    void setUp() {
        gc = new GameController(tc);
        piece = new Piece(3, 5,1, PieceTypes.L);
    }

    @Test
    void isSquareOpenTest() {
        assertEquals(true, gc.isSquareOpen(5, 8)); // t-016
        assertEquals(true, gc.isSquareOpen(5, 10)); // t-017
        assertEquals(false, gc.isSquareOpen(-1, 10)); // t-018
        assertEquals(true, gc.isSquareOpen(5, 10)); // t-019
        assertEquals(false, gc.isSquareOpen(5, 20)); // t-020
    }

    @Test
    public void testClearLinesForOneFullLine()
    {
        FieldSquare[] newRow = new FieldSquare[10];
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);

        gc.clearLines();
        int score = gc.getScore();

        assertEquals(1, score);
    }

    @Test
    public void testClearLinesForTwoFullLines()
    {
        FieldSquare[] newRow = new FieldSquare[10];
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);
        gc.getGameState().setRow(newRow, 1);

        gc.clearLines();
        int score = gc.getScore();

        assertEquals(2, score);
    }
    @Test
    public void testClearLinesForThreeFullLines()
    {
        FieldSquare[] newRow = new FieldSquare[10];
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);
        gc.getGameState().setRow(newRow, 1);
        gc.getGameState().setRow(newRow, 2);

        gc.clearLines();
        int score = gc.getScore();

        assertEquals(3, score);
    }

    @Test
    public void testClearLinesForFourFullLines()
    {
        FieldSquare[] newRow = new FieldSquare[10];
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);
        gc.getGameState().setRow(newRow, 1);
        gc.getGameState().setRow(newRow, 2);
        gc.getGameState().setRow(newRow, 3);

        gc.clearLines();
        int score = gc.getScore();

        assertEquals(4, score);
    }

    @Test
    public void testClearLinesForBrokenLine()
    {
        FieldSquare[] newRow = new FieldSquare[10];
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            if(i == 4)
            {
                newRow[i].setIsOpen(true);
            }
            else
            {
                newRow[i].setIsOpen(false);
            }
        }
        gc.getGameState().setRow(newRow, 0);

        gc.clearLines();
        int score = gc.getScore();

        assertEquals(0, score);
    }

    @Test
    public void testClearRowForOneFullLine()
    {
        GameController emptyGame = new GameController(tc);
        FieldSquare[] newRow = new FieldSquare[10];
        boolean compareGames = true;
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);

        //clearLines calls clearRow
        gc.clearLines();

        //see if each element is open (should be)
        for(int i = 0; i < gc.getGameState().getFieldSquares().length;i++)
        {
            for(int j = 0; j < gc.getGameState().getFieldSquares()[0].length; j++)
            {
                if(gc.getGameState().getFieldSquares()[i][j].getIsOpen() != emptyGame.getGameState().getFieldSquares()[i][j].getIsOpen())
                    compareGames = false;
            }
        }

        assertTrue(compareGames);
    }

    @Test
    public void testClearRowForTwoFullLines()
    {
        GameController emptyGame = new GameController(tc);
        FieldSquare[] newRow = new FieldSquare[10];
        boolean compareGames = true;
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);
        gc.getGameState().setRow(newRow, 1);

        //clearLines calls clearRow
        gc.clearLines();

        //see if each element is open (should be)
        for(int i = 0; i < gc.getGameState().getFieldSquares().length;i++)
        {
            for(int j = 0; j < gc.getGameState().getFieldSquares()[0].length; j++)
            {
                if(gc.getGameState().getFieldSquares()[i][j].getIsOpen() != emptyGame.getGameState().getFieldSquares()[i][j].getIsOpen())
                    compareGames = false;
            }
        }

        assertTrue(compareGames);
    }

    @Test
    public void testClearRowForThreeFullLines()
    {
        GameController emptyGame = new GameController(tc);
        FieldSquare[] newRow = new FieldSquare[10];
        boolean compareGames = true;
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);
        gc.getGameState().setRow(newRow, 1);
        gc.getGameState().setRow(newRow, 2);

        //clearLines calls clearRow
        gc.clearLines();

        //see if each element is open (should be)
        for(int i = 0; i < gc.getGameState().getFieldSquares().length;i++)
        {
            for(int j = 0; j < gc.getGameState().getFieldSquares()[0].length; j++)
            {
                if(gc.getGameState().getFieldSquares()[i][j].getIsOpen() != emptyGame.getGameState().getFieldSquares()[i][j].getIsOpen())
                    compareGames = false;
            }
        }

        assertTrue(compareGames);
    }

    @Test
    public void testClearRowForFourFullLines()
    {
        GameController emptyGame = new GameController(tc);
        FieldSquare[] newRow = new FieldSquare[10];
        boolean compareGames = true;
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            newRow[i].setIsOpen(false);
        }
        gc.getGameState().setRow(newRow, 0);
        gc.getGameState().setRow(newRow, 1);
        gc.getGameState().setRow(newRow, 2);
        gc.getGameState().setRow(newRow, 3);

        //clearLines calls clearRow
        gc.clearLines();

        //see if each element is open (should be)
        for(int i = 0; i < gc.getGameState().getFieldSquares().length;i++)
        {
            for(int j = 0; j < gc.getGameState().getFieldSquares()[0].length; j++)
            {
                if(gc.getGameState().getFieldSquares()[i][j].getIsOpen() != emptyGame.getGameState().getFieldSquares()[i][j].getIsOpen())
                    compareGames = false;
            }
        }

        assertTrue(compareGames);
    }

    @Test
    public void testClearRowForBrokenLine()
    {
        GameController emptyGame = new GameController(tc);
        FieldSquare[] newRow = new FieldSquare[10];
        boolean compareGames = true;
        for(int i = 0; i < newRow.length; i++)
        {
            newRow[i] = new FieldSquare();
            if(i == 4)
            {
                newRow[i].setIsOpen(true);
            }
            else
            {
                newRow[i].setIsOpen(false);
            }
        }
        gc.getGameState().setRow(newRow, 0);

        //clearLines calls clearRow
        gc.clearLines();

        //see if each element is open (should be)
        for(int i = 0; i < gc.getGameState().getFieldSquares().length;i++)
        {
            for(int j = 0; j < gc.getGameState().getFieldSquares()[0].length; j++)
            {
                if(gc.getGameState().getFieldSquares()[i][j].getIsOpen() != emptyGame.getGameState().getFieldSquares()[i][j].getIsOpen())
                    compareGames = false;
            }
        }

        assertFalse(compareGames);
    }

    @Test
    void startSoftDrop() {
        Piece piece = new Piece(3, 19, 2, PieceTypes.I);
        GameState testState = new GameState(10, 20, piece);
        GameController testController = new GameController(tc, testState);
        testController.stopSoftDrop();
        Assertions.assertNotEquals(true, piece.isSoftDropped());// t-020
    }

    @Test
    void stopSoftDrop() {
        Piece piece = new Piece(3, 19, 2, PieceTypes.I);
        GameState testState = new GameState(10, 20, piece);
        GameController testController = new GameController(tc, testState);
        testController.stopSoftDrop();
        Assertions.assertEquals(false, piece.isSoftDropped());// t-019
    }

    /**
     * @author Jonathan Olson,
     * This test method tests the determineIfGrounded() method from GameController.java when it should return true
     */
    @Test
    void determineIfGroundedTestTRUE() {
        Piece piece = new Piece(3, 19, 2, PieceTypes.I);
        GameState testState = new GameState(10, 20, piece);
        GameController testController = new GameController(tc, testState);

        Assertions.assertEquals(true, testController.determineIfGrounded());
    }

    /**
     * @author Jonathan Olson,
     * This test method tests the determineIfGrounded() method from GameController.java when it should return false
     */
    @Test
    void determineIfGroundedTestFALSE() {
        Piece piece = new Piece(3, 0, 2, PieceTypes.I);
        GameState testState = new GameState(10, 20, piece);
        GameController testController = new GameController(tc, testState);

        Assertions.assertEquals(false, testController.determineIfGrounded());
    }

    /**
     * @author Jonathan Olson,
     * This test method tests the determineIfGameEnd() method from GameController.java when it should return true
     */
    @Test
    void determineIfGameEndTestTRUE() {
        Piece piece = new Piece(-1, 0, 0, PieceTypes.I);
        GameState testState = new GameState(10, 20, piece);
        testState.getFieldSquares()[0][4].setIsOpen(false);
        GameController testController = new GameController(tc, testState);

        Assertions.assertEquals(true, testController.determineIfGameEnd());
    }

    /**
     * @author Jonathan Olson,
     * This test method tests the determineIfGameEnd() method from GameController.java when it should return false
     */
    @Test
    void determineIfGameEndTestFALSE() {
        Piece piece = new Piece(3, 19, 2, PieceTypes.I);
        GameState testState = new GameState(10, 20, piece);
        GameController testController = new GameController(tc, testState);

        Assertions.assertEquals(false, testController.determineIfGameEnd());
    }

}