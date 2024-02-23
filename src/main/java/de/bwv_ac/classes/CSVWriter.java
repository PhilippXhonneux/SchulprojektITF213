package de.bwv_ac.classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Philipp Goebel
 * @version 1.1.0
 */
public class CSVWriter {
    private String path;

    public CSVWriter(String path) {
        this.path = path;
    }
    private static String delimiter = ";";
    public static void setDelimiter(String delimiter){
        delimiter = delimiter;
    }
    public void write(String[] content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(String.join(delimiter, content));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
