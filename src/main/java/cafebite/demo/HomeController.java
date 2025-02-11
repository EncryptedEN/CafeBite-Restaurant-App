package cafebite.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Objects;

/**
 * The HomeController class manages moving from the home screen to the login screens for customers or staff.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class HomeController {

    /**
     * Moves to the Customer Login Page.
     * This function is called when you click the button for customer login on the home screen.
     *
     * @param event The action event that happens when you click the Customer Login button.
     * @throws IOException If the file needed for the customer login screen cannot be loaded.
     */
    @FXML
    private void handleCustomer(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerLogInPage.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Moves to the Staff Login Page.
     * This function is called when you click the button for staff login on the home screen.
     *
     * @param event The action event that happens when you click the Staff Login button.
     * @throws IOException If the file needed for the staff login screen cannot be loaded.
     */
    @FXML
    private void handleStaff(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLoginPage.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
