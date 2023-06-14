import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class StartTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testStartPositive() {
        String input = "12\n" + System.lineSeparator();
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        staffManagementSystem.start();

        String expectedOutput = "Gym Management System" + System.lineSeparator() +
                "1. Add Staff" + System.lineSeparator() +
                "2. Update Staff" + System.lineSeparator() +
                "3. Delete Staff" + System.lineSeparator() +
                "4. Display Staff" + System.lineSeparator() +
                "5. Member Operations" + System.lineSeparator() +
                "6. Equipment and Maintenance Operations" + System.lineSeparator() +
                "7. Add Private Lesson" + System.lineSeparator() +
                "8. List available classes and quotas" + System.lineSeparator() +
                "9. Display Class Attendance Report" + System.lineSeparator() +
                "10. Display Equipment Usage Report" + System.lineSeparator() +
                "11. Display Membership Status Report" + System.lineSeparator() +
                "12. Exit" + System.lineSeparator() +
                "Enter your choice:" + System.lineSeparator() +
                "Thank you for using Gym Management System. Exiting..." + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testStartNegative() {
        String input = "12\n" + System.lineSeparator();
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        staffManagementSystem.start();

        String expectedOutput = "Gym Management System" + System.lineSeparator() +
                "1. Add Staff" + System.lineSeparator() +
                "2. Update Staff" + System.lineSeparator() +
                "3. Delete Staff" + System.lineSeparator() +
                "4. Exit" + System.lineSeparator() +
                "Enter your choice:" + System.lineSeparator() +
                "Exiting..." + System.lineSeparator();

        assertNotSame(expectedOutput, outputStream.toString());
    }
}