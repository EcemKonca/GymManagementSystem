import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindStaffTest {

    private Staff staff;

    @Before
    public void setUp() throws Exception {
        staff = new Staff("Ecem", "Konca", "12312312", "123", "Izmir","Trainer","Monday, Tuesday, Wednesday, Saturday", "11:00-19:00");

    }

    @After
    public void tearDown() throws Exception {
        staff = new Staff("", "", "", "", "","" ,"", "");
    }


    @Test
    public void testFindStaffPositive() {
        StaffManagementSystem a = new StaffManagementSystem();
        assertEquals(staff.getName(), a.findStaff("Ecem", "Konca").getName());
    }
    @Test
    public void testFindStaffNegative() {
        StaffManagementSystem a = new StaffManagementSystem();
        assertNotSame(staff.getName(), a.findStaff("Melis", "Baran"));
    }
}