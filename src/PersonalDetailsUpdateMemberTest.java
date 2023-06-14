import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonalDetailsUpdateMemberTest {

    private static List<PersonalDetails> personalDetailsTestList;
    private String name;
    private String lastname;

    private String newName;
    private String newLastname;
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

        String inputName = name + "\n" + lastname + "\n" + phoneNo + "\n" + address;
        InputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);

        detailTest = PersonalDetails.createPersonalDetails();
        newName = "Yaren1";
        newLastname = "Karabacak1";

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

    @Test
    public void testUpdateMemberPersonalDetailsPositive() {

        String inputName = newName + "\n" + newLastname + "\n";
        InputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);
        detailTest.updateMemberPersonalDetails(name, lastname);

        PersonalDetails updatedDetail = PersonalDetails.findDetail(newName, newLastname);

        String expectedName = newName;
        String actualName = updatedDetail.getName();

        Assert.assertEquals(expectedName, actualName);

    }

    @Test
    public void testUpdateMemberPersonalDetailsNegative() {

        String inputName = newName + "\n" + newLastname + "\n";
        InputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);
        detailTest.updateMemberPersonalDetails("jane", "doe");

        PersonalDetails updatedDetail = PersonalDetails.findDetail(newName, newLastname); // should return null

        Assert.assertNull(updatedDetail);

    }
}