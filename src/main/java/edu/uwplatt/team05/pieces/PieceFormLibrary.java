package edu.uwplatt.team05.pieces;

/**
 * Class containing information about the forms that a piece can take and how
 * they are represented
 * @author: Reese
 */
public class PieceFormLibrary {
    // Format is [orientIndex][pointIndex][dimIndex]
    private int[][][] iPieceForms;
    private int[][][] jPieceForms;
    private int[][][] lPieceForms;
    private int[][][] oPieceForms;
    private int[][][] sPieceForms;
    private int[][][] tPieceForms;
    private int[][][] zPieceForms;

    /**
     * Default constructor
     */
    public PieceFormLibrary() {
        iPieceForms = new int[][][]{
                {{1, 0}, {1, 1}, {1, 2}, {1, 3}},
                {{3, 1}, {2, 1}, {1, 1}, {0, 1}},
                {{1, 3}, {1, 2}, {1, 1}, {1, 0}},
                {{0, 1}, {1, 1}, {2, 1}, {3, 1}}
        };
        jPieceForms = new int[][][]{
                {{1, 0}, {1, 1}, {1, 2}, {0, 2}},
                {{2, 1}, {1, 1}, {0, 1}, {0, 0}},
                {{1, 2}, {1, 1}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}, {2, 2}}
        };
        lPieceForms = new int[][][]{
                {{1, 0}, {1, 1}, {1, 2}, {2, 2}},
                {{2, 1}, {1, 1}, {0, 1}, {0, 2}},
                {{1, 2}, {1, 1}, {1, 0}, {0, 0}},
                {{0, 1}, {1, 1}, {2, 1}, {2, 0}}
        };
        oPieceForms = new int[][][]{
                {{0, 0}, {1, 0}, {1, 1}, {0, 1}},
                {{0, 0}, {1, 0}, {1, 1}, {0, 1}},
                {{0, 0}, {1, 0}, {1, 1}, {0, 1}},
                {{0, 0}, {1, 0}, {1, 1}, {0, 1}}
        };
        sPieceForms = new int[][][]{
                {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
                {{2, 1}, {1, 1}, {1, 2}, {0, 2}},
                {{1, 2}, {1, 1}, {0, 1}, {0, 0}},
                {{0, 2}, {1, 2}, {1, 1}, {2, 1}}
        };
        tPieceForms = new int[][][]{
                {{2, 1}, {1, 1}, {1, 2}, {0, 1}},
                {{1, 2}, {1, 1}, {0, 1}, {1, 0}},
                {{0, 1}, {1, 1}, {1, 0}, {2, 1}},
                {{1, 0}, {1, 1}, {2, 1}, {1, 2}}
        };
        zPieceForms = new int[][][]{
                {{2, 0}, {2, 1}, {1, 1}, {1, 2}},
                {{2, 2}, {1, 2}, {1, 1}, {0, 1}},
                {{1, 2}, {1, 1}, {2, 1}, {2, 0}},
                {{0, 1}, {1, 1}, {1, 2}, {2, 2}}
        };
    }

    /**
     * Returns the piece form
     *
     * @param pieceType   The type of piece
     * @param orientIndex The orientation index
     * @return returns the piece form
     */
    public int[][] getPieceForm(PieceTypes pieceType, int orientIndex) {
        int[][] value;
        switch (pieceType) {
            case I:
                value = iPieceForms[orientIndex];
                break;
            case J:
                value = jPieceForms[orientIndex];
                break;
            case L:
                value = lPieceForms[orientIndex];
                break;
            case O:
                value = oPieceForms[orientIndex];
                break;
            case S:
                value = sPieceForms[orientIndex];
                break;
            case T:
                value = tPieceForms[orientIndex];
                break;
            case Z:
                value = zPieceForms[orientIndex];
                break;
            default:
                value = zPieceForms[orientIndex];
        }
        return value;
    }
}
