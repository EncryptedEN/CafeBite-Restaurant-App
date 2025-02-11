package cafebite.demo.Booking;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages booking operations such as creating new bookings and retrieving existing bookings from a file.
 *
 * @author Aquib Afzal
 * @version 1.0
 */
public class BookingModel {

    /**
     * Creates a new booking and writes it to the specified file.
     *
     * @param filePath The path of the file to write the booking data.
     * @param booking  The booking object containing the booking details.
     * @throws IOException If an I/O error occurs.
     */
    public void createBooking(String filePath, Booking booking) throws IOException {
        XSSFWorkbook workbook = null;
        Sheet sheet = null;
        try (FileInputStream fis = new FileInputStream(filePath);) {
            workbook = fis.available() > 0 ? new XSSFWorkbook(fis) : new XSSFWorkbook();
            sheet = workbook.getSheet("Bookings");

        } catch (FileNotFoundException e) {
            // If the file does not exist, create a new file and write data
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Bookings");
            Row headerRow = sheet.createRow(0);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            String[] headers = { "Booking Id", "Num Of Guest", "Date", "Hours", "Minute", "Duration", "Table",
                    "Status" };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int lastRow = sheet.getLastRowNum() == -1 ? 1 : sheet.getLastRowNum();
        Row dataRow = sheet.createRow(lastRow + 1);
        dataRow.createCell(0).setCellValue(booking.getBookingId().toString());
        dataRow.createCell(1).setCellValue(booking.getNumOfGuests());
        dataRow.createCell(2).setCellValue(booking.getDate());
        dataRow.createCell(3).setCellValue(booking.getHour());
        dataRow.createCell(4).setCellValue(booking.getMinute());
        dataRow.createCell(5).setCellValue(booking.getDuration());
        dataRow.createCell(6).setCellValue(booking.getTable());
        dataRow.createCell(7).setCellValue(booking.getStatus());

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * Retrieves all bookings from the specified file.
     *
     * @param filePath The path of the file to read the bookings from.
     * @return A list of Booking objects containing the retrieved bookings.
     * @throws IOException If an I/O error occurs.
     */
    public List<Booking> getBookings(String filePath) throws IOException {
        List<Booking> bookings = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);) {
            XSSFWorkbook workbook = fis.available() > 0 ? new XSSFWorkbook(fis) : new XSSFWorkbook();
            Sheet sheet = workbook.getSheet("Bookings");
            for (Row row : sheet) {
                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Read data from columns

                String bookingId = row.getCell(0).getStringCellValue();
                String guests = row.getCell(1).getStringCellValue();
                String date = row.getCell(2).getStringCellValue();
                String hour = row.getCell(3).getStringCellValue();
                String minute = row.getCell(4).getStringCellValue();
                String duration = row.getCell(5).getStringCellValue();
                String table = row.getCell(6).getStringCellValue();
                String status = row.getCell(7).getStringCellValue();

                Booking newBooking = new Booking(guests, date, hour, minute, duration, table, status);
                bookings.add(newBooking);
            }
            return bookings;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
