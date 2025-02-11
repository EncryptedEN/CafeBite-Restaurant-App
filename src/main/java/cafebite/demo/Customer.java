package cafebite.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

/**
 * Handles operations related to customers including initializing data storage,
 * saving customer data, validating customer login, and retrieving customer full names.
 * This class interacts with an Excel file to perform CRUD operations on customer data.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class Customer {
    private static final String EXCEL_FILE = "CustomerData.xlsx";

    /**
     * Initializes the Excel file used for storing customer data.
     * If the file does not exist, it creates a new file with default customer headers.
     */
    private static void initializeExcelFile() {
        try {
            File file = new File(EXCEL_FILE);
            if (!file.exists()) {
                try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet("Customers");
                    Row header = sheet.createRow(0);
                    header.createCell(0).setCellValue("First Name");
                    header.createCell(1).setCellValue("Last Name");
                    header.createCell(2).setCellValue("Address");
                    header.createCell(3).setCellValue("Username");
                    header.createCell(4).setCellValue("Password");
                    try (FileOutputStream out = new FileOutputStream(EXCEL_FILE)) {
                        workbook.write(out);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a customer's details to the Excel file.
     *
     * @param firstName the customer's first name.
     * @param lastName  the customer's last name.
     * @param address   the customer's address.
     * @param username  the customer's username.
     * @param password  the customer's password.
     * @return true if the customer was successfully saved, false otherwise.
     */
    public static boolean saveCustomer(String firstName, String lastName, String address, String username, String password) {
        initializeExcelFile();  // Ensure the file is ready and has the correct structure
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(firstName);
            row.createCell(1).setCellValue(lastName);
            row.createCell(2).setCellValue(address);
            row.createCell(3).setCellValue(username);
            row.createCell(4).setCellValue(password);

            try (FileOutputStream outFile = new FileOutputStream(EXCEL_FILE)) {
                workbook.write(outFile);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Validates a customer's login credentials against the data stored in the Excel file.
     *
     * @param username the customer's username to validate.
     * @param password the customer's password to validate.
     * @return true if the credentials are valid, false otherwise.
     */
    public static boolean validateLogin(String username, String password) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header row
                if (username.equals(row.getCell(3).getStringCellValue()) &&
                        password.equals(row.getCell(4).getStringCellValue())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the full name of a customer based on the username.
     *
     * @param username the username of the customer whose full name is to be retrieved.
     * @return the full name of the customer, or "Username not found" if no matching user is found.
     */
    public static String getFullName(String username) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("Customers");
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header
                if (username.equals(row.getCell(3).getStringCellValue())) {
                    String firstName = row.getCell(0).getStringCellValue();
                    String lastName = row.getCell(1).getStringCellValue();
                    return firstName + " " + lastName; // Return the full name
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Username not found"; // Return default message if username is not found
    }
}
