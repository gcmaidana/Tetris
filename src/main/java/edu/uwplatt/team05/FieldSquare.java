package edu.uwplatt.team05;

import javafx.scene.paint.Color;

/**
 * Class containing information about the the field square, which is essentially
 * logic for the game board (which spots are open, etc.)
 * @author: Jon
 */
public class FieldSquare
{
    boolean isOpen;
    Color color;

    /**
     * Default constructor
     */
    public FieldSquare()
    {
        this.isOpen = true;
        this.color = null;
    }

    /**
     * Default constructor
     * @param i Sets whether the spot is open
     * @param c  Sets the color
     */
    public FieldSquare(boolean i, Color c)
    {
        this.isOpen = i;
        this.color = c;
    }

    /**
     * Returns if the spot is open
     * @return returns true if open, false if not
     */
    public boolean getIsOpen()
    {
        return isOpen;
    }

    /**
     * Returns the color
     * @return The color is returned
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Sets spot to open or not
     * @param o O is if the spot is open or not and is specified by caller
     */
    public void setIsOpen(boolean o)
    {
        this.isOpen = o;
    }

    /**
     * Sets the color
     * @param color The specified color to be set
     */
    public void setColor(Color color)
    {
        this.color = color;
    }
}
