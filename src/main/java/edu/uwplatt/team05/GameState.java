package edu.uwplatt.team05;

import edu.uwplatt.team05.pieces.Piece;

/**
 * Class containing information about board and helps get and set pieces
 * on a particular spot
 * @author: Tim
 */
public class GameState {
    private Piece currentPiece;
    private FieldSquare [][] fieldSquares;

    /**
     * Default constructor
     */
    public GameState(int x, int y, Piece currentPiece)
    {
        this.currentPiece = currentPiece;
        this.fieldSquares = new FieldSquare[x][y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                fieldSquares[i][j] = new FieldSquare();
            }
        }
    }

    /**
     * Gets the current piece
     * @return The current piece is returned
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Sets the current piece
     * @param currentPiece The piece to be set
     */
    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    /**
     * Returns the field squares
     * @return The field squares are returned as a 2d array
     */
    public FieldSquare[][] getFieldSquares() {
        return fieldSquares;
    }

    /**
     * Returns a row of field squares
     * @param index
     * @return a row of field squares
     */
    public FieldSquare[] getRow(int index)
    {
        FieldSquare[] temp = new FieldSquare[fieldSquares.length];

        for(int i = 0; i < fieldSquares.length; i++)
        {
            temp[i] = fieldSquares[i][index];
        }

        return temp;
    }

    /**
     * Sets the row of field squares to a new row provided
     * @param newRow
     * @param index
     */
    public void setRow(FieldSquare[] newRow, int index)
    {
        for(int i = 0; i < fieldSquares.length; i++)
        {
            fieldSquares[i][index] = newRow[i];
        }
    }
}
