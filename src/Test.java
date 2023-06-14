import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        StaffManagementSystem staffManagementSystem = new StaffManagementSystem();
        Login gym = new Login(staffManagementSystem.getStaff());

        System.out.println("Welcome to the GYM MANAGEMENT SYSTEM\n");

        Scanner scanner = new Scanner(System.in);
        boolean isLogged = true;
        while (isLogged) {
            System.out.print("Please enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            if (gym.authenticateUser(username, password)) {
                System.out.println("Login successful!");
                System.out.println("Welcome " + username + "!");
                staffManagementSystem.start();
            } else {
                System.out.println("Incorrect username or password. Please try again.");
                continue;
            }
            break;

        }
    }
}
