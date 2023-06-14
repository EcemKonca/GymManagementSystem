import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MembershipStatusReportTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testGenerateMembershipStatusReportPositive() {
        // Prepare test data
        List<MemberRecords> memberRecordsList = new ArrayList<>();

        PersonalDetails personalDetails1 = new PersonalDetails("Melis", "Baran", "123456789", "Izmir");
        Membership membership1 = new Membership();
        PaymentHistory paymentHistory1 = new PaymentHistory(membership1);
        MemberRecords memberRecord1 = new MemberRecords(personalDetails1, membership1, paymentHistory1);
        memberRecordsList.add(memberRecord1);

        PersonalDetails personalDetails2 = new PersonalDetails("Ecem", "Konca", "987654321", "Istanbul");
        Membership membership2 = new Membership();
        PaymentHistory paymentHistory2 = new PaymentHistory(membership2);
        MemberRecords memberRecord2 = new MemberRecords(personalDetails2, membership2, paymentHistory2);
        memberRecordsList.add(memberRecord2);

        // Set the test data in the MemberRecords class (assuming static method setRecords() is available)
        MemberRecords.setRecords(memberRecordsList);

        // Call the method under test
        StaffManagementSystem.generateMembershipStatusReport();

        // Verify the output
        String expectedOutput = "Membership Status Report:" + System.lineSeparator() +
                "Name: Melis" + System.lineSeparator() +
                "Last Name: Baran" + System.lineSeparator() +
                "Adress: Izmir" + System.lineSeparator() +
                "Phone Number: 123456789" + System.lineSeparator() +
                "Membership Type :economic" + System.lineSeparator() +
                "Membership Status: Active" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "Name: Ecem" + System.lineSeparator() +
                "Last Name: Konca" + System.lineSeparator() +
                "Adress: Istanbul" + System.lineSeparator() +
                "Phone Number: 987654321" + System.lineSeparator() +
                "Membership Type :economic" + System.lineSeparator() +
                "Membership Status: Active" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "Name: ecem" + System.lineSeparator() +
                "Last Name: konca" + System.lineSeparator() +
                "Adress: Izmir" + System.lineSeparator() +
                "Phone Number: 1234" + System.lineSeparator() +
                "Membership Type :economic" + System.lineSeparator() +
                "Membership Status: Active" + System.lineSeparator() +
                "------------";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testGenerateMembershipStatusReportNegative() {
        // Prepare test data
        List<MemberRecords> memberRecordsList = new ArrayList<>();

        PersonalDetails personalDetails1 = new PersonalDetails("Melis", "Baran", "123456789", "Izmir");
        Membership membership1 = new Membership();
        PaymentHistory paymentHistory1 = new PaymentHistory(membership1);
        MemberRecords memberRecord1 = new MemberRecords(personalDetails1, membership1, paymentHistory1);
        memberRecordsList.add(memberRecord1);

        // Set the test data in the MemberRecords class (assuming static method setRecords() is available)
        MemberRecords.setRecords(memberRecordsList);

        // Call the method under test
        StaffManagementSystem.generateMembershipStatusReport();

        // Verify the output
        String expectedOutput = "Membership Status Report:" + System.lineSeparator() +
                "Name: Melis" + System.lineSeparator() +
                "Last Name: Baran" + System.lineSeparator() +
                "Membership Status: Active" + System.lineSeparator() +
                "------------" + System.lineSeparator();
        assertNotSame(expectedOutput, outputStream.toString().trim());
    }
}