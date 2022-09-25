package edu.uwplatt.team05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Class helps set up the GUI window using the hello-view FXML file, we choose
 * 700 x 700 window size.
 * @author: Sean
 */
public class TetrisApplication extends Application
{
    public static final int X_WINDOW_SIZE = 700; // Default X coordinate size
    public static final int Y_WINDOW_SIZE = 700; // Default Y coordinate size

    // Default variable made by JavaFX to check
    // if window is ongoing
    public static boolean stopped;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        stopped = false;
        FXMLLoader fxmlLoader = new FXMLLoader(TetrisApplication.class
                .getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), X_WINDOW_SIZE, Y_WINDOW_SIZE);
        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        stopped = true;
    }

    public Scene getScene()
    {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}