import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class UpdateMembershipTest {
    Membership membership = new Membership();
    int expectedFitnessGoal = 70;

    @Before
    public void setUp() throws Exception {
        String[] input = {"3", "70", "p"};
        ByteArrayInputStream in = new ByteArrayInputStream(String.join(System.lineSeparator(), input).getBytes());
        System.setIn(in);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
    }

    @Test
    public void testUpdateMembershipPositive() {
        Membership.updateMembership(membership);

        assertEquals(Membership.DUE_3MON, membership.getDueDate());
        assertEquals(expectedFitnessGoal, membership.getFitnessGoal());
        assertEquals(Membership.TYPE_PREM, membership.getType());
        assertEquals(300.0, membership.getPrice(), 0.01);

        System.setIn(System.in);
    }

    @Test
    public void testUpdateMembershipNegative() {
        Membership membership = new Membership();
        String[] input = {"6", "10", "e"};
        ByteArrayInputStream in = new ByteArrayInputStream(String.join(System.lineSeparator(), input).getBytes());
        System.setIn(in);

        Membership.updateMembership(membership);

        assertNotSame(Membership.DUE_3MON, membership.getDueDate());
        assertNotSame(20, membership.getFitnessGoal());
        assertNotSame(Membership.TYPE_PREM, membership.getType());
        assertNotSame(300.0, membership.getPrice());

        System.setIn(System.in);
    }
}