package cafebite.demo.Order;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cafebite.demo.OrderManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * this class manages the order-related operations,
 * such as creating new orders and retrieving existing orders from a file.
 *
 * @author Aquib Afzal
 * @version 1.0
 */
public class OrderModel {

    /**
     * This method creates a new order and adds it to the orders file.
     *
     * @param filePath the path to the orders file
     * @param order    the order to be added
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void createOrder(String filePath, OrderManager order) throws IOException {
        XSSFWorkbook workbook = null;
        Sheet sheet = null;
        try (FileInputStream fis = new FileInputStream(filePath);) {
            workbook = fis.available() > 0 ? new XSSFWorkbook(fis) : new XSSFWorkbook();
            sheet = workbook.getSheet("Orders");

        } catch (FileNotFoundException e) {
            // If the file does not exist, create a new file and write data
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Orders");
            Row headerRow = sheet.createRow(0);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            String[] headers = { "Order ID", "Order Type", "Customer Name", "Items", "Total Price" };
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
        dataRow.createCell(0).setCellValue(order.orderId.toString());
        dataRow.createCell(1).setCellValue(order.orderType);
        dataRow.createCell(2).setCellValue(order.customerFullName);
        dataRow.createCell(3).setCellValue(order.items.toString());
        dataRow.createCell(4).setCellValue(order.totalPrice);

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * This method retrieves existing orders from the orders file.
     *
     * @param filePath the path to the orders file
     * @return a string containing the orders in a CSV format
     * @throws IOException if an I/O error occurs while reading the file
     */
    public String getOrders(String filePath) throws IOException {
        String ordersText = "orderId,  orderType,  customerName,  items\n";
        try (FileInputStream fis = new FileInputStream(filePath);) {
            XSSFWorkbook workbook = fis.available() > 0 ? new XSSFWorkbook(fis) : new XSSFWorkbook();
            Sheet sheet = workbook.getSheet("Orders");
            for (Row row : sheet) {
                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Read data from columns

                String orderId = row.getCell(0).getStringCellValue();
                String orderType = row.getCell(1).getStringCellValue();
                String customeName = row.getCell(2).getStringCellValue();
                String items = row.getCell(3).getStringCellValue();
                // String totalPrice = row.getCell(4).getStringCellValue();
                ordersText += orderId + ",  " + orderType + ",  " + customeName + ",  " + items
                        + "\n";
            }
            return ordersText;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
