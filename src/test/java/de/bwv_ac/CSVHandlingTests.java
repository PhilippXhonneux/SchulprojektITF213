package de.bwv_ac;

import de.bwv_ac.classes.CSVReader;
import de.bwv_ac.classes.CSVWriter;
import de.bwv_ac.data.Datastructure;
import de.bwv_ac.data.Wish;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CSVHandlingTests {

    String tempFilePath = "src/test/resources/example_data/test.csv";
    File tempFile = new File(tempFilePath);
    FileWriter writer;
    CSVWriter csvWriter = new CSVWriter(tempFilePath);

    @BeforeEach
    public void setUp() {
        try {
            writer = new FileWriter(tempFile);
            writer.write("25;Wirtschaftsrecht FH-Aachen;;20;5;A\n");
            writer.close();
        } catch (IOException e) {
            fail("Exception occurred while writing the file: " + e.getMessage());
        }
        tempFile.deleteOnExit();//<-- Funktioniert nicht.
    }

    @Test
    public void testWrite() {
        String[] content = {"Test", "bla bla", "test test"};
        csvWriter.write(content);

        try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {
            String line = br.readLine();
            assertEquals("Test;bla bla;test test", line);
        } catch (IOException e) {
            fail("Exception occurred while reading the file: " + e.getMessage());
        }
    }

    @Test
    public void testRead() throws Exception {
        ArrayList<Wish> wishes = CSVReader.read(tempFilePath, false, Wish.class);
        assertEquals(1, wishes.size());
        assertEquals("25", wishes.get(0));
    }
    @Test
    public void testGetFirstLine() throws FileNotFoundException {
        String[] firstLine = CSVReader.getFirstLine(tempFilePath);
        assertEquals("25", firstLine[0]);
        assertEquals("Wirtschaftsrecht FH-Aachen", firstLine[1]);
        assertEquals("", firstLine[2]);
        assertEquals("20", firstLine[3]);
        assertEquals("5", firstLine[4]);
        assertEquals("A", firstLine[5]);
    }

}