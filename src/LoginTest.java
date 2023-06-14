import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoginTest {

    private Login login;
    private List<Staff> staffList;
    StaffManagementSystem A = new StaffManagementSystem();
    Staff updatedStaff = A.findStaff("Ecem", "Konca");

    @Before
    public void setUp() {
        staffList = new ArrayList<>();
        staffList.add(updatedStaff);

        login = new Login(staffList);
    }

    @Test
    public void testAuthenticateUserPositive() {
        boolean expected = true;
        boolean actual = login.authenticateUser(updatedStaff.getName(),updatedStaff.getPassword());

        assertEquals(expected,actual);
    }

    @Test
    public void testAuthenticateUserNegative() {
        boolean expected = true;
        boolean actual = login.authenticateUser(updatedStaff.getName(),updatedStaff.getLastname());

        assertNotSame(expected,actual);
    }
}