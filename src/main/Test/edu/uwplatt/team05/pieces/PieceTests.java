package edu.uwplatt.team05.pieces;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTests {
    @Test
    public void testColorIsCorrectForO()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.O);
        if(piece.getColor() == Color.RED)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }

    @Test
    public void testColorIsCorrectForJ()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.J);
        if(piece.getColor() == Color.GRAY)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }

    @Test
    public void testColorIsCorrectForL()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.L);
        if(piece.getColor() == Color.YELLOW)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }

    @Test
    public void testColorIsCorrectForI()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.I);
        if(piece.getColor() == Color.BROWN)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }

    @Test
    public void testColorIsCorrectForS()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.S);
        if(piece.getColor() == Color.GREEN)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }

    @Test
    public void testColorIsCorrectForT()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.T);
        if(piece.getColor() == Color.BLUE)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }

    @Test
    public void testColorIsCorrectForZ()
    {
        boolean colorCorrect = false;
        Piece piece = new Piece(0, 3, 0, PieceTypes.Z);
        if(piece.getColor() == Color.PINK)
        {
            colorCorrect = true;
        }
        assertTrue(colorCorrect);
    }
}
