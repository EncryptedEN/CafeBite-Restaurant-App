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
 * Control customer log-in actions in CafeBite.
 * Handles log-in attempts, sign-up navigation, and returning to the home page.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class CustomerLoginController {
    @FXML private TextField username;
    @FXML private PasswordField password;

    /**
     * Trying to confirm user using given data.
     * If good, move to order or booking interface; else, alert for not correct login.
     *
     * @param event What happened, usually pressing a button.
     * @throws IOException If there is a problem with moving pages.
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        if (Customer.validateLogin(username.getText(), password.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MakeOrderOrBooking.fxml"));
            Parent root = loader.load();
            MakeOrderOrBookingController controller = loader.getController();
            controller.setCurrentUser(username.getText());  // Giving the username to the next controller

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username or password is not correct!");
            alert.showAndWait();
        }
    }

    /**
     * Moving to customer sign-up page when clicking a button.
     * Helping user to register by showing the sign-up page.
     *
     * @param event What happened, typically clicking a hyperlink.
     * @throws IOException If there is a problem with moving pages.
     */
    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerSignUpPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sending user back to the home page when pressing a back button.
     * Giving a way back to the main interface from the log-in screen.
     *
     * @param event What happened, usually pressing a button.
     * @throws IOException If there is a problem with moving pages.
     */
    public void handleBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
