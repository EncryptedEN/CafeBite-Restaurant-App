package cafebite.demo;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * Manages the navigation for staff-related screens within the application.
 * Handles user interactions to go back to the home screen or to the staff login screen.
 * author: Emmanuel Nwokoro
 * version: 1.0
 */
public class StaffPageController {

    /**
     * Takes the user back to the home page.
     *
     * @param event the action event that starts this method. Uses the event to find which stage to show the home page on.
     */
    public void handleBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // Load the home page FXML
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            Scene scene = new Scene(root);

            // Add the CSS file to the scene
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

            // Set and show the scene on the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an IOException occurs
        }
    }

    /**
     * Changes the screen to the staff login page.
     *
     * @param event the action event that starts this method.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login_staff.fxml"))));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the screen to the home page.
     *
     * @param event the action event that starts this method.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    public void switchToView(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
        stage.setScene(scene);
        stage.show();
    }
}
