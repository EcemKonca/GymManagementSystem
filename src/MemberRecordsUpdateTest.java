import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MemberRecordsUpdateTest {

    private MemberRecords memberRecords;
    private List<MemberRecords> records;

    @Before
    public void setUp() {
        memberRecords = new MemberRecords();
        records = new ArrayList<>();

        PersonalDetails personalDetails1 = new PersonalDetails("Ecem", "Konca", "1234567890", "Izmir");
        Membership membership1 = new Membership();
        records.add(new MemberRecords(personalDetails1, membership1, new PaymentHistory(0,0,true)));

        PersonalDetails personalDetails2 = new PersonalDetails("Halis", "Cin", "9876543210", "Istanbul");
        Membership membership2 = new Membership();
        records.add(new MemberRecords(personalDetails2, membership2, new PaymentHistory(0,0,true)));
    }

    @Test
    public void testFindToUpdateMemberPositive() {
        String input = "Ecem\nKonca\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        memberRecords.findToUpdateMember();

        String expectedOutput = "Enter first name of the member to update:\nEnter last name of the member to update:\n";
        assertEquals(expectedOutput, outContent.toString().replace("\r", ""));

    }

    @Test
    public void testFindToUpdateMemberNegative() {
        String input = "Halis\nCin\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        memberRecords.findToUpdateMember();

        String expectedOutput = "Enter first name of the member to update:\nEnter last name of the member to update:\nRecord could not be found...\n";
        assertNotSame(expectedOutput, outContent.toString().replace("\r", ""));

    }
}