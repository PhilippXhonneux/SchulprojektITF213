package de.bwv_ac.classes;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Philipp Goebel
 * @version 1.2.0
 */
public class CSVReader {

    private String path;
    private static String delimiter = ";";

    public static void setDelimiter(String delimiter){
        delimiter = delimiter;
    }
    public CSVReader(String path) {
        this.path = path;
    }
    public List<String[]> read(Class clazz) {
        List<String[]> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line.split(delimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private static String[] getFirstLine(String filePath, String delimiter) throws FileNotFoundException
    {
        File file = new File(filePath);
        if(!file.exists())
            throw new FileNotFoundException("File not found: " + filePath);
        Scanner sc = new Scanner(file);
        if(!sc.hasNext())
            throw new NullPointerException("File is empty: " + filePath);
        return sc.nextLine().split(delimiter);
    }
}
