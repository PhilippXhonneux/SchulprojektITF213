package de.bwv_ac.classes;

import de.bwv_ac.data.Datastructur;
import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BotFiles
{
    private static String delimiter = ";";

    public static void setDelimiter(String delimiter){
        BotFiles.delimiter = delimiter;
    }
    public static void CSVWriter(String filePath, ArrayList<ArrayList<String>> csv, boolean hasHeader)
    {
        File file = new File(filePath);
        try
        {
            FileWriter writer = new FileWriter(file);

            for (ArrayList<String> row : csv )
            {
                if(hasHeader)
                {
                    continue;
                }
                String tempLine = convertToCSVLine(row);
                writer.append(tempLine);
                writer.flush();
            }

            writer.close(); // Schließt den Writer
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (ExecutionControl.NotImplementedException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static <T extends Datastructur> ArrayList<T> CSVReader(String filePath, boolean hasHeader, Class<T> clazz) throws FileNotFoundException
    {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        sc.useDelimiter(delimiter);

        ArrayList<T> output = new ArrayList<>();
        try
        {
            while(sc.hasNext())
            {
                String line = sc.nextLine();

                T temp = clazz.newInstance();
                temp.FromCSVStringToObject(line, delimiter);
            }

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return output;
    }

    private static String convertToCSVLine(ArrayList<String> row) throws ExecutionControl.NotImplementedException {
        for (String columns : row)
        {

        }
        throw new ExecutionControl.NotImplementedException("TODO");
    }
}
