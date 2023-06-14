import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ShowMemberRecordsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testShowMemberRecordsPositive() {
        PersonalDetails personalDetails = new PersonalDetails("Ecem", "Konca", "12346", "izmir");
        Membership membership = new Membership();
        PaymentHistory paymentHistory = new PaymentHistory(0, 0, true);

        MemberRecords memberRecords = new MemberRecords(personalDetails, membership, paymentHistory);
        memberRecords.showMemberRecords();

        String expectedOutput = "PersonalDetails: name='Ecem', lastname='Konca', phoneNo='12346', address='izmir'" +
                System.lineSeparator() +
                System.lineSeparator() +
                "Membership: type='economic', price=200.0, fitness goal=10kg, dueDate= 6 months'" +
                System.lineSeparator() +
                System.lineSeparator() + "PaymentHistory: registeredDate='null', billDate='null', isPayed=false'," +
                " payedPrice=0.0";

        assertEquals(expectedOutput, outputStream.toString().trim());
    }
    @Test
    public void testShowMemberRecordsNegative() {
        PersonalDetails personalDetails = new PersonalDetails("Ecem", "Konca", "12346", "izmir");
        Membership membership = new Membership();
        PaymentHistory paymentHistory = new PaymentHistory(0, 0, true);

        MemberRecords memberRecords = new MemberRecords(personalDetails, membership, paymentHistory);
        memberRecords.showMemberRecords();

        String expectedOutput = "PersonalDetails: name='John', lastname='Doe', phoneNo='izmir', address='123'" +

                System.lineSeparator() + "PaymentHistory: registeredDate='null', billDate='null', isPayed=false'," +
                " payedPrice=0.0";

        assertNotSame(expectedOutput, outputStream.toString().trim());
    }
}
