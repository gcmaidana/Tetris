package edu.uwplatt.team05;

import edu.uwplatt.team05.controller.GameController;
import edu.uwplatt.team05.controller.RotateDirection;
import edu.uwplatt.team05.pieces.Piece;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static edu.uwplatt.team05.TetrisApplication.X_WINDOW_SIZE;
import static edu.uwplatt.team05.TetrisApplication.Y_WINDOW_SIZE;

/**
 * This class interacts with the GameController class to draw and move blocks
 * around the Canvas
 * @author: Sean, Reese
 */
public class TetrisController {
    @FXML
    private Button playButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;
    @FXML
    private Canvas playingFieldCanvas;
    @FXML
    private Pane playingFieldPane;
    @FXML
    private VBox rootVBox;
    @FXML
    private Label scoreLabel;
    @FXML
    private Slider audioSlider;
    @FXML
    private Label audioLabel;
    @FXML
    private Label gameOverScore;


    private GraphicsContext graphicsContext;
    private GameController gameController;
    private Timer fallTimer;
    private Media media;
    private static MediaPlayer mediaPlayer;
    private static boolean musicPlaying = false;
    private static double musicVol = 0.25f;
    private static double changedMusicVol = 0.25f;
    public static boolean LEFT_ARROW_PRESSED = false;
    public static boolean RIGHT_ARROW_PRESSED = false;

