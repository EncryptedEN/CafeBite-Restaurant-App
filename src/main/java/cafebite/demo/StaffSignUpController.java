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
 * Manages the sign-up process for new staff in the application.
 * This controller helps new staff to register by entering their details and choosing their role.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class StaffSignUpController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField staffIdField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> staffTypeComboBox;

    /**
     * Sets up the sign-up screen by filling in the types of staff roles in the drop-down list.
     * This method is called automatically after the FXML file has loaded.
     */
    @FXML
    public void initialize() {
        staffTypeComboBox.getItems().addAll("Waiter", "Chef", "Delivery Driver", "Manager");
    }

    /**
     * Responds to the sign-up button click. It checks if the input is valid, saves the new staff data,
     * and moves to the login screen if the registration is successful.
     *
     * @param event the action event that triggers this method
     * @throws IOException if an error happens during navigation
     */
    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        String staffType = staffTypeComboBox.getValue();
        if (staffType != null) {
            boolean success = Staff.saveStaff(firstNameField.getText(), lastNameField.getText(),
                    staffIdField.getText(), passwordField.getText(), staffType);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Sign-up successful. Please log in.");
                navigateToLogin(event);  // Navigate back to login page after successful sign-up
            } else {
                showAlert(Alert.AlertType.ERROR, "Sign-up failed. Please try again.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Please select a staff type.");
        }
    }

    /**
     * Handles navigation back to the login page when the 'Already a member? Log in here!' hyperlink is clicked.
     *
     * @param event the event that triggers this method
     * @throws IOException if an error occurs during navigation
     */
    @FXML
    private void handleBackToLogin(ActionEvent event) throws IOException {
        navigateToLogin(event);
    }

    /**
     * Moves the user back to the staff login page.
     *
     * @param event the action event that triggers this method
     * @throws IOException if there is an issue loading the FXML file
     */
    private void navigateToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLoginPage.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Shows an alert to the user.
     *
     * @param type the type of alert to display
     * @param content the message to display in the alert
     */
    private void showAlert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type, content);
        alert.showAndWait();
    }
}
