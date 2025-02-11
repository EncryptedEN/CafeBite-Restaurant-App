package cafebite.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * This class sets up the main stage of the application and loads the initial screen.
 * It also applies a CSS style to make the application look better.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Starts the main window of the application, loads the first screen, and applies style.
     * @param primaryStage The main window of the application where everything is shown.
     * @throws Exception If there is a problem loading the files needed for the start screen or style.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the initial FXML for the home scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));

        // Create a scene with the loaded FXML
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));

        // Apply the CSS stylesheet to the scene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        // Set the scene to the stage and configure stage properties
        primaryStage.setScene(scene);
        primaryStage.setTitle("CafeBite Home");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * This method starts the application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
