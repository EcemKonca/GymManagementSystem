import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonalDetailsFindDetailTest extends TestCase {

    private static List<PersonalDetails> personalDetailsTestList;
    private String name;
    private String lastname;

    private PersonalDetails detailToTestNegative;
    private String phoneNo;
    private String address;

    private PersonalDetails detailTest;

    @Before
    public void setUp() throws Exception {
        personalDetailsTestList = new ArrayList<>();
        name = "Yaren";
        lastname = "Karabacak";
        phoneNo = "0123987";
        address = "Balcova, Izmir";
        detailToTestNegative = new PersonalDetails("Sarp", "Demir", "000000", "not exist");
        String inputName = name + "\n" + lastname + "\n" + phoneNo + "\n" + address;
        InputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);

        detailTest = PersonalDetails.createPersonalDetails();

    }

    @After
    public void tearDown() throws Exception {
        personalDetailsTestList.clear();
        detailTest = null;
        name = "";
        lastname = "";
        phoneNo = "";
        address = "";

    }

    public static void assertTextEquals(String expected, String actual) {
        assertEquals(expected.length(), actual.length());
        for (int i = 0; i < expected.length(); i++) {
            assertEquals("Mismatch at index " + i, expected.charAt(i), actual.charAt(i));
        }
    }

    @Test
    public void testFindDetailsPositive() {

        PersonalDetails actualDetail = PersonalDetails.findDetail(name, lastname);
        PersonalDetails expectedDetail = detailTest;

        assertTextEquals(expectedDetail.toString(), actualDetail.toString());

    }

    @Test
    public void testFindDetailsNegative() {

        PersonalDetails actualDetail = PersonalDetails.findDetail("Sarp", "Demir");
        PersonalDetails expectedDetail = detailToTestNegative; // never added to list
        // because not created with PersonalDetails.createPersonalDetails();

        assertNotSame(expectedDetail, actualDetail);

    }
}