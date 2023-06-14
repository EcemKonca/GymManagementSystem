import java.util.HashMap;
import java.util.Map;

public class ClassSystem {
    private Map<String, Integer> classQuotas;

    public ClassSystem() {
        classQuotas = new HashMap<>();
    }

    public Map<String, Integer> getClassQuotas() {
        return classQuotas;
    }

    public void addClass(String className, int quota) {
        classQuotas.put(className, quota);
    }

    // Method to list classes and their quotas
    public static void listClasses() {
        ClassSystem classSystem = new ClassSystem();
        classSystem.addClass("Pilates Class", 30);
        classSystem.addClass("Yoga Class", 25);
        classSystem.addClass("Running Class", 20);
        System.out.println("Available Classes and Quotas:");
        for (Map.Entry<String, Integer> entry : classSystem.classQuotas.entrySet()) {
            System.out.println(entry.getKey() + " - Quota: " + entry.getValue());
        }
    }
}