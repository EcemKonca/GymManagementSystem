import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AddClassTest {
    ClassSystem classSystem = new ClassSystem();
    Map<String, Integer> expectedClassQuotas = new HashMap<>();

    String className;
    int quota;

    @Before
    public void setUp() throws Exception {
        className = "Pilates";
        quota = 30;
        expectedClassQuotas.put(className, quota);

        // Act
        classSystem.addClass(className, quota);
    }

    @After
    public void tearDown() throws Exception {
        className = "";
        quota = 0;
    }

    @Test
    public void addClassTestPositive() {
        assertEquals(expectedClassQuotas, classSystem.getClassQuotas());
    }

    @Test
    public void addClassTestNegative() {
        className = "yoga";
        quota = 25;
        assertNotSame(expectedClassQuotas, classSystem.getClassQuotas());
    }
}