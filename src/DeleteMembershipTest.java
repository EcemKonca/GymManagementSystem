import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class DeleteMembershipTest {

    private String name;
    private String lastname;
    private String phoneNo;
    private String address;

    private MemberRecords recordTest;

    @Before
    public void setUp() throws Exception {
        name = "Yaren";
        lastname = "Karabacak";
        phoneNo = "0123987";
        address = "Balcova, Izmir";
        String membershipChoice = "y";
        int weightToLose = 10;
        String membershipType = "e";

        String input = name + "\n" + lastname + "\n" + phoneNo + "\n" + address + "\n" + membershipChoice + "\n" + weightToLose + "\n" + membershipType + "\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));


        recordTest = MemberRecords.createMember();

    }

    @After
    public void tearDown() throws Exception {
        recordTest = null;
        name = "";
        lastname = "";
        phoneNo = "";
        address = "";
    }

    @Test
    public void testDeleteMembershipPositive() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String inputName = name + "\n" + lastname + "\n";
        InputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);
        MemberRecords.deleteMembership();

        String expectedOutput = "Enter first name of the member to delete:" + System.lineSeparator()
                + "Enter last name of the member to delete:" + System.lineSeparator() +
                "Record is deleted" + System.lineSeparator();

       assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDeleteMembershipNegative() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        String inputName = name.toUpperCase() + "\n" + lastname.toUpperCase() + "\n";
        InputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);
        MemberRecords.deleteMembership();

        String expectedOutput = "Enter first name of the member to delete:" + System.lineSeparator()
                + "Enter last name of the member to delete:" + System.lineSeparator() +
                "Record is deleted" + System.lineSeparator();

        assertNotSame(expectedOutput, outContent.toString());
    }
}
