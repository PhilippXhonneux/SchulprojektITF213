package de.bwv_ac.classes;


import de.bwv_ac.data.Datastructure;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Philipp Goebel
 * @version 1.2.0
 */
@SuppressWarnings({"FieldMayBeFinal"})
public class CSVReader {

    private String path;
    private static String delimiter = ";";

    public static void setDelimiter(String Delimiter){
        delimiter = Delimiter;
    }
    public static <T extends Datastructure> ArrayList<T> read(String filePath, boolean skipFirstLine, Class<T> clazz) throws Exception {
        ArrayList<T> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean skipped = false;
            String line;
            while ((line = br.readLine()) != null) {
                if(!skipped && skipFirstLine){
                    skipped = true;
                    continue;
                }
                T temp = clazz.newInstance();
                try{
                    temp.FromCSVStringToObject(line, delimiter);

                }catch (NumberFormatException e){
                    throw new Exception();
                }
                content.add(temp);
            }
        } catch (IOException e) { //TODO Error Handling
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

		return content;
    }

    public static String[] getFirstLine(String filePath) throws FileNotFoundException
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
