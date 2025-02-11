package cafebite.demo.Booking;

import java.util.UUID;

/**
 * Represents a booking made by a customer.
 * This class stores information about the booking such as the number of guests,
 * date, time, duration, table number, and status.
 *
 * @author Aquib Afzal
 * @version 1.0
 */
public class Booking {
    private final UUID bookingId;
    private String numOfGuests;
    private String date;
    private String hour;
    private String minute;
    private String duration;
    private String table;
    private String status; // pending, approved, canceled

    /**
     * Constructs a new Booking object with the given parameters.
     *
     * @param numOfGuests The number of guests for the booking.
     * @param date        The date of the booking.
     * @param hour        The hour of the booking.
     * @param minute      The minute of the booking.
     * @param duration    The duration of the booking.
     * @param table       The table number for the booking.
     * @param status      The status of the booking (pending, approved, canceled).
     */
    public Booking(String numOfGuests, String date, String hour, String minute,
                   String duration, String table, String status) {
        this.bookingId = UUID.randomUUID();
        this.numOfGuests = numOfGuests;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.duration = duration;
        this.table = table;
        this.status = status;
    }

    // Getters and setters

    /**
     * Gets the number of guests for the booking.
     *
     * @return The number of guests.
     */
    public String getNumOfGuests() {
        return numOfGuests;
    }

    /**
     * Sets the number of guests for the booking.
     *
     * @param numOfGuests The number of guests to set.
     */
    public void setNumOfGuests(String numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    /**
     * Gets the date of the booking.
     *
     * @return The date of the booking.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the booking.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the hour of the booking.
     *
     * @return The hour of the booking.
     */
    public String getHour() {
        return hour;
    }

    /**
     * Sets the hour of the booking.
     *
     * @param hour The hour to set.
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * Gets the minute of the booking.
     *
     * @return The minute of the booking.
     */
    public String getMinute() {
        return minute;
    }

    /**
     * Sets the minute of the booking.
     *
     * @param minute The minute to set.
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * Gets the duration of the booking.
     *
     * @return The duration of the booking.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the booking.
     *
     * @param duration The duration to set.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the table number for the booking.
     *
     * @return The table number.
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets the table number for the booking.
     *
     * @param table The table number to set.
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * Gets the status of the booking.
     *
     * @return The status of the booking (pending, approved, canceled).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the booking.
     *
     * @param status The status to set (pending, approved, canceled).
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the unique identifier for the booking.
     *
     * @return The booking ID.
     */
    public UUID getBookingId() {
        return bookingId;
    }

    /**
     * Returns a string representation of the booking information.
     *
     * @return A string containing the booking information.
     */
    @Override
    public String toString() {
        return numOfGuests + ",  " + date + ",  " + hour + ",  " + minute
                + ",  " + duration + ",  " + table + ",  " + status + "\n";
    }
}
