package de.bwv_ac.classes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Can read an CSV file
public class CSVReader {

    private String path;

    public CSVReader(String path) {
        this.path = path;
    }
    public List<String[]> read() {
        List<String[]> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
