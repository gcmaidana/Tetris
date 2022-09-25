package edu.uwplatt.team05.pieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class containing information about a block piece
 * @author: Reese, Tim
 */
public class Piece {


    private int originX;
    private int originY;
    private int orientation;
    private PieceTypes pieceType;
    private Color color;
    private boolean softDropped;
    private boolean moveResetAvailable;
    private boolean grounded;
    private int groundTime;
    private PieceFormLibrary pieceFormLibrary;

    /**
     * Default constructor
     */
    public Piece(int originX, int originY, int orientation,
                 PieceTypes pieceType) {


        this.originX = originX;
        this.originY = originY;
        this.orientation = orientation;
        this.pieceType = pieceType;
        softDropped = false;
        moveResetAvailable = true;
        grounded = false;
        groundTime = 5;
        pieceFormLibrary = new PieceFormLibrary();

        switch(pieceType) {
            case O:
                color = Color.RED;
                break;
            case J:
                color = Color.GRAY;
                break;
            case L:
                color = Color.YELLOW;
                break;
            case I:
                color = Color.BROWN;
                break;
            case S:
                color = Color.GREEN;
                break;
            case T:
                color = Color.BLUE;
                break;
            case Z:
                color = Color.PINK;
                break;
        }
    }

    /**
     * Gets the origin for the X coordinate
     * @return the x coordinate
     */
    public int getOriginX() {
        return originX;
    }

    /**
     * Sets the origin for the X coordinate
     */
    public void setOriginX(int originX) {
        this.originX = originX;
    }

    /**
     * Gets the origin for the Y coordinate
     * @return the Y coordinate
     */
    public int getOriginY() {
        return originY;
    }

    /**
     * Sets the origin for the Y coordinate
     */
    public void setOriginY(int originY) {
        this.originY = originY;
    }

    /**
     * Gets the orientation for the block
     * @return the orientation is returned
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Sets the orientation for the block
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * Gets the piece type
     * @return The piece type is returned
     */
    public PieceTypes getPieceType() {
        return pieceType;
    }

    /**
     * Sets the piece type
     */
    public void setPieceType(PieceTypes pieceType) {
        this.pieceType = pieceType;
    }

    /**
     * get color of the block
     * @return The color of the block is returned
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the color of the block
     * @param color the color to be set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * If the block is dropped by the player or not
     * @return if the player drops the block
     */
    public boolean isSoftDropped() {
        return softDropped;
    }

    /**
     * Set if the block should be dropped
     */
    public void setSoftDropped(boolean softDropped) {
        this.softDropped = softDropped;
    }

    public boolean isMoveResetAvailable() {
        return moveResetAvailable;
    }

    public void setMoveResetAvailable(boolean moveResetAvailable) {
        this.moveResetAvailable = moveResetAvailable;
    }

    /**
     * Checks if the block is at the bottom
     * @return returns if the block is at the bottom or not
     */
    public boolean isGrounded() {
        return grounded;
    }

    /**
     * Sets the block at the bottom using the boolean variable
     */
    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public int getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(int groundTime) {
        this.groundTime = groundTime;
    }

    /**
     * Returns the piece form library
     * @return piece form library is returned
     */
    public PieceFormLibrary getPieceFormLibrary() {
        return pieceFormLibrary;
    }

    /**
     * Gets the absolute points of the piece
     * @return the absolute points of the piece is returned
     */
    public int[][] getAbsolutePoints(){
       int[][] absolutePoints = {{0,0},{0,0},{0,0},{0,0}};

       int[][] pieceForm = pieceFormLibrary.getPieceForm(pieceType, orientation);
       for (int i = 0; i < 4; i++)
       {
           absolutePoints[i][0] = originX + pieceForm[i][0];
           absolutePoints[i][1] = originY + pieceForm[i][1];
       }

       return absolutePoints;
    }
}
