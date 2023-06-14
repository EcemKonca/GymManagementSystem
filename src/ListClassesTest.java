import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ListClassesTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testListClassesPositive() {
        ClassSystem classSystem = new ClassSystem();
        classSystem.addClass("Pilates", 30);
        classSystem.addClass("Yoga", 25);
        classSystem.addClass("Running class", 20);

        ClassSystem.listClasses();

        String expectedOutput = "Available Classes and Quotas:" +System.lineSeparator()+
                "Running Class - Quota: 20" +System.lineSeparator()+
                "Pilates Class - Quota: 30"+System.lineSeparator()+
                "Yoga Class - Quota: 25" +System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testListClassesNegative() {
        ClassSystem classSystem = new ClassSystem();
        classSystem.addClass("Pilates", 30);
        classSystem.addClass("Yoga", 25);
        classSystem.addClass("Running class", 20);

        ClassSystem.listClasses();

        String expectedOutput = "Available Classes and Quotas:" +System.lineSeparator()+
                "Running Class - Quota: 20" +System.lineSeparator()+
                "Yoga Class - Quota: 25" +System.lineSeparator();

        assertNotSame(expectedOutput, outputStream.toString());
    }
}
