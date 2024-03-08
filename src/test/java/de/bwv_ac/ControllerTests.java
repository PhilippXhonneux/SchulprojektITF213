package de.bwv_ac;

import de.bwv_ac.classes.Bot1Controller;
import de.bwv_ac.data.Companies;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ControllerTests {
    private Bot1Controller controller;
    private Companies companies;

    @Before
    public void setUp() {
        controller = new Bot1Controller(companies);
    }

    @Test
    public void testGetPanel() {
        assertNotNull(controller.getPanel());
    }
}
