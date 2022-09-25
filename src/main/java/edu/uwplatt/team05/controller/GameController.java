package edu.uwplatt.team05.controller;

import edu.uwplatt.team05.FieldSquare;
import edu.uwplatt.team05.GameState;
import edu.uwplatt.team05.TetrisApplication;
import edu.uwplatt.team05.TetrisController;
import edu.uwplatt.team05.pieces.Piece;
import edu.uwplatt.team05.pieces.PieceTypes;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static edu.uwplatt.team05.TetrisController.LEFT_ARROW_PRESSED;
import static edu.uwplatt.team05.TetrisController.RIGHT_ARROW_PRESSED;

/**
 * This class interacts with the TetrisController class and handles game logic
 * @author: Reese, Sean, Jon, Blake
 */
public class GameController {
    private TetrisController tetrisController;
    private GameState gameState;
    private int currentPieceType;
    private int score;

    /**
     * Default constructor
     */
    public GameController(TetrisController tetrisController) {
        this.tetrisController = tetrisController;
        gameState = new GameState(10, 20, new Piece(3, 0,
                0, PieceTypes.J));
        currentPieceType = 0;
    }

    public GameController(TetrisController tetrisController, GameState gameState) {
        this.tetrisController = tetrisController;
        this.gameState = gameState;
        currentPieceType = 0;
    }

    /**
     * Starts a new game of tetris
     */
    public void runGame() {
        gameState = new GameState(10, 20, new Piece(3, 0,
                0, PieceTypes.J));
        currentPieceType = 0;
        score = 0;
    }

    /**
     * Performs a series of tasks on the fall timer tick. It determines if the
     * piece is grounded. If the piece is grounded, it is locked and a new piece
     * is loaded. If the piece is not grounded, it is moved down. Then,the
     * playing field is redrawn.
     */
    public void onFallTimerTick() {
        Piece piece = gameState.getCurrentPiece();

        boolean previouslyGrounded = piece.isGrounded();
        boolean grounded = determineIfGrounded();
        boolean gameEnd = determineIfGameEnd();
        boolean gameOverWinOpen = false;

        if(gameEnd) {
            TetrisApplication.stopped = true;
            try {
                if (!gameOverWinOpen) {
                    tetrisController.gameOver();
                    gameOverWinOpen = true;
                }
            }
            catch(IOException e){
                System.out.println("tetrisController.gameOver() failed to run.");
            }
        }
        
        // Perform gravity logic
        if (grounded) {
            piece.setGroundTime(piece.getGroundTime() - 1);
            // If the piece has just landed, stop the soft drop if necessary.
            if (!previouslyGrounded)
                stopSoftDrop();
        }
        else {
            // If the piece has just left the ground, reset ground timer
            // to low value and make move reset available.
            if (previouslyGrounded) {
                piece.setGroundTime(2);
                piece.setMoveResetAvailable(true);
            }
        }

        // Lock or move piece
        if (piece.getGroundTime() <= 0) {
            lockPiece();
            loadNextPiece();
            tetrisController.startNormalTimer(); // Ensures that new piece is not soft dropped
        }
        else {
            movePieceDown();
        }

        clearLines();
        tetrisController.redrawField(gameState);
    }

    /**
     *  Returns true if the square at (x, y) is open.
     *  Locations outside the playing field are not open.
     *  Squares occupied by dropped pieces are not open.
     * @param: x The x coordinate
     * @param: y The y coordinate
     * @return: returns if the square is open or not
     */
    public boolean isSquareOpen(int x, int y) {
        boolean squareOpen = true;
        FieldSquare[][] playingField = gameState.getFieldSquares();
        if (x < 0 || playingField.length <= x) {
            squareOpen = false;
        }
        else if (y < 0 || playingField[0].length <= y) {
            squareOpen = false;
        }
        else if (!playingField[x][y].getIsOpen()) {
            squareOpen = false;
        }
        return squareOpen;
    }

    public void startSoftDrop() {
        Piece piece = gameState.getCurrentPiece();
        tetrisController.startSoftDropTimer();
        piece.setSoftDropped(true);
    }

    public void stopSoftDrop() {
        Piece piece = gameState.getCurrentPiece();
        if (piece.isSoftDropped())
            tetrisController.startNormalTimer();
        piece.setSoftDropped(false);
    }

    /**
     * Moves the piece downward
     */
    private void movePieceDown() {
        tryMovePiece(0, 1, 0);
    }

    /**
     * Moves the piece to the left
     */
    public void movePieceLeft() {
        stopSoftDrop();
        tryMovePiece(-1, 0, 0);
    }

    /**
     * Moves the piece to the right
     */
    public void movePieceRight() {
        stopSoftDrop();
        tryMovePiece(1, 0, 0);
    }

    /**
     * Rotates the piece in the given direction.
     * @param rotateDirection direction to rotate the piece
     */
    public void rotatePiece(RotateDirection rotateDirection) {
        stopSoftDrop();
        switch (rotateDirection) {
            case CLOCKWISE -> tryMovePiece(0, 0, 1);
            default -> tryMovePiece(0, 0, -1); // COUNTERCLOCKWISE
        }
    }

