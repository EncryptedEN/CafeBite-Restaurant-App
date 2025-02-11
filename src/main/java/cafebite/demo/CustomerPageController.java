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
 * It controls all the customer pages, managing the moving around and the things the customers do.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class CustomerPageController {

    /**
     * Does the back navigation thing.
     * <p>
     * This here method answers to some click or touch or such, it is like a button, and it
     * takes the user back home. Yes, back home. Like where all start from. Good, yes?
     *
     * @param event The action thing that made this method do its thing.
     */
    @FXML
    public void handleBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            // We must get the home page thing ready
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));

            Scene scene = new Scene(root);
            // Add the CSS for the nice looks
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

            // Put the scene in the stage, show it, make it big
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // If there's some problem, print it out
        }
    }

    /**
     * This one here is just a pretend booking function.
     * <p>
     * Right now, all it does is it writes something on the screen, like a little hello message,
     * when someone clicks on the button to book a table.
     */
    @FXML
    public void bookTable() {
        System.out.println("Book Table button clicked");
    }

    /**
     * Deals with going to the order page.
     * <p>
     * When the user clicks on a button or such, this makes them go to the page where they can
     * order food from the menu. It is like magic, yes?
     *
     * @param event The thing that happened that made this method do what it does.
     */
    @FXML
    public void handleMakeOrder(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderOptions.fxml"))); // Be sure the path is correct
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // If there's some problem, print it out
        }
    }
}