    /**
     * This method will play the music if it is the first time the method is being
     * called or it will set the volume if the music is being played already and the
     * volume has been changed in the settings menu. (It is called in both initialize()
     * and in the drag() method.
     * @param mVol This volume parameter is used to check if the volume changed or not
     *             and, it will set the volume if it has changed. The drag() method is where
     *             this parameter is important.
     */
    public void music(double mVol)
    {

        if(!musicPlaying)
        {
            try{
                String fileName = getClass().getClassLoader().getResource("tetris-music.wav").toURI().toString(); // null? why
                media = new Media(fileName);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setVolume(musicVol);
                mediaPlayer.play();
                musicPlaying = true;
            }
            catch (URISyntaxException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            if(musicVol != mVol)
            {
                musicVol = mVol;
                mediaPlayer.setVolume(musicVol * 0.01);
            }

        }



    }

    public static double getMusicVol() {
        return musicVol;
    }

    @FXML
    public void initialize() {
        gameController = new GameController(this);
        fallTimer = null;
        music(musicVol);

    }

    /**
     * Open the game upon clicking the play button
     * @param actionEvent Play button will initiate this actionEvent
     */
    @FXML
    public void playClick(ActionEvent actionEvent) throws IOException, InterruptedException {

        graphicsContext = playingFieldCanvas.getGraphicsContext2D();
        playingFieldPane.setBackground(new Background(
                new BackgroundFill(Color.web("e2e2e2"), CornerRadii.EMPTY,
                        Insets.EMPTY)));
        // rootVBox.setFocusTraversable(true);
        rootVBox.requestFocus();
        rootVBox.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    // Set to false in GameController
                    case LEFT -> gameController.movePieceLeft();
                    case RIGHT -> gameController.movePieceRight();
                    case SPACE -> gameController.startSoftDrop();
                    case UP -> gameController.rotatePiece(RotateDirection.CLOCKWISE);
                    case DOWN -> gameController.rotatePiece(RotateDirection.COUNTERCLOCKWISE);
                }
                keyEvent.consume();
            }
        });

        // Cancel timer task if timer has already been started.
        if (fallTimer != null)
            fallTimer.cancel();

        // Start the fallTimer
        fallTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> onFallTimerTick());
                if (TetrisApplication.stopped)
                    fallTimer.cancel();
            }
        };
        fallTimer.schedule(task, 250l, 250l);

        // Starts the game
        gameController.runGame();
    }

    public void startSoftDropTimer() {
        fallTimer.cancel();
        // Start the fast fallTimer
        fallTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> onFallTimerTick());
                if (TetrisApplication.stopped)
                    fallTimer.cancel();
            }
        };
        fallTimer.schedule(task, 50l, 50l);
    }

    public void startNormalTimer() {
        fallTimer.cancel();
        // Start the slow fallTimer
        fallTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> onFallTimerTick());
                if (TetrisApplication.stopped)
                    fallTimer.cancel();
            }
        };
        fallTimer.schedule(task, 250l, 250l);
    }

    /**
     * Close main menu and open settings window upon pressing this button
     * 
     * @param actionEvent Settings button will initiate this actionEvent
     */
    @FXML
    public void settingsClick(ActionEvent actionEvent) throws IOException {
        // Closes the menu page
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        Parent root;
        root  = FXMLLoader.load(TetrisController.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(root , X_WINDOW_SIZE, Y_WINDOW_SIZE);
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Settings");
        settingsStage.setScene(scene);
        settingsStage.show();



    }

    /**
     * This method handles setting the volume with the slider in the settings page
     * @param mouseEvent This will change the volume after the user release the mouse
     *                   when moving the slider.
     */
    public void drag(MouseEvent mouseEvent) {
        audioSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasChanging, Boolean isNowChanging) {
                if (!isNowChanging) {
                    music(audioSlider.getValue());
                }
            }
        });

    }


    /**
     * Close main menu and open settings window upon pressing this button
     * 
     * @param actionEvent Settings button will initiate this actionEvent
     */
    @FXML
    public void returnClick(ActionEvent actionEvent) throws IOException {
        // Closes the Help page
        TetrisApplication.stopped = false;
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
        Parent root;
        root  = FXMLLoader.load(TetrisController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root , X_WINDOW_SIZE, Y_WINDOW_SIZE);
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Settings");
        settingsStage.setScene(scene);
        settingsStage.show();
    }

    /**
     * Close window upon clicking exit button
     * @param actionEvent Exit button will initiate this actionEvent
     */
    @FXML
    public void exitClick(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Performs some tasks on the fall timer tick
     */
    private void onFallTimerTick() {
        gameController.onFallTimerTick();
    }

    /**
     * Draws shapes on the Canvas
     * @param gameState Draws on the Canvas given the gameState
     */
    public void redrawField(GameState gameState) {
        scoreLabel.setText(gameController.getScoreAsString());
        Piece piece = gameState.getCurrentPiece();
        int[][] piecePoints = piece.getAbsolutePoints();
        FieldSquare[][] playingField = gameState.getFieldSquares();
        int fieldWidth = playingField.length;
        int fieldHeight = playingField[0].length;
        int squareWidth = (int)(playingFieldCanvas.getWidth() / fieldWidth);
        int squareHeight = (int)(playingFieldCanvas.getHeight() / fieldHeight);

        graphicsContext.clearRect(0,0, playingFieldCanvas.getWidth(),
                playingFieldCanvas.getHeight());
        graphicsContext.setFill(piece.getColor());
        for (int i = 0; i < piecePoints.length; i++) {
            int originX = piecePoints[i][0] * squareWidth;
            int originY = piecePoints[i][1] * squareHeight;
            graphicsContext.fillRoundRect(originX, originY, squareWidth,
                    squareHeight, squareWidth / 4, squareHeight / 4);
        }

        // Fill in locked piece squares.
        boolean emptyRowReached = false;
        int r = fieldHeight - 1;
        while (r >= 0 && !emptyRowReached) {
            emptyRowReached = true;
            for (int c = 0; c < fieldWidth; c++) {
                if (!playingField[c][r].getIsOpen()) {
                    emptyRowReached = false;
                    graphicsContext.setFill(playingField[c][r].getColor());
                    graphicsContext.fillRoundRect(c * squareWidth, r * squareHeight, squareWidth, squareHeight,
                            squareWidth / 4, squareHeight / 4);
                }
            }
            r--;
        }

    }

    /**
     * Close main menu and open Game Over window upon calling this function.
     */
    public void gameOver() throws IOException {
        // Closes the menu page
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        Parent root;
        root  = FXMLLoader.load(TetrisController.class.getResource("game-over-view.fxml"));
        Scene scene = new Scene(root , 400, 400);
        Stage settingsStage = new Stage();
        settingsStage.setTitle("GAME OVER");
        settingsStage.setScene(scene);
        settingsStage.show();
        gameOverScore = (Label) root.lookup("#gameOverScore");
        gameOverScore.setText(gameController.getScoreAsString());
    }

}

