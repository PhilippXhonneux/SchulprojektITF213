package de.bwv_ac.classes;

import de.bwv_ac.data.Datastructur;
import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
    public static <T extends Datastructur> ArrayList<T> CSVReader(String filePath, boolean skipHeader, Class<T> clazz) throws FileNotFoundException
    {
        File file = new File(filePath);
        if(!file.exists())
            throw new FileNotFoundException();
        Scanner sc = new Scanner(file);
        sc.useDelimiter(delimiter);
        boolean headerSkipped = false;

        ArrayList<T> output = new ArrayList<>();
        try
        {
            while(sc.hasNext())
            {
                String line = sc.nextLine();

                if(!headerSkipped && skipHeader) // Skip the first line if skipHeader set on true
                {
                    headerSkipped = true;
                    continue;
                }

                T temp = clazz.newInstance();

                temp.setData(line, delimiter);
                //temp.FromCSVStringToObject(line, delimiter);
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

    public static String[] getFirstLine(String filePath, String delimiter) throws FileNotFoundException {
        File f = new File(filePath);
        if(!f.exists())
            throw new FileNotFoundException();

        Scanner sc = new Scanner(f);
        if(!sc.hasNext())
            throw new EmptyStackException();
        String line = sc.nextLine();
        return line.split(delimiter);

    }
}
