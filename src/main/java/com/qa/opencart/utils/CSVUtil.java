package com.qa.opencart.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {

    private static final String CSV_PATH = "./src/test/resources/testdata/";
    private static List<String[]> rows;

    /**
     * Reads data from a CSV file and returns it as a 2D array of objects.
     * 
     * @param csvName The name of the CSV file (without the extension) to read data from.
     * @return A 2D array of objects containing the data from the CSV file.
     */
    public static Object[][] csvData(String csvName) {
        String csvFile = CSV_PATH + csvName + ".csv";

        CSVReader reader;
        try {
            // Initialize the CSVReader with the file
            reader = new CSVReader(new FileReader(csvFile));
            // Read all rows from the CSV file
            rows = reader.readAll();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        // Convert the list of rows into a 2D array of objects
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }

        return data;
    }
}
