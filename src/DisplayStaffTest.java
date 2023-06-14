import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DisplayStaffTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
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

        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.setOut(originalOut);
    }

    @Test
    public void testDisplayStaffPositive() {
        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        staffManagementSystem.displayStaff();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM staff WHERE Name = 'Ecem' AND Lastname = 'Konca'";
            assertTrue(statement.execute(query));
            assertTrue(statement.getResultSet().next());
            assertEquals("Ecem", statement.getResultSet().getString("Name"));
            assertEquals("Konca", statement.getResultSet().getString("Lastname"));
            assertEquals("developer", statement.getResultSet().getString("JobRole"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDisplayStaffNegative() {
        // Call the method under test
        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        staffManagementSystem.displayStaff();

        // Verify the output
        String expectedOutput = "List of staffs:" + System.lineSeparator() +
                "First Name: Ecem" + System.lineSeparator() +
                "Last Name: Konca" + System.lineSeparator() +
                "Job Role: developer" + System.lineSeparator() +
                "Work Days: friday,tuesday" + System.lineSeparator() +
                "Work Hours: 12.00-19-00" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "First Name: Halis" + System.lineSeparator() +
                "Last Name: Cin" + System.lineSeparator() +
                "Job Role: Developer" + System.lineSeparator() +
                "Work Days: friday,wednesday,tuesday" + System.lineSeparator() +
                "Work Hours: 8:30-19:30" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "First Name: Melis" + System.lineSeparator() +
                "Last Name: Baran" + System.lineSeparator() +
                "Job Role: Trainer" + System.lineSeparator() +
                "Work Days: Monday,Tuesday,Wednesday" + System.lineSeparator() +
                "Work Hours: 09:00-16:00" + System.lineSeparator() +
                "------------" + System.lineSeparator();
        assertNotSame(expectedOutput, outputStream.toString().trim());
    }
}
