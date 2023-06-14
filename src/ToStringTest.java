import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToStringTest {

    private PersonalDetails personalDetails;

    @Before
    public void setUp() {
        personalDetails = new PersonalDetails();
        personalDetails.setName("Melis");
        personalDetails.setLastname("Baran");
        personalDetails.setPhoneNo("1234567890");
        personalDetails.setAddress("Izmir");
    }

    @Test
    public void testToStringPositive() {
        String expected = "PersonalDetails: name='Melis', lastname='Baran', phoneNo='1234567890', address='Izmir'";

        String actual = personalDetails.toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToStringNegative() {
        String expected = "PersonalDetails: name='Ecem', lastname='Konca', phoneNo='1234567890', address='Izmir'";

        String actual = personalDetails.toString();

        Assert.assertNotEquals(expected, actual);
    }
}