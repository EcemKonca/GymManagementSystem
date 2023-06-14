import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsPayedTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testIsPayedPositive() {

        boolean isPayed = true;

        PaymentHistory paymentHistory = new PaymentHistory(1, 1000, isPayed);
        String result = paymentHistory.isPayed();

        assertEquals("Please pay your bill", result);
    }
    @Test
    public void testIsPayednegative() {

        boolean isPayed = false;

        PaymentHistory paymentHistory = new PaymentHistory(0, 0, isPayed);
        String result = paymentHistory.isPayed();

        assertNotEquals("Bill payed.", result);
    }
}





