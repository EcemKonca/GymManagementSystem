import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class DeleteStaffTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private Connection connection;

    @Before
    public void setUp() {
        String url = "jdbc:mysql://localhost:3306/gym management system";
        String username = "root";
        String password = "1234";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void deleteStaffPositive() {
        // Set up input for the test
        String input = "NewJohn\nNewDoe\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

         StaffManagementSystem A = new StaffManagementSystem();
        // Call the method under test
        A.deleteStaff();

        // Verify the output
        String expectedOutput = "Enter first name of the staff to delete:" + System.lineSeparator()
                + "Enter last name of the staff to delete:" + System.lineSeparator() +
                "Staff deleted successfully.";
        assertEquals(expectedOutput, output.toString().trim());

        // Verify that the staff is deleted
        // Add assertions here to check if the staff is deleted from the database or your data structure
    }

    @Test
    public void deleteStaffNegative() {
        // Set up input for the test
        String input = "NonExistent\nStaff\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        StaffManagementSystem A = new StaffManagementSystem();
        // Call the method under test
        A.deleteStaff();

        // Verify the output
        String expectedOutput = "Staff not found.";
        assertNotSame(expectedOutput, output.toString().trim());
    }
}