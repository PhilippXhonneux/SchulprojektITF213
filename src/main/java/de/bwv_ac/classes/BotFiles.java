package de.bwv_ac.classes;

import de.bwv_ac.data.Datastructure;
import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@Deprecated
public class BotFiles
{
    private static String delimiter = ";";

    public static void setDelimiter(String delimiter){
        BotFiles.delimiter = delimiter;
    }

    @Deprecated
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

    @Deprecated
    public static <T extends Datastructure> ArrayList<T> CSVReader(String filePath, boolean hasHeader, Class<T> clazz) throws FileNotFoundException
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
                output.add(temp);
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

    private static String getFirstLine(String filePath, String delimiter) throws FileNotFoundException
    {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        sc.useDelimiter(delimiter);
        return sc.nextLine();
    }
}
