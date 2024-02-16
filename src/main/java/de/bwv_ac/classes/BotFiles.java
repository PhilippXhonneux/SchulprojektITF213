package de.bwv_ac.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BotFiles
{
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
                String tempLine = ConvertToCSVLine(row);
                writer.append(tempLine);
                writer.flush();
            }


            writer.close(); // Schließt den Writer
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static String ConvertToCSVLine(ArrayList<String> row)
    {
        for (String columns : row)
        {

        }
        return null;
    }
}
