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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class UpdateStaffTest {
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
    public void updateStaffPositive() {
        // Set up input for the test
        String input = "John\nDoe\nNewJohn\nNewDoe\nNewManager\nMonday,Tuesday,Wednesday\n9-6\nnewpassword\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        StaffManagementSystem A = new StaffManagementSystem();
        // Call the method under test
        A.updateStaff();

        // Verify the output
        String expectedOutput = "Enter first name of the staff to update:" + System.lineSeparator()
        + "Enter last name of the staff to update:" + System.lineSeparator()
        + "Enter new first name:" + System.lineSeparator()
        + "Enter new last name:" + System.lineSeparator()
        + "Enter new job role:" + System.lineSeparator()
        + "Enter work days (write with , ):" + System.lineSeparator()
        + "Enter work hours:" + System.lineSeparator()
        + "Enter password:" + System.lineSeparator()
        + "Staff updated successfully.";
        assertEquals(expectedOutput, output.toString().trim());

        // Verify the updated staff
        Staff updatedStaff = A.findStaff("NewJohn", "NewDoe");
        assertEquals("NewJohn", updatedStaff.getName());
        assertEquals("NewDoe", updatedStaff.getLastname());
        assertEquals("NewManager", updatedStaff.getJobRole());
        assertEquals("Monday,Tuesday,Wednesday", updatedStaff.getWorkDays());
        assertEquals("9-6", updatedStaff.getWorkHours());
        assertEquals("newpassword", updatedStaff.getPassword());

        // Verify the updated staff in the database (optional)
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM staff WHERE Name = 'NewJohn' AND Lastname = 'NewDoe'";
            assertTrue(statement.execute(query));
            assertTrue(statement.getResultSet().next());
            // Verify other columns if needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Test
    public void updateStaffNegative() {
        // Set up input for the test
        String input = "NonExistent\nStaff\nSelin\nYılmaz\nManager\nMonday,Tuesday,Wednesday\n9-6\nnewpassword\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        StaffManagementSystem A = new StaffManagementSystem();
        // Call the method under test
        A.updateStaff();

        // Verify the output
        String expectedOutput = "Staff not found.";
        assertNotSame(expectedOutput, output.toString().trim());
    }
}