package edu.uwplatt.team05.utils;

import edu.uwplatt.team05.FieldSquare;
import edu.uwplatt.team05.GameState;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jonathan Olson
 * This class tests the setRow method of GameState.java
 */

class GameStateTest {
    GameState gs;

    @Test
    public void testSetRow() {
        gs = new GameState(10, 20, new Piece(0,0, 1, PieceTypes.I));

        FieldSquare zero = new FieldSquare(false, Color.BLUE);
        FieldSquare one = new FieldSquare(false, Color.BLUE);
        FieldSquare two = new FieldSquare(false, Color.BLUE);
        FieldSquare three = new FieldSquare(false, Color.BLUE);
        FieldSquare four = new FieldSquare(false, Color.BLUE);
        FieldSquare five = new FieldSquare(false, Color.BLUE);
        FieldSquare six = new FieldSquare(false, Color.BLUE);
        FieldSquare seven = new FieldSquare(false, Color.BLUE);
        FieldSquare eight = new FieldSquare(false, Color.BLUE);
        FieldSquare nine = new FieldSquare(false, Color.BLUE);

        FieldSquare[] testRow = {zero, one, two, three, four, five, six, seven, eight, nine};

        gs.setRow(testRow, 0);

        Assertions.assertArrayEquals(testRow, gs.getRow(0));
    }
}