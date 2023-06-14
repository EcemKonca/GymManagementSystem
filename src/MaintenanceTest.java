import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MaintenanceTest {
    Maintenance myClass;
    List<Equipment> equipmentList;

    @Before
    public void setUp() {
        myClass = new Maintenance();
        equipmentList = myClass.getEquipmentList();
    }

    @After
    public void tearDown() {
        myClass = null;
        equipmentList = null;
    }

    @Test
    public void testAddEquipmentPositive() {
        myClass.addEquipment();

        assertEquals(6, equipmentList.size());
    }

    @Test
    public void testAddEquipmentNegative() {

        List<Equipment> equipmentList = myClass.getEquipmentList();

        assertNotEquals(10, equipmentList.size());
    }
}