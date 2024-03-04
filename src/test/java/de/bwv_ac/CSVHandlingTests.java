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

    String tempFilePath = "test.csv";
    @BeforeEach
    public void setUp() {
        CSVWriter.setDelimiter(";");
        try (FileWriter writer = new FileWriter(tempFilePath)) {
            writer.write("25;Wirtschaftsrecht FH-Aachen;;20;5;A\n");
        } catch (IOException e) {
            fail("Failed to write to temporary file");
        }
    }
    @AfterEach
    public void tearDown() {
        File tempFile = new File(tempFilePath);
        if (tempFile.exists() && !tempFile.delete()) {
            fail("Failed to delete temporary file");
        }
    }

    @Test
    public void testWrite() {
        CSVWriter csvWriter = new CSVWriter(tempFilePath);
        String[] content = {"Test", "bla bla", "test test"};
        csvWriter.write(content);

        try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath))) {
            String line = br.readLine();
            assertEquals("Test;bla bla;test test", line);
        } catch (IOException e) {
            fail("Exception occurred while reading the file: " + e.getMessage());
        }
    }

    @Test
    public void testRead() {

        ArrayList<Datastructure> content = CSVReader.read(tempFilePath, false, Datastructure.class);
        //assertEquals(1, content.size());
        assertEquals("25;Wirtschaftsrecht FH-Aachen;;20;5;A", content.get(0));
    }

    @Test
    public void testGetFirstLine() {
        try {
            String[] firstLine = CSVReader.getFirstLine(tempFilePath);
            assertArrayEquals(new String[]{"25;Wirtschaftsrecht FH-Aachen;;20;5;A\n"}, firstLine);
        } catch (IOException e) {
            fail("Failed to read from temporary file");
        }
    }
}
