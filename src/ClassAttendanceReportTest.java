import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ClassAttendanceReportTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testGenerateClassAttendanceReportPositive() {
        // Create test data
        ArrayList<Staff> staff = new ArrayList<>();
        ArrayList<MemberRecords> memberRecords = new ArrayList<>();

        // Create a staff member
        Staff staffMember = new Staff("Melis", "Baran", "5556465", "Izmir", "Trainer", "Trainer","Monday","09:00-15:00");
        Map<String, ArrayList<String>> lessonTimeListWithDays = new HashMap<>();
        ArrayList<String> lesson1Attendees = new ArrayList<>();
        lesson1Attendees.add("Member1");
        lesson1Attendees.add("Member2");
        lessonTimeListWithDays.put("Lesson 1", lesson1Attendees);
        staffMember.setLessonTimeListWithDays((HashMap<String, ArrayList<String>>) lessonTimeListWithDays);
        staff.add(staffMember);

        // Create a member record
        PersonalDetails personalDetails = new PersonalDetails("Melis", "Baran","05435935","izmir");
        Membership membership= new Membership();
        PaymentHistory paymentHistory=new PaymentHistory(membership);
        MemberRecords memberRecord = new MemberRecords(personalDetails,membership,paymentHistory);
        memberRecords.add(memberRecord);

        // Set the test data in the main class
        StaffManagementSystem.setStaff(staff);
        StaffManagementSystem.setMemberRecords(memberRecords);

        // Invoke the method
        StaffManagementSystem s =new StaffManagementSystem();
        s.generateClassAttendanceReport();

        // Assert the output
        String expectedOutput = "Class Attendance Report:" + System.lineSeparator() +
                "Trainer: Melis Baran"  + System.lineSeparator() +
                "Class Attendance:" +  System.lineSeparator() +
                "------------" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
    @Test
    public void testGenerateClassAttendanceReportNegative() {
        // Create test data
        ArrayList<Staff> staff = new ArrayList<>();
        ArrayList<MemberRecords> memberRecords = new ArrayList<>();

        // Create a staff member
        Staff staffMember = new Staff("Melis", "Baran", "5556465", "Izmir", "Trainer", "Trainer","Monday","09:00-15:00");
        Map<String, ArrayList<String>> lessonTimeListWithDays = new HashMap<>();
        ArrayList<String> lesson1Attendees = new ArrayList<>();
        lesson1Attendees.add("Member1");
        lesson1Attendees.add("Member2");
        lessonTimeListWithDays.put("Lesson 1", lesson1Attendees);
        staffMember.setLessonTimeListWithDays((HashMap<String, ArrayList<String>>) lessonTimeListWithDays);
        staff.add(staffMember);

        // Create a member record
        PersonalDetails personalDetails = new PersonalDetails("Melis", "Baran","05435935","izmir");
        Membership membership= new Membership();
        PaymentHistory paymentHistory=new PaymentHistory(membership);
        MemberRecords memberRecord = new MemberRecords(personalDetails,membership,paymentHistory);
        memberRecords.add(memberRecord);

        // Set the test data in the main class
        StaffManagementSystem.setStaff(staff);
        StaffManagementSystem.setMemberRecords(memberRecords);

        // Invoke the method
        StaffManagementSystem s =new StaffManagementSystem();
        s.generateClassAttendanceReport();

        // Assert the output
        String expectedOutput = "Class Attendance Report:" + System.lineSeparator() +
                "Trainer: Ecem Konca"  + System.lineSeparator() +
                "Class Attendance:" +  System.lineSeparator() +
                "------------" + System.lineSeparator();
        assertNotSame(expectedOutput, outContent.toString());
    }
}