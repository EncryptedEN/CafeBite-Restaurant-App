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
 * The ManagerController handles the actions from the manager's interface.
 * This class manages the screens for viewing staff details and going back to the staff login screen.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class ManagerController {

    /**
     * Shows the staff details screen.
     * This method loads the staff details scene when the action is triggered.
     *
     * @param event The event that starts this method. It comes from user's action.
     * @throws IOException If an error happens when loading the file.
     */
    @FXML
    private void handleViewStaffDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaffDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Staff Details");
        stage.show();
    }

    /**
     * Navigates back to the staff login screen.
     * This method changes the current screen to the staff login screen after an action is triggered.
     *
     * @param event The event that starts this method. It is caused by user's action.
     * @throws IOException If an error happens when loading the file.
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