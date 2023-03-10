/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class JenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testRun() {
        TestMenu menu = new TestMenu();

        try {
            assertEquals("Hello", menu.run(0, String.class));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testShow() {
        TestMenu menu = new TestMenu();

        menu.show();

        assertTrue("",
                outContent.toString().contains("==") &&
                        outContent.toString().contains("Test Menu") &&
                        outContent.toString().contains("Hello"));
    }

    @Test
    public void testQuery() {
        TestMenu menu = new TestMenu();

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        menu.query();
    }
}
