package cafebite.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cafebite.demo.Order.OrderModel;

/**
 * Controller for managing the booking table interface in the CafeBite application.
 * This class handles user interactions on the menu page, allowing customers to
 * select menu items, review their order, and check out.
 *
 * @author Aquib Afzal
 * @version 1.0
 */
public class BookingTableController {
    @FXML
    private TilePane menuTilePane;
    @FXML
    private ComboBox<String> orderTypeCombo;
    @FXML
    private TextArea orderDetailsArea;
    @FXML
    private Label customerNameLabel;

    private final OrderManager orderManager = new OrderManager();
    private final Set<VBox> selectedItems = new HashSet<>();
    private String currentUsername;
    private OrderModel orderExcel;

    /**
     * Initializes the menu page by setting available order types and loading menu items.
     */
    public void initialize() {
        orderTypeCombo.getItems().addAll("Eat In", "Take Away", "Delivery");
        loadMenuItems();
        orderExcel = new OrderModel();
    }

    /**
     * Sets the current user for the menu page and updates the user interface to show the user's full name.
     *
     * @param username the username of the current customer
     */
    public void setCurrentUser(String username) {
        this.currentUsername = username;
        String customerFullName = Customer.getFullName(username);
        customerNameLabel.setText(customerFullName);
        orderManager.setCustomerName(customerFullName);
    }

    /**
     * Initiates the checkout preview process by updating the order details based on selected items.
     *
     * @param event the event that triggered this method
     */
    @FXML
    private void handleCheckoutPreview(ActionEvent event) {
        orderManager.setOrderType(orderTypeCombo.getValue());
        String orderSummary = orderManager.getOrderDetails();
        orderDetailsArea.setText(orderSummary);
    }

    /**
     * Completes the checkout process, updates the order details, and saves the order to an Excel file.
     *
     * @param event the event that triggered this method
     */
    @FXML
    private void handleCheckout(ActionEvent event) {
        try {
            orderManager.setOrderType(orderTypeCombo.getValue());
            String orderSummary = orderManager.getOrderDetails();
            orderDetailsArea.setText(orderSummary);
            orderExcel.createOrder("./OrderData.xlsx", orderManager);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Order Successful! \n Your Order id is " + orderManager.orderId);
            alert.showAndWait();
            loadMenuItems();
            orderDetailsArea.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads menu items from a CSV file and displays them in the menu TilePane.
     */
    private void loadMenuItems() {
        Path filePath = Paths.get(System.getProperty("user.dir"),
                "src/main/resources/cafebite/demo/menu.csv");
        try {
            List<String> lines = Files.readAllLines(filePath);
            menuTilePane.getChildren().clear();
            for (String line : lines.subList(1, lines.size())) {
                String[] details = line.split(",");
                VBox itemBox = createMenuItem(details[0], details[1], Double.parseDouble(details[2]), details[4]);
                menuTilePane.getChildren().add(itemBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a visual representation of a menu item using its details and an image.
     *
     * @param name      the name of the menu item
     * @param type      the type of the menu item
     * @param price     the price of the menu item
     * @param imagePath the path to the image of the menu item
     * @return a VBox containing the menu item's image, name, and price label
     */
    private VBox createMenuItem(String name, String type, double price, String imagePath) {
        Path filePath = Paths.get(System.getProperty("user.dir"),
                "src/main/resources/cafebite/demo/IMAGES", imagePath);
        Image image = new Image(new File(filePath.toString()).toURI().toString(), true);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        Label nameLabel = new Label(name);
        Label priceLabel = new Label(String.format("£%.2f", price));
        VBox box = new VBox(10, imageView, nameLabel, priceLabel);
        box.setOnMouseClicked(event -> toggleSelectItem(name, price, box));
        return box;
    }

    /**
     * Toggles the selection state of a menu item when clicked,
     * updating the order details accordingly.
     *
     * @param name  the name of the menu item
     * @param price the price of the menu item
     * @param box   the VBox representing the menu item
     */
    private void toggleSelectItem(String name, double price, VBox box) {
        if (selectedItems.contains(box)) {
            selectedItems.remove(box);
            box.setStyle("");
            orderManager.removeItem(name);
        } else {
            selectedItems.add(box);
            box.setStyle("-fx-border-color: red; -fx-border-radius: 20px;");
            orderManager.addItem(name, price);
        }
    }

    /**
     * Handles navigation back to the Make Order or Booking page.
     *
     * @param event the event that triggered this method
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MakeOrderOrBooking.fxml"));
        Parent root = loader.load();
        MakeOrderOrBookingController controller = loader.getController();
        controller.setCurrentUser(this.currentUsername);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
