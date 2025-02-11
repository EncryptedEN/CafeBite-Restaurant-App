package cafebite.demo;

import javafx.scene.control.TextField;

/**
 * Represents a staff member in the application.
 * This class stores information about a staff member such as their name, ID, and staff type.
 * It also handles user interface components to edit these properties.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class StaffMember {
    private String firstName;
    private String lastName;
    private String staffId;
    private String staffType;

    // JavaFX TextField components for editing properties
    private transient TextField nameField;
    private transient TextField idField;
    private transient TextField typeField;

    /**
     * Constructor to create a new StaffMember.
     *
     * @param firstName the first name of the staff member
     * @param lastName  the last name of the staff member
     * @param staffId   the unique identifier for the staff member
     * @param staffType the type of staff (e.g., Manager, Waiter)
     * @param s         unused parameter
     */
    public StaffMember(String firstName, String lastName, String staffId,
                       String staffType, String s) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffId = staffId;
        this.staffType = staffType;
    }

    /**
     * Gets the first name of the staff member.
     *
     * @return the first name of the staff member
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the full name of the staff member, combining first and last name.
     *
     * @return the full name of the staff member
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Sets the first name of the staff member.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the staff member.
     *
     * @return the last name of the staff member
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the staff member.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the staff ID of the staff member.
     *
     * @return the staff ID
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * Sets the staff ID for the staff member.
     *
     * @param staffId the staff ID to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * Gets the staff type of the staff member.
     *
     * @return the staff type
     */
    public String getStaffType() {
        return staffType;
    }

    /**
     * Sets the staff type for the staff member.
     *
     * @param staffType the staff type to set
     */
    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    /**
     * Gets the TextField for editing the name.
     *
     * @return the TextField for the name
     */
    public TextField getNameField() {
        return nameField;
    }

    /**
     * Sets the TextField for editing the name.
     *
     * @param nameField the TextField to set for the name
     */
    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    /**
     * Gets the TextField for editing the staff ID.
     *
     * @return the TextField for the staff ID
     */
    public TextField getIdField() {
        return idField;
    }

    /**
     * Sets the TextField for editing the staff ID.
     *
     * @param idField the TextField to set for the staff ID
     */
    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    /**
     * Gets the TextField for editing the staff type.
     *
     * @return the TextField for the staff type
     */
    public TextField getTypeField() {
        return typeField;
    }

    /**
     * Sets the TextField for editing the staff type.
     *
     * @param typeField the TextField to set for the staff type
     */
    public void setTypeField(TextField typeField) {

        this.typeField = typeField;
    }
}
