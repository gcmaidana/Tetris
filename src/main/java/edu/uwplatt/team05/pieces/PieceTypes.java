package edu.uwplatt.team05.pieces;

import edu.uwplatt.team05.utils.Utils;

/**
 * Enumeration class containing the different types of pieces
 * @author: Tim
 */
public enum PieceTypes {
    O,
    J,
    L,
    I,
    S,
    T,
    Z;

    @Override
    public String toString(){return Utils.convertEnumString(this.name());}
}
