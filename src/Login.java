import java.util.HashMap;
import java.util.List;

public class Login {
    private HashMap<String, String> staffs;

    public Login(List<Staff> staffList) {
        this.staffs = new HashMap<String, String>();
        for (Staff staff : staffList) {
            staffs.put(staff.getName(), staff.getPassword());
        }
    }

    public boolean authenticateUser(String username, String password) {
        if (staffs.containsKey(username) && staffs.get(username).equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
