import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CreatePersonalDetailsTest {

    private InputStream stdin;

    @Before
    public void setUp() throws Exception {
        String input = "Ecem\nKonca\n1234567890\nIzmir\n";
        stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(stdin);
    }

    @Test
    public void testCreatePersonalDetailsPositive() {
        PersonalDetails expectedResult = new PersonalDetails("Ecem", "Konca", "1234567890", "Izmir");
        PersonalDetails actual = PersonalDetails.createPersonalDetails();

        assertEquals(expectedResult.getName(), actual.getName());
        assertEquals(expectedResult.getLastname(), actual.getLastname());
        assertEquals(expectedResult.getPhoneNo(), actual.getPhoneNo());
        assertEquals(expectedResult.getAddress(), actual.getAddress());
    }
    @Test
    public void testCreatePersonalDetailsNegative() {
        PersonalDetails expected = new PersonalDetails("Zeynep", "Demir", "1234", "Istanbul");
        PersonalDetails actual = PersonalDetails.createPersonalDetails();
        assertNotSame(expected.getName(), actual.getName());
        assertNotSame(expected.getLastname(), actual.getLastname());
        assertNotSame(expected.getPhoneNo(), actual.getPhoneNo());
        assertNotSame(expected.getAddress(), actual.getAddress());
    }
}
