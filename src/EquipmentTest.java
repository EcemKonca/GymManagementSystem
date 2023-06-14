import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class EquipmentTest {

    private Equipment equipment;

    @Before
    public void setUp() {
        equipment = new Equipment("Treadmill", new ArrayList<>(), LocalDate.of(2021, 1, 1));
    }
    @After
    public void tearDown() {
        equipment = null;
    }

    @Test
    public void testGetLifetimePositiveCase() {
        LocalDate purchaseDate = LocalDate.of(2021, 1, 1);
        equipment.setPurchaseDate(purchaseDate);

        Period lifetime = equipment.getLifetime();

        assertEquals(Period.between(purchaseDate, LocalDate.now()), lifetime);
    }

    @Test
    public void testGetLifetimeNegativeCase() {
        LocalDate purchaseDate = LocalDate.of(2022, 1, 1);
        equipment.setPurchaseDate(purchaseDate);

        LocalDate currentDate = LocalDate.now();
        Period expectedLifetime = Period.between(purchaseDate, currentDate);
        expectedLifetime = expectedLifetime.plusDays(1);

        Period lifetime = equipment.getLifetime();

        assertNotSame(expectedLifetime, lifetime);
    }
}
