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
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class AddStaffTest {
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
    public void addStaffPositive() {
        String input = "John\nDoe\n123456789\npassword\n123 Main St\nManager\nMonday,Tuesday,Wednesday\n9-5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        StaffManagementSystem A = new StaffManagementSystem();

        A.addStaff();

        String expectedOutput = "Enter first name:" + System.lineSeparator()+
                "Enter last name:" + System.lineSeparator()+
                "Enter phone number:" + System.lineSeparator()+
                "Enter password:" + System.lineSeparator()+
                "Enter address:" + System.lineSeparator()+
                "Enter job role:" + System.lineSeparator()+
                "Enter work days (write with , ):" + System.lineSeparator()+
                "Enter work hours:" + System.lineSeparator()+
                "A new staff has been added successfully.";
        assertEquals(expectedOutput, output.toString().trim());

        // Verify the inserted data in the database (optional)
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM staff WHERE Name = 'John' AND Lastname = 'Doe'";
            assertTrue(statement.execute(query));
            assertTrue(statement.getResultSet().next());
            assertEquals("123456789", statement.getResultSet().getString("PhoneNo"));
            // Verify other columns if needed
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addStaffNegative() {
        // Set up input for the test
        String input = "John\nDoe\n123456789\npassword\n123 Main St\nManager\nMonday,Tuesday,Wednesday\n9-5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        StaffManagementSystem A = new StaffManagementSystem();
        // Call the method under test
        A.addStaff();

        // Verify the output
        String expectedOutput = "Enter first name:" + System.lineSeparator()+
                "Enter last name:" + System.lineSeparator()+
                "Enter phone number:" + System.lineSeparator()+
                "Enter password:" + System.lineSeparator()+
                "Enter job role:" + System.lineSeparator()+
                "Enter work days (write with , ):" + System.lineSeparator()+
                "Enter work hours:" + System.lineSeparator()+
                "A new staff has been added successfully.";
        assertNotSame(expectedOutput, output.toString().trim());
    }
}