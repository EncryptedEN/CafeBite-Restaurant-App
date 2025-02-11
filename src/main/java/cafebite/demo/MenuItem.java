package cafebite.demo;

/**
 * Represents a menu item in a restaurant. This class stores details: item's name, type, price, available quantity,
 * and the path to its image. This helps in managing the menu items shown in the application.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class MenuItem {
    /**
     * Name of the menu item.
     */
    private final String name;

    /**
     * Type of the menu item, such as appetizer, main course, or dessert.
     */
    private final String type;

    /**
     * Price of the menu item in the currency used.
     */
    private final double price;

    /**
     * Number of this menu item available in stock.
     */
    private final int quantity;

    /**
     * File path to the image of the menu item, used for display.
     */
    private final String imagePath;

    /**
     * Constructs a new MenuItem with detailed specifications.
     *
     * @param name      Name of the menu item.
     * @param type      Type or category of the menu item.
     * @param price     Price of the menu item.
     * @param quantity  Number of items available.
     * @param imagePath Path to the image file for the item.
     */
    public MenuItem(String name, String type, double price, int quantity, String imagePath) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    /**
     * Gets the name of the menu item.
     *
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the menu item.
     *
     * @return The type of the menu item.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of the menu item that is available.
     *
     * @return The available quantity of the menu item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the image path of the menu item.
     *
     * @return The file path to the image of the menu item.
     */
    public String getImagePath() {
        return imagePath;
    }
}
