import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Maintenance {
    private String description;
    private String date;
    private int cost;
    private String technician;
    static ArrayList<Equipment> equipmentList = new ArrayList<>(); // Composition -> to get the information of equipments

    public Maintenance(String description, String date, int cost, String technician) {
        this.description = description;
        this.date = date;
        this.cost = cost;
        this.technician = technician;
    }
    public Maintenance(){}

    public static ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public static void addEquipment() {
        LocalDate treadmillPurchaseDate = LocalDate.of(2022, 1, 1);
        equipmentList.add(new Equipment("Treadmill", new ArrayList<>(), treadmillPurchaseDate));

        LocalDate chestPressPurchaseDate = LocalDate.of(2022, 2, 15);
        equipmentList.add(new Equipment("Chest Press", new ArrayList<>(), chestPressPurchaseDate));

        LocalDate exerciseBikePurchaseDate = LocalDate.of(2022, 3, 10);
        equipmentList.add(new Equipment("Exercise Bike", new ArrayList<>(), exerciseBikePurchaseDate));

        LocalDate smithMachinePurchaseDate = LocalDate.of(2022, 4, 25);
        equipmentList.add(new Equipment("Smith Machine", new ArrayList<>(), smithMachinePurchaseDate));

        LocalDate legPressPurchaseDate = LocalDate.of(2022, 5, 5);
        equipmentList.add(new Equipment("Leg Press", new ArrayList<>(), legPressPurchaseDate));

        LocalDate stabilityBallPurchaseDate = LocalDate.of(2022, 6, 20);
        equipmentList.add(new Equipment("Stability Ball", new ArrayList<>(), stabilityBallPurchaseDate));
    }

    // Add some Maintenance objects to the Equipment objects
    public static void addMaintenanceHistory() {

        addEquipment();

        equipmentList.get(0).getMaintenanceHistory().add(new Maintenance("Replaced battery", "2023-01-01", 500, "Burak Acar"));
        equipmentList.get(0).getMaintenanceHistory().add(new Maintenance("Installed new software", "2023-02-15", 150, "Fatma Acar"));
        equipmentList.get(1).getMaintenanceHistory().add(new Maintenance("Replaced ink cartridge", "2023-03-10", 200, "Burak Acar"));
        equipmentList.get(2).getMaintenanceHistory().add(new Maintenance("Replaced battery", "2022-12-20", 350, "Fatma Acar"));
        equipmentList.get(2).getMaintenanceHistory().add(new Maintenance("Repair broken part", "2023-02-12", 1500, "Burak Acar"));
        equipmentList.get(2).getMaintenanceHistory().add(new Maintenance("Installed new software", "2023-04-15", 150, "Fatma Acar"));
        equipmentList.get(3).getMaintenanceHistory().add(new Maintenance("Replaced ink cartridge", "2023-02-20", 750, "Fatma Acar"));
        equipmentList.get(3).getMaintenanceHistory().add(new Maintenance("Lubricate machine", "2023-04-10", 250, "Burak Acar"));
        equipmentList.get(4).getMaintenanceHistory().add(new Maintenance("Replaced ink cartridge", "2022-12-10", 400, "Fatma Acar"));
        equipmentList.get(4).getMaintenanceHistory().add(new Maintenance("Lubricate machine", "2023-03-18", 300, "Burak Acar"));
        equipmentList.get(5).getMaintenanceHistory().add(new Maintenance("Regular cleaning", "2023-02-29", 200, "Fatma Acar"));
        equipmentList.get(5).getMaintenanceHistory().add(new Maintenance("Regular cleaning", "2023-04-29", 200, "Fatma Acar"));
    }

    // Method to display equipments and their maintenance history
    public static List<Equipment> displayEquipmentAndMaintenanceHistory() {

        //createEquipmentMaintenanceHistory();
        addMaintenanceHistory();

        for (Equipment equipment : equipmentList) {
            List<Maintenance> maintenanceHistory = equipment.getMaintenanceHistory();
            if (!maintenanceHistory.isEmpty()) {
                System.out.println("Equipment Name: " + equipment.getName());
                Period lifetime = equipment.getLifetime();
                System.out.println("Lifetime: " + lifetime.getYears() + " years, " + lifetime.getMonths() + " months, " + lifetime.getDays() + " days");
                System.out.println("Maintenance History:");
                for (Maintenance maintenance : maintenanceHistory) {
                    System.out.println(maintenance.getDescription() + " - " + maintenance.getDate() + " - " + maintenance.getCost() + "$" + " - " + maintenance.getTechnician());
                }
                System.out.println();
            }
        }

        return null;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

}