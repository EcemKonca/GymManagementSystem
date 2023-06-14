import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class StartMemberOperationsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        String[] input = {"E"};
        ByteArrayInputStream in = new ByteArrayInputStream(String.join(System.lineSeparator(), input).getBytes());
        System.setIn(in);
        System.setOut(new PrintStream(outContent));
        StaffManagementSystem system = new StaffManagementSystem();
        system.startMemberOperations();
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void startMemberOperationsTestPositive() {
        String expectedOutput = "Choose an operation: " + System.lineSeparator()
                + "A) Create Member" + System.lineSeparator()
                + "B) Update Member" + System.lineSeparator()
                + "C) Delete Member" + System.lineSeparator()
                + "D) Show Member Details" + System.lineSeparator()
                + "E) Exit Member Operations" + System.lineSeparator()
                + "Exiting..." + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void startMemberOperationsTestNegative() {
        String expectedOutput = "Choose an operation: " + System.lineSeparator()
                + "A) Create Member" + System.lineSeparator()
                + "B) Update Member" + System.lineSeparator()
                + "Exiting..." + System.lineSeparator();

        assertNotSame(expectedOutput, outContent.toString());
    }
}
