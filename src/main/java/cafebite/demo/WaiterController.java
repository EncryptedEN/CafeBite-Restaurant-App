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
 * Controls the actions of the waiter interface.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class WaiterController {

    /**
     * Handles the action of navigating back from the waiter interface to the staff login page.
     *
     * @param event the ActionEvent triggered when the "Back" button is clicked
     * @throws IOException if an I/O error occurs while loading the FXML file or setting the scene
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
