import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class CreateMemberTest {
    private final InputStream systemInBackup = System.in;
    private final PrintStream systemOutBackup = System.out;

    @Before
    public void setUp() {
        // No setup required for now
    }

    @Test
    public void testCreateMemberPositive() {
        String input = "John\nDoe\n1234567890\n123 Main St\ny\n";
        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);

        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));

        MemberRecords member = MemberRecords.createMember();

        String expectedOutput = "Start with personal information"+System.lineSeparator()
                + "Enter your name: "+System.lineSeparator()
                + "Enter your lastname: "+System.lineSeparator()
                + "Enter your phone number: "+System.lineSeparator()
                + "Enter your address: "+System.lineSeparator()
                + "Do you want to go with default membership or choose?: (y for default / n for choose / d for delete )"+System.lineSeparator();
        assertEquals(expectedOutput, testOutput.toString());

        assertNotNull(member);
        assertNotNull(member.getMembership());
        assertNotNull(member.getPersonalDetails());
    }

    @Test
    public void testCreateMemberNegative() {
        String input = "John\nDoe\n1234567890\n123 Main St\ny\n";
        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);

        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));

        MemberRecords member = MemberRecords.createMember();

        String expectedOutput = "Start with personal information"+System.lineSeparator()
                + "Enter your name: "+System.lineSeparator()
                + "Do you want to go with default membership or choose?: (y for default / n for choose / d for delete )"+System.lineSeparator();
        assertNotSame(expectedOutput, testOutput.toString());
    }
}