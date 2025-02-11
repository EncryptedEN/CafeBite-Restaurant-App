package cafebite.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles the management of orders, including adding and removing items, and keeping track of order details.
 *
 * @author Aquib Afzal
 * @version 1.0
 */
public class OrderManager {
    /**
     * Stores each item and its price in the order.
     */
    public Map<String, Double> items = new HashMap<>();

    /**
     * Specifies the type of the order (e.g., "Dine-In", "Takeaway").
     */
    public String orderType;

    /**
     * A unique ID for identifying the order.
     */
    public UUID orderId;

    /**
     * The total cost of all items in the order.
     */
    public double totalPrice;

    /**
     * The full name of the customer who placed the order.
     */
    public String customerFullName;

    /**
     * Creates a new instance of OrderManager and generates a unique order ID.
     */
    public OrderManager() {
        this.orderId = UUID.randomUUID(); // Generates a unique order ID
    }

    /**
     * Adds an item to the order with its price and updates the total price.
     *
     * @param item  The name of the item to be added.
     * @param price The price of the item.
     */
    public void addItem(String item, double price) {
        items.put(item, price);
        totalPrice += price;
    }

    /**
     * Removes an item from the order if it is present and adjusts the total price accordingly.
     *
     * @param item The name of the item to be removed.
     */
    public void removeItem(String item) {
        if (items.containsKey(item)) {
            totalPrice -= items.get(item);
            items.remove(item);
        }
    }

    /**
     * Sets the type of the order.
     *
     * @param orderType The type of the order (e.g., "Dine-In", "Takeaway").
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Provides a detailed description of the order, including item names, prices, and the total cost.
     *
     * @return A string representing the details of the order.
     */
    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId.toString()).append("\n");
        details.append("Order Type: ").append(orderType).append("\n");
        details.append("Customer Name: ").append(customerFullName).append("\n");
        details.append("Items:\n");
        items.forEach((name, price) -> details.append(name).append(" - £").append(String.format("%.2f", price)).append("\n"));
        details.append("Total Price: £").append(String.format("%.2f", totalPrice));
        return details.toString();
    }

    /**
     * Sets the full name of the customer who placed the order.
     *
     * @param customerName The full name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerFullName = customerName;
    }

    /**
     * Retrieves the full name of the customer who placed the order.
     *
     * @return The full name of the customer.
     */
    public String getCustomerName() {
        return this.customerFullName;
    }
}
