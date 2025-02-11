package cafebite.demo;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import cafebite.demo.Booking.Booking;
import cafebite.demo.Booking.BookingModel;
import cafebite.demo.Order.OrderModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controls the actions for ordering and booking within the app.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class MakeOrderOrBookingController {
    private String username;
    private BookingModel bookingExcel;
    private OrderModel orderExcel;

    @FXML
    private VBox bookTableContainer;

    @FXML
    private Spinner numOfGuests;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner hourSpinner;
    @FXML
    private Spinner minuteSpinner;
    @FXML
    private ComboBox tableComboBox;
    @FXML
    private Spinner durationTime;

    /**
     * Prepares the controller for use, including setting up initial data in components.
     */
    public void initialize() {
        tableComboBox.getItems().addAll("Table with 2 Seats", "Table with 4 Seats", "Table with 8 Seats",
                "Table with 10 Seats");
        bookingExcel = new BookingModel();
        orderExcel = new OrderModel();
    }

    /**
     * Sets the username of the user currently logged in.
     *
     * @param username the username of the logged-in user
     */
    public void setCurrentUser(String username) {
        this.username = username;
    }

    /**
     * Shows the booking interface to book a table.
     *
     * @param event the event that triggered this method
     * @throws IOException if an error occurs during the action
     */
    @FXML
    public void handleBookTable(ActionEvent event) throws IOException {
        bookTableContainer.setVisible(true);
    }

    /**
     * Hides the booking interface.
     *
     * @throws IOException if an error occurs during the action
     */
    @FXML
    public void handleCancelBookTable() throws IOException {
        bookTableContainer.setVisible(false);
    }

    /**
     * Retrieves and displays a list of all bookings from the file.
     *
     * @throws IOException if an error occurs during file reading
     */
    @FXML
    public void getBookingList() throws IOException {
        List<Booking> bookingList = bookingExcel.getBookings("./BookingData.xlsx");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking List");
        if (bookingList == null) {
            alert.setContentText("No Booking Available!");
            alert.showAndWait();
            return;
        }
        String bookingText = "Number of Guests, Date, Hour, Time, Duration, Table, Status \n";
        for (Booking booking : bookingList) {
            bookingText += booking.toString();
        }
        alert.setContentText(bookingText);
        alert.showAndWait();
    }

    /**
     * Retrieves and displays a list of all orders from the file.
     *
     * @throws IOException if an error occurs during file reading
     */
    @FXML
    public void getOrderList() throws IOException {
        String ordersText = orderExcel.getOrders("./OrderData.xlsx");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order List");
        if (ordersText == null) {
            alert.setContentText("No Order Available!");
            alert.showAndWait();
            return;
        }
        alert.setContentText(ordersText);
        alert.showAndWait();
    }

    /**
     * Submits a new booking based on the user input.
     *
     * @throws IOException if an error occurs during booking submission
     */
    @FXML
    public void handleSubmitBooking() throws IOException {
        String guests = numOfGuests.getValue().toString();
        String date = datePicker.getValue().toString();
        String hour = hourSpinner.getValue().toString();
        String minute = minuteSpinner.getValue().toString();
        String duration = durationTime.getValue().toString();
        String table = tableComboBox.getValue().toString();

        Booking newBooking = new Booking(guests, date, hour, minute, duration, table, "pending");
        bookingExcel.createBooking("./BookingData.xlsx", newBooking);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Booking Successful! \n Your Booking id is " + newBooking.getBookingId());
        alert.showAndWait();
        System.out.println("Number of Guests: " + guests);
        System.out.println("Date: " + date);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Duration: " + duration);
        System.out.println("Table: " + table);

        bookTableContainer.setVisible(false);
    }

    /**
     * Starts the process for making an order by loading the menu page.
     *
     * @param event the event that triggered this action
     * @throws IOException if an error occurs during loading the FXML file
     */
    @FXML
    private void handleMakeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
        Parent root = loader.load();
        MenuPageController controller = loader.getController();
        controller.setCurrentUser(username); // Pass the username to the MenuPageController

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns the user to the home page.
     *
     * @param event the event that triggered this action
     * @throws IOException if an error occurs during loading the FXML file
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
