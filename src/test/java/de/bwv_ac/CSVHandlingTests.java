package de.bwv_ac;

import de.bwv_ac.classes.CSVReader;
import de.bwv_ac.classes.CSVWriter;
import de.bwv_ac.data.Wish;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class CSVHandlingTests {

    @BeforeEach
    public void setUp() {
        CSVWriter.setDelimiter(";");
    }

    @Test
    public void testReadContent() throws IOException, URISyntaxException {
        File file = new File(ClassLoader.getSystemResource("example_data/BOT1_Veranstaltungsliste.csv").toURI());
        CSVReader reader = new CSVReader();
        //TODO: Der Test sollte genauer sein
        assertFalse(reader.read(file.getAbsolutePath(), true, Wish.class).isEmpty());
    }

    @Test
    public void testGetFirstLine() throws IOException, URISyntaxException {
        File file = new File(ClassLoader.getSystemResource("example_data/BOT1_Veranstaltungsliste.csv").toURI());
        CSVReader reader = new CSVReader();
        //TODO: Erste Zeile kann als Kopie zum abgleich herhalten.
        //assertThat(reader.getFirstLine(file.getAbsolutePath())); // Work in progress
    }
}
