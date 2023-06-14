import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class CreateMembershipTest {

    @Before
    public void setUp() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("6\n10\ne\n".getBytes());
        System.setIn(in);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(System.out);
    }

    @Test
    public void testCreateMembershipPositive() {
        Membership membership = new Membership();
        String answer = "y";
        Membership.createMembership(membership, answer);
        assertEquals(Membership.DUE_6MON, membership.getDueDate());
        assertEquals(10, membership.getFitnessGoal());
        assertEquals(Membership.TYPE_ECO, membership.getType());
        assertEquals(200.0, membership.getPrice(), 0.01);
    }

    @Test
    public void testCreateMembershipNegative() {
        Membership membership = new Membership();
        String answer = "x";
        Membership.createMembership(membership, answer);

        assertNotSame(Membership.DUE_3MON, membership.getDueDate());
        assertNotSame(20, membership.getFitnessGoal());
        assertNotSame(Membership.TYPE_PREM, membership.getType());
        assertNotSame(300.0, membership.getPrice());
    }
}