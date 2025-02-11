package cafebite.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles staff operations like starting up, saving, and checking logins
 * using an Excel file for storing staff data.
 *
 * @author Emmanuel Nwokoro
 * @version 1.0
 */
public class Staff {
    private static final String EXCEL_FILE = "StaffData.xlsx";

    /**
     * Starts the Excel file if it doesn't exist already.
     * Makes a new sheet for staff data with the right headers.
     */
    private static void initializeExcelFile() {
        try {
            File file = new File(EXCEL_FILE);
            if (!file.exists()) {
                try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet("StaffData");
                    Row header = sheet.createRow(0);
                    header.createCell(0).setCellValue("First Name");
                    header.createCell(1).setCellValue("Last Name");
                    header.createCell(2).setCellValue("Staff ID");
                    header.createCell(3).setCellValue("Password");
                    header.createCell(4).setCellValue("Staff Type");

                    try (FileOutputStream out = new FileOutputStream(file)) {
                        workbook.write(out);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves staff details into the Excel file.
     *
     * @param firstName the first name of the staff member
     * @param lastName  the last name of the staff member
     * @param staffId   the ID of the staff member
     * @param password  the password of the staff member
     * @param staffType the type of staff role
     * @return true if the save was successful, false if it failed
     */
    public static boolean saveStaff(String firstName, String lastName,
                                    String staffId, String password, String staffType) {
        initializeExcelFile();  // Make sure the file is ready and correct
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("StaffData");
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(firstName);
            row.createCell(1).setCellValue(lastName);
            row.createCell(2).setCellValue(staffId);
            row.createCell(3).setCellValue(password);
            row.createCell(4).setCellValue(staffType);

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
     * Adds a new staff member to the Excel file.
     * This method creates a new row at the end of the sheet
     * and fills it with the staff member's details.
     *
     * @param firstName the first name of the new staff member
     * @param lastName  the last name of the new staff member
     * @param staffId   the unique ID of the staff member
     * @param password  the password for the staff member, not displayed in the UI
     * @param staffType the type of role the staff member has
     * @return true if the staff member was successfully added, false if there was an error
     */
    public static boolean addNewStaff(String firstName, String lastName,
                                      String staffId, String password, String staffType) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("StaffData");
            int rowNum = sheet.getLastRowNum() + 1;  // Get the next row number to add a new staff member
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(firstName);
            row.createCell(1).setCellValue(lastName);
            row.createCell(2).setCellValue(staffId);
            row.createCell(3).setCellValue(password);  // Password is saved but not displayed in the UI
            row.createCell(4).setCellValue(staffType);

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
     * Checks if the login details are correct.
     *
     * @param staffId  the staff ID to check
     * @param password the password to check
     * @return true if the details are correct, false otherwise
     */
    public static boolean validateLogin(String staffId, String password) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("StaffData");
            if (sheet == null) {
                System.out.println("Sheet 'StaffData' does not exist in the Excel file.");
                return false;
            }
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip the header row.

                Cell staffIdCell = row.getCell(2); // Assuming the Staff ID is in the third column.
                Cell passwordCell = row.getCell(3); // Assuming the Password is in the fourth column.

                String excelStaffId = getCellValue(staffIdCell);
                String excelPassword = getCellValue(passwordCell);

                if (staffId.equals(excelStaffId) && password.equals(excelPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets the value from a cell, even if it's empty.
     *
     * @param cell the cell to get the value from
     * @return the value of the cell, or an empty string if the cell is empty
     */
    private static String getCellValue(Cell cell) {
        return cell == null ? ""
                : cell.getStringCellValue();
    }

    /**
     * Gets the type of staff based on their ID.
     *
     * @param staffId the ID of the staff
     * @return the staff type, or null if not found
     */
    public static String getStaffType(String staffId) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("StaffData");
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Cell idCell = row.getCell(2);
                if (idCell != null && idCell.getStringCellValue().equals(staffId)) {
                    Cell typeCell = row.getCell(4);
                    return typeCell.getStringCellValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the full name of a staff based on their ID.
     *
     * @param staffId the ID of the staff
     * @return the full name of the staff, or null if not found
     */
    public static String getFullName(String staffId) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("StaffData");
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Cell idCell = row.getCell(2);
                if (idCell != null && idCell.getStringCellValue().equals(staffId)) {
                    Cell firstNameCell = row.getCell(0);
                    Cell lastNameCell = row.getCell(1);
                    return firstNameCell.getStringCellValue() + " "
                            + lastNameCell.getStringCellValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Gets details of all staff from the Excel file.
     *
     * @return a list of all staff members
     * @throws IOException if there is a problem reading the file
     */
    public static List<StaffMember> getAllStaffDetails() throws IOException {
        List<StaffMember> staffList = new ArrayList<>();
        try (InputStream is = new FileInputStream(new File(EXCEL_FILE));
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet("StaffData");
            if (sheet == null) return staffList;  // Return empty list if the sheet does not exist
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;  // Skip header row
                StaffMember member = new StaffMember(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(4).getStringCellValue(),
                        "");
                staffList.add(member);
            }
        }
        return staffList;
    }

    /**
     * Updates the details of an existing staff member in the Excel file.
     *
     * @param staff the staff member with updated details
     */
    public static void updateStaffDetails(StaffMember staff) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("StaffData");
            int rowIndex = findStaffRow(sheet, staff.getStaffId());
            if (rowIndex != -1) {
                Row row = sheet.getRow(rowIndex);
                row.getCell(0).setCellValue(staff.getFirstName());
                row.getCell(1).setCellValue(staff.getLastName());
                row.getCell(2).setCellValue(staff.getStaffId());
                row.getCell(4).setCellValue(staff.getStaffType());

                try (FileOutputStream outFile = new FileOutputStream(EXCEL_FILE)) {
                    workbook.write(outFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a staff member from the Excel file based on their ID.
     *
     * @param staffId the ID of the staff to delete
     * @return true if the staff was successfully deleted, false otherwise
     */
    public static boolean deleteStaff(String staffId) {
        try (FileInputStream file = new FileInputStream(EXCEL_FILE);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("StaffData");
            int rowIndex = findStaffRow(sheet, staffId);
            if (rowIndex != -1) {
                sheet.removeRow(sheet.getRow(rowIndex));
                // Ensure rows below are shifted up
                int lastRowNum = sheet.getLastRowNum();
                if (rowIndex >= 0 && rowIndex < lastRowNum) {
                    sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
                }
                try (FileOutputStream outFile = new FileOutputStream(EXCEL_FILE)) {
                    workbook.write(outFile);
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finds the row in the sheet that corresponds to the given staff ID.
     *
     * @param sheet   the Excel sheet
     * @param staffId the staff ID to find
     * @return the row index or -1 if not found
     */
    private static int findStaffRow(Sheet sheet, String staffId) {
        for (Row row : sheet) {
            if (row.getCell(2).getStringCellValue().equals(staffId)) {
                return row.getRowNum();
            }
        }
        return -1;
    }
}



