package edu.uwplatt.team05.controller;

import edu.uwplatt.team05.utils.Utils;

/**
 * Enumeration class containing the rotation that the block can go
 * @author: Reese
 */
public enum RotateDirection {
    COUNTERCLOCKWISE,
    CLOCKWISE;

    @Override
    public String toString(){return Utils.convertEnumString(this.name());}
}
