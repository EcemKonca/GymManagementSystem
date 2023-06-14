import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddMaintenanceHistoryTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddMaintenanceHistoryPositive() {
        Maintenance.addMaintenanceHistory();
        assertEquals("Replaced battery", Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getDescription());
        assertEquals("2023-01-01", Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getDate());
        assertEquals(500, Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getCost());
        assertEquals("Burak Acar", Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getTechnician());
    }

    @Test
    public void testAddMaintenanceHistoryNegative() {
        Maintenance.addMaintenanceHistory();

        assertNotEquals(3, Maintenance.equipmentList.get(0).getMaintenanceHistory().size());
        assertNotEquals("Installed new software", Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getDescription());
        assertNotEquals("2023-02-15", Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getDate());
        assertNotEquals(100, Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getCost());
        assertNotEquals("Fatma Acar", Maintenance.equipmentList.get(0).getMaintenanceHistory().get(0).getTechnician());

    }
}