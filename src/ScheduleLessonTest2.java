import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ScheduleLessonTest2 {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testScheduleLessonsPositive() {
        // Prepare input for the test
        String input = "Melis\nBaran\nb\nMonday\n10\n30\nEXIT\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method to be tested
        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        staffManagementSystem.scheduleLessons();

        // Verify the output
        String expectedOutput = "Enter first name of the staff to add lesson:" +System.lineSeparator()+
                "Enter last name of the staff to add lesson:" +System.lineSeparator()+
                "If you want to exit adding lessons type 'EXIT', if continue type anything " +System.lineSeparator()+
                "Melis's schedule is empty.. You can choose from Monday,Tuesday,Wednesday between 09:00-16:00"+System.lineSeparator()+
                "Enter the week of the day you want to add lesson:" +System.lineSeparator()+
                "Enter the start hour:" +System.lineSeparator()+
                "Enter the minute:" +System.lineSeparator()+
                "Private lesson scheduled for Monday10:30:00-11:30:00" +System.lineSeparator()+
                "If you want to exit adding lessons type 'EXIT', if continue type anything "+System.lineSeparator();
        assertEquals(expectedOutput, output.toString());
    }
    @Test
    public void testScheduleLessonsNegative() {
        // Prepare input for the test
        String input = "Melis\nBaran\nb\nMonday\n10\n30\nEXIT\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method to be tested
        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        staffManagementSystem.scheduleLessons();

        // Verify the output
        String expectedOutput = "Enter first name of the staff to add lesson:" +System.lineSeparator()+
                "Enter last name of the staff to add lesson:" +System.lineSeparator()+
                "If you want to exit adding lessons type 'EXIT', if continue type anything " +System.lineSeparator()+
                "Private lesson scheduled for Monday10:30:00-11:30:00" +System.lineSeparator()+
                "If you want to exit adding lessons type 'EXIT', if continue type anything "+System.lineSeparator();
        assertNotSame(expectedOutput, output.toString());
    }
}