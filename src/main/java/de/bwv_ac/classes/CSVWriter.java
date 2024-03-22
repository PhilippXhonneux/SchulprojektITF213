package de.bwv_ac.classes;

import de.bwv_ac.data.DataCollection;
import de.bwv_ac.data.Datastructure;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Philipp Goebel, Philipp Xhonneux
 * @version 1.2.0
 */
@SuppressWarnings("ALL")
public class CSVWriter {
    private String path;

    public CSVWriter(String path) {
        this.path = path;
    }
    private static String delimiter = ";";
    public static void setDelimiter(String Delimiter){
        delimiter = Delimiter;
    }

    public void write(DataCollection content) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

        bw.write(String.join(delimiter, content.getColumns()));
        bw.newLine();

        for( int i = 0; i<content.size(); i++)
        {
            bw.write(content.get(i).ToCSVString(delimiter));
            bw.newLine();
        }
    }
    @Deprecated
    public void write(String[] content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(String.join(delimiter, content));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
