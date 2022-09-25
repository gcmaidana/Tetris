package edu.uwplatt.team05.controller;

import edu.uwplatt.team05.utils.Utils;

/**
 * Enumeration class containing the directions the block can go towards
 * @author: Reese
 */
public enum TranslateDirection {
    LEFT,
    RIGHT;

    @Override
    public String toString(){return Utils.convertEnumString(this.name());}
}
