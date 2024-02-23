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
    public static List<String[]> read(String filePath, boolean skipFirstLine, Class clazz) {
        List<String[]> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean skipped = false;
            String line;
            while ((line = br.readLine()) != null) {
                if(!skipped && skipFirstLine){
                    skipped = true;
                    continue;
                }
                content.add(line.split(delimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static String[] getFirstLine(String filePath, String delimiter) throws FileNotFoundException
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
