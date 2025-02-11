package cafebite.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Manages the display and interactions within the staff details page.
 * Allows adding, updating, and deleting staff members.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class StaffDetailsController {

    @FXML
    private VBox staffCardsContainer;

    @FXML
    private TextField firstNameInput, lastNameInput, staffIdInput, staffTypeInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button addButton;

    /**
     * Sets up the staff list and the add button when the page is first shown.
     * Loads all staff details to display.
     *
     * @throws IOException if there is a problem loading staff details
     */
    public void initialize() throws IOException {
        refreshStaffList(); // Make sure this method handles exceptions internally and gracefully.
        setupAddButton();
        try {
            List<StaffMember> staffMembers = Staff.getAllStaffDetails();
            for (StaffMember staff : staffMembers) {
                staffCardsContainer.getChildren().add(createStaffCard(staff, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshStaffList();
        if (addButton != null) {
            addButton.setOnAction(event -> addNewStaff());
        }
    }

    private void setupAddButton() {
        if (addButton != null) {
            addButton.setOnAction(event -> {
                try {
                    addNewStaff();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to add new staff.", Alert.AlertType.ERROR);
                }
            });
        }
    }

    /**
     * Refreshes the list of staff displayed on the page.
     * @throws IOException if there is a problem reading staff details
     */
    private void refreshStaffList() throws IOException {
        try {
            staffCardsContainer.getChildren().clear();
            List<StaffMember> staffMembers = Staff.getAllStaffDetails();
            if (staffMembers != null) {
                for (StaffMember staff : staffMembers) {
                    VBox staffCard = createStaffCard(staff, false);
                    staffCardsContainer.getChildren().add(staffCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load staff details.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Creates a visual card for each staff member that allows interaction such as delete and update.
     * @param staff the staff member data to create a card for
     * @param isNew flag indicating if this is a new staff member being added
     * @return VBox the visual card for a staff member
     */
    private VBox createStaffCard(StaffMember staff, Boolean isNew) {
        VBox staffCard = new VBox(10);
        TextField nameField = new TextField(staff.getFirstName() + " " + staff.getLastName());
        TextField idField = new TextField(staff.getStaffId());
        TextField typeField = new TextField(staff.getStaffType());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> deleteStaff(staff, staffCard));
        if(isNew){
            Button saveButton = new Button("Save");

            saveButton.setOnAction(event -> saveStaff(staff, nameField.getText(), idField.getText(), typeField.getText(), true));

            staffCard.getChildren().addAll(new Label("Name:"), nameField, new Label("ID:"), idField, new Label("Type:"),
                    typeField, saveButton, deleteButton);
        }else{

            Button updateButton = new Button("Update");

            updateButton.setOnAction(event -> saveStaff(staff, nameField.getText(), idField.getText(), typeField.getText(), false));

            staffCard.getChildren().addAll(new Label("Name:"), nameField, new Label("ID:"), idField, new Label("Type:"),
                    typeField, updateButton, deleteButton);
        }
        HBox.setHgrow(staffCard, Priority.ALWAYS);
        return staffCard;
    }

    /**
     * Saves or updates staff information based on the input fields from the staff card.
     * @param staff the staff member object to update
     * @param fullName the full name of the staff
     * @param staffId the ID of the staff
     * @param staffType the type of staff
     * @param isNew a boolean indicating if this is a new staff member
     */
    private void saveStaff(StaffMember staff, String fullName, String staffId,
                           String staffType, Boolean isNew) {
        String[] names = fullName.split(" ");
        String firstName = names[0];
        String lastName = names.length > 1 ? names[1] : "";

        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setStaffId(staffId);
        staff.setStaffType(staffType);
        if (isNew) {
            Staff.addNewStaff(firstName, lastName, staffId, "", staffType);
        }else{
            Staff.updateStaffDetails(staff);
        }
        showAlert("Success", isNew ? "Staff details saved successfully!"
                : "Staff details updated successfully!", Alert.AlertType.INFORMATION);
    }

    /**
     * Deletes a staff member and updates the UI.
     * @param staff the staff member to delete
     * @param staffCard the visual card of the staff member that needs to be removed
     */
    private void deleteStaff(StaffMember staff, VBox staffCard) {
        if (Staff.deleteStaff(staff.getStaffId())) {
            staffCardsContainer.getChildren().remove(staffCard);
            showAlert("Success", "Staff deleted successfully!",
                    Alert.AlertType.INFORMATION);
        } else {
            staffCardsContainer.getChildren().remove(staffCard);
        }
    }

    @FXML
    private void addNewStaff() {
        StaffMember newStaff = new StaffMember("", "",
                "", "", ""); // Adjust as necessary for constructor with password
        VBox newStaffCard = createStaffCard(newStaff, true);
        staffCardsContainer.getChildren().add(newStaffCard);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Manager.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
