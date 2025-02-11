package cafebite.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Objects;

/**
 * Manages the login process for staff members.
 * This class handles interactions on the staff login screen.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class StaffLoginController {

    @FXML
    private TextField staffId;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink signUpHyperlink; // Hyperlink for navigating to the sign-up page.

    /**
     * Handles what happens when the staff login button is clicked.
     * It checks if the staff ID and password are correct.
     * If they are correct, it moves to the staff dashboard.
     * If they are not correct, it shows an error message.
     *
     * @param event the event that triggers this method
     * @throws IOException if there is an issue loading the next screen
     */
    @FXML
    private void handleStaffLogin(ActionEvent event) throws IOException {
        if (Staff.validateLogin(staffId.getText(), password.getText())) {
            String staffType = Staff.getStaffType(staffId.getText());
            String fullName = Staff.getFullName(staffId.getText());
            FXMLLoader loader = new FXMLLoader();

            switch (staffType) {
                case "Waiter":
                    loader.setLocation(getClass().getResource("Waiter.fxml"));
                    break;
                case "Chef":
                    loader.setLocation(getClass().getResource("Chef.fxml"));
                    break;
                case "Delivery Driver":
                    loader.setLocation(getClass().getResource("DeliveryDriver.fxml"));
                    break;
                case "Manager":
                    loader.setLocation(getClass().getResource("Manager.fxml"));
                    break;
                default:
                    showAlert(Alert.AlertType.ERROR, "Invalid Staff Role!");
                    return;
            }

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(fullName); // Set the window title to the user's full name
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Staff ID or password!");
        }
    }

    /**
     * Shows a dialog box with a message.
     * This method is used to display error or information messages.
     *
     * @param type the type of alert to show
     * @param content the message to display in the alert
     */
    private void showAlert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type, content);
        alert.showAndWait();
    }

    /**
     * Handles the action when the 'sign up' hyperlink is clicked.
     * It loads the sign-up screen.
     *
     * @param event the event that triggers this method
     * @throws IOException if there is an issue loading the sign-up screen
     */
    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffSignUpPage.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
