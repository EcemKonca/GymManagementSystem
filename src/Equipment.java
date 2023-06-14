import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Equipment {
    private String name;
    private List<Maintenance> maintenanceHistory;
    private LocalDate purchaseDate;

    public Equipment(String name, List<Maintenance> maintenanceHistory, LocalDate purchaseDate) {
        this.name = name;
        this.maintenanceHistory = maintenanceHistory;
        this.purchaseDate = purchaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Maintenance> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public void setMaintenanceHistory(List<Maintenance> maintenanceHistory) {
        this.maintenanceHistory = maintenanceHistory;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Period getLifetime() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(purchaseDate, currentDate);
    }
}
