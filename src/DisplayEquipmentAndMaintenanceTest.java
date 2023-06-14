import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import static org.junit.Assert.*;

public class DisplayEquipmentAndMaintenanceTest {

    @Before
    public void setUp() throws Exception {

        Maintenance.addEquipment();
        Maintenance.addMaintenanceHistory();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDisplayEquipmentAndMaintenanceHistoryPositive() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Maintenance.displayEquipmentAndMaintenanceHistory();


        String out = outContent.toString().lines().toList().get(0);
        String out2 = outContent.toString().lines().toList().get(2);
        String out3 = outContent.toString().lines().toList().get(3);

        String exp = "Equipment Name: Treadmill";
        String exp2 = "Maintenance History:";
        String exp3 = "Replaced battery - 2023-01-01 - 500$ - Burak Acar";


        assertEquals(exp, out);
        assertEquals(exp2, out2);
        assertEquals(exp3, out3);

    }

    @Test
    public void testDisplayEquipmentAndMaintenanceHistoryNegative() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Maintenance.displayEquipmentAndMaintenanceHistory();

        String expectedOutput = "";

        assertNotEquals(expectedOutput, outContent.toString());
    }

}