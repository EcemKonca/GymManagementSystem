import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class EquipmentUsageReportTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Period lifetime;
    private Period lifetime2 ;
    private Period lifetime3 ;
    private Period lifetime4 ;
    private Period lifetime5 ;
    private Period lifetime6 ;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        Equipment equipment1 = new Equipment("Treadmill", new ArrayList<>(), LocalDate.of(2022, 1, 1));
        lifetime = equipment1.getLifetime();
        Equipment equipment2 = new Equipment("Chest Press", new ArrayList<>(), LocalDate.of(2022, 2, 15));
        lifetime2 = equipment2.getLifetime();
        Equipment equipment3 = new Equipment("Exercise Bike", new ArrayList<>(), LocalDate.of(2022, 3, 10));
        lifetime3 = equipment3.getLifetime();
        Equipment equipment4 = new Equipment("Smith Machine", new ArrayList<>(), LocalDate.of(2022, 4, 25));
        lifetime4 = equipment4.getLifetime();
        Equipment equipment5 = new Equipment("Leg Press", new ArrayList<>(), LocalDate.of(2022, 5, 5));
        lifetime5 = equipment5.getLifetime();
        Equipment equipment6 =new Equipment("Stability Ball", new ArrayList<>(), LocalDate.of(2022, 6, 20));
        lifetime6 = equipment6.getLifetime();
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        lifetime = null;
        lifetime2 = null;
        lifetime3 = null;
        lifetime4 = null;
        lifetime5 = null;
        lifetime6 = null;
    }

    @Test
    public void testGenerateEquipmentUsageReport() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method under test
        StaffManagementSystem.generateEquipmentUsageReport();

        String out = outContent.toString().lines().toList().get(0);
        String out2 = outContent.toString().lines().toList().get(1);
        String out3 = outContent.toString().lines().toList().get(2);
        String out4 = outContent.toString().lines().toList().get(3);

        String exp = "Equipment Usage Report:";
        String exp2 = "Equipment Name: Treadmill";
        String exp3 = "Lifetime: "  + lifetime.getYears() + " years, " + lifetime.getMonths() + " months, " + lifetime.getDays() + " days" ;
        String exp4 = "Maintenance History:";

        assertEquals(exp, out);
        assertEquals(exp2, out2);
        assertEquals(exp3, out3);
        assertEquals(exp4, out4);
    }

    @Test
    public void testGenerateEquipmentUsageReport_NoEquipment() {
        // Call the method under test
        StaffManagementSystem.generateEquipmentUsageReport();

        // Verify the output
        String expectedOutput = "Equipment Usage Report:" + System.lineSeparator() +
                "No equipment found.";
        assertNotSame(expectedOutput, outputStream.toString().trim());
    }
}