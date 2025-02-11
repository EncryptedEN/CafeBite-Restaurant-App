package cafebite.demo;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

/**
 * Controls the movement for chef screen inside
 * the CafeBite app.
 * This controller has functions to make chef go back to the login screen.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class ChefController {

    /**
     * Does things when the chef presses
     * the back button.
     * This method brings the login screen for the chef,
     * making chef stop what he's doing.
     *
     * @param event What the chef did to make this happen, usually clicking a button.
     * @throws IOException If something wrong happens while loading FXML file.
     */
    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLoginPage.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