    /**
     * Tries to move the piece according to the given transformations. If the new piece state has collisions with
     * dropped pieces or edges of the play field, the original state is restored. If the piece is moved, the play
     * field is redrawn. If the piece is moved while it is grounded and the lock delay move reset is available,
     * it is used.
     * @param deltaX change in X position of piece
     * @param deltaY change in Y position of piece
     * @param deltaOrientation change in orientation of piece
     * @return the condition of whether the piece was moved as a boolean
     */
    public boolean tryMovePiece(int deltaX, int deltaY, int deltaOrientation) {
        boolean open = true;
        Piece piece = gameState.getCurrentPiece();

        // Save original state
        int originalX = piece.getOriginX();
        int originalY = piece.getOriginY();
        int originalOrientation = piece.getOrientation();

        // Replace with new state
        piece.setOriginX(originalX + deltaX);
        piece.setOriginY(originalY + deltaY);
        piece.setOrientation(Math.floorMod(originalOrientation + deltaOrientation, 4));

        // Get new absolute positions
        int[][] absolutePoints = piece.getAbsolutePoints();

        // Test all positions
        for (int i = 0; i < absolutePoints.length; i++)
            open = open && isSquareOpen(absolutePoints[i][0], absolutePoints[i][1]);

        if (open) {
            // If the piece was moved, redraw the play field.
            tetrisController.redrawField(gameState);
            // If piece was moved while it is grounded and the lock delay move reset is available, use it.
            if (piece.isGrounded() && piece.isMoveResetAvailable()) {
                piece.setGroundTime(8);
                piece.setMoveResetAvailable(false);
            }
        }
        else {
            // Restore original state if new place not open.
            piece.setOriginX(originalX);
            piece.setOriginY(originalY);
            piece.setOrientation(originalOrientation);
        }

        return open;
    }

    /**
     *  Determines if the piece has been grounded.
     *  Sets the piece's grounded boolean accordingly.
     *  @return: Returns the condition of whether the piece is grounded.
     */
    public boolean determineIfGrounded() {
        Piece piece = gameState.getCurrentPiece();
        int[][] piecePoints = piece.getAbsolutePoints();
        boolean grounded = false;

        // Check if any of the squares below the piece's squares are not open.
        for (int i = 0; i < piecePoints.length; i++) {
            if (!isSquareOpen(piecePoints[i][0], piecePoints[i][1] + 1)) {
                grounded = true;
            }
        }

        // Set piece.grounded and return grounded.
        piece.setGrounded(grounded);
        return grounded;
    }

    /**
     * Determines if the game is over.
     * Sets a gameEnd boolean accordingly.
     * @return: Returns the condition of whether the game is over.
     */
    public boolean determineIfGameEnd() {
        boolean grounded = determineIfGrounded();
        Piece piece = gameState.getCurrentPiece();
        int[][] piecePoints = piece.getAbsolutePoints();
        boolean gameEnd = false;

        if(grounded) {
            for (int i = 0; i < piecePoints.length; i++) {
                if ((piecePoints[i][1] - 1) == 0) {
                    gameEnd = true;
                }
            }
        }
        // return gameEnd.
        return gameEnd;
    }

    /**
     *  Locks piece into the playing field
     */
    public void lockPiece() {
        int[][] points = gameState.getCurrentPiece().getAbsolutePoints();
        for(int i =0; i < 4; i++) {
            gameState.getFieldSquares()[points[i][0]][points[i][1]]
                    .setIsOpen(false);
            gameState.getFieldSquares()[points[i][0]][points[i][1]]
                    .setColor(gameState.getCurrentPiece().getColor());
        }
    }

    /**
     * Loads the next piece with a random orientation and piece type.
     */
    private void loadNextPiece() {
        int orientation = (int)(4 * Math.random());
        int currentPieceType = (int)(7 * Math.random());
        gameState.setCurrentPiece(new Piece(3, 0, orientation,
                numToPieceType(currentPieceType)));
    }

    /**
     * Returns the PieceTypes value corresponding to the given value.
     * @param pieceTypeNum The piece type number
     * @return returns the piece type
     */
    private PieceTypes numToPieceType(int pieceTypeNum) {
        PieceTypes pieceType = PieceTypes.I;
        switch (pieceTypeNum) {
            case 0:
                pieceType = PieceTypes.I;
                break;
            case 1:
                pieceType = PieceTypes.J;
                break;
            case 2:
                pieceType = PieceTypes.L;
                break;
            case 3:
                pieceType = PieceTypes.O;
                break;
            case 4:
                pieceType = PieceTypes.S;
                break;
            case 5:
                pieceType = PieceTypes.T;
                break;
            case 6:
                pieceType = PieceTypes.Z;
        }
        return pieceType;
    }

    /**
     * Clears lines from the board
     */
    public void clearLines()
    {
        boolean rowFull = true;
        int rowsCleared = 0;
        FieldSquare[][] playingField = gameState.getFieldSquares();
        for(int i = 0; i < playingField[0].length; i++)
        {
            rowFull = true;
            for(int j = 0; j < playingField.length; j++)
            {
                if(isSquareOpen(j, i))
                {
                    rowFull = false;
                }
            }

            if(rowFull)
            {
                playingField = clearRow(playingField, i);
                rowsCleared++;
            }
        }
        score += rowsCleared;
    }

    /**
     * Clears the row
     * @param field The field square matrix
     * @param row The row we wish to clear
     * @return returns the cleared row field
     */
    public FieldSquare[][] clearRow(FieldSquare[][] field, int row)
    {
        for(int i = row; i > 0; i--)
        {
            for(int j = 0; j < field.length; j++)
            {
                field[j][i] = field[j][i-1];
            }
        }
        for(int l = 0; l < field.length; l++)
        {
            field[l][0] = new FieldSquare();
        }
        return field;
    }

    public int getScore()
    {
        return score;
    }

    public String getScoreAsString()
    {
        String scoreString = Integer.toString(score);
        return scoreString;
    }

    public GameState getGameState()
    {
        return gameState;
    }
}
