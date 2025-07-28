package com.flightbooking.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public static List<Map<String, String>> getData(String excelFilePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0); // Assuming first row is header

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell currentCell = currentRow.getCell(j);

                    String headerName = headerCell.getStringCellValue();
                    String cellValue = "";

                    if (currentCell != null) {
                        switch (currentCell.getCellType()) {
                            case STRING:
                                cellValue = currentCell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf((long) currentCell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(currentCell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                cellValue = currentCell.getCellFormula();
                                break;
                            default:
                                cellValue = "";
                        }
                    }
                    rowMap.put(headerName, cellValue);
                }
                data.add(rowMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}