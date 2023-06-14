import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class StaffManagementSystem {

    String url = "jdbc:mysql://localhost:3306/gym management system";
    String user = "root";
    String password = "1234";
    Connection connection;

    private final List<Staff> staff;  // Composition -> to get staff's info
    private final Scanner scanner;

    public static void setStaff(ArrayList<Staff> staff) {
    }

    public static void setMemberRecords(ArrayList<MemberRecords> memberRecords) {
    }

    // Constructor
    public StaffManagementSystem() {
        staff = new ArrayList<>();
        scanner = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql = "SELECT * FROM staff";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString("Name");
                String lastname = result.getString("Lastname");
                String phoneNo = result.getString("PhoneNo");
                String address = result.getString("Address");
                String jobRole = result.getString("JobRole");
                String workDays = result.getString("WorkDays");
                String workHours = result.getString("WorkHours");
                String password = result.getString("Password");

                Staff staff = new Staff(name, lastname, phoneNo, password, address, jobRole, workDays, workHours);
                this.staff.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a staff
    public void addStaff() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNo = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter job role:");
        String jobRole = scanner.nextLine();
        System.out.println("Enter work days (write with , ):");
        String workDays = scanner.nextLine();
        System.out.println("Enter work hours:");
        String workHours = scanner.nextLine();

        try {
            String sql = "INSERT INTO staff (Name, Lastname, PhoneNo, Password, Address, JobRole, Workdays, WorkHours) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNo);
            statement.setString(4, password);
            statement.setString(5, address);
            statement.setString(6, jobRole);
            statement.setString(7, workDays); // Pass workDays instead of workingDays
            statement.setString(8, workHours);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new staff has been added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to update a staff
    public void updateStaff() {
        System.out.println("Enter first name of the staff to update:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of the staff to update:");
        String lastName = scanner.nextLine();

        Staff staffToUpdate = findStaff(firstName, lastName);
        if (staffToUpdate != null) {
            try {
                System.out.println("Enter new first name:");
                String newFirstName = scanner.nextLine();
                System.out.println("Enter new last name:");
                String newLastName = scanner.nextLine();
                System.out.println("Enter new job role:");
                String newJobRole = scanner.nextLine();
                System.out.println("Enter work days (write with , ):");
                String newWorkDays = scanner.nextLine();
                System.out.println("Enter work hours:");
                String newWorkHours = scanner.nextLine();
                System.out.println("Enter password:");
                String newPassword = scanner.nextLine();

                // Find the ID of the staff to update
                String sql = "SELECT StaffId FROM staff WHERE Name = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                ResultSet result = statement.executeQuery();
                int staffId = 0;
                if (result.next()) {
                    staffId = result.getInt(1);
                }

                // Update the staff in the database
                sql = "UPDATE staff SET Name=?, Lastname=?, JobRole=?, WorkDays=?, WorkHours=?, Password=? WHERE StaffId=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, newFirstName);
                statement.setString(2, newLastName);
                statement.setString(3, newJobRole);
                statement.setString(4, newWorkDays);
                statement.setString(5, newWorkHours);
                statement.setString(6, newPassword);
                statement.setInt(7, staffId);
                statement.executeUpdate();

                // Update the staff in the ArrayList
                staffToUpdate.setName(newFirstName);
                staffToUpdate.setLastname(newLastName);
                staffToUpdate.setJobRole(newJobRole);
                staffToUpdate.setWorkDays(newWorkDays);
                staffToUpdate.setWorkHours(newWorkHours);
                staffToUpdate.setPassword(newPassword);

                System.out.println("Staff updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Staff not found.");
        }
    }

    // Method to delete an employee
    public void deleteStaff() {
        System.out.println("Enter first name of the staff to delete:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of the staff to delete:");
        String lastName = scanner.nextLine();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT StaffId FROM staff WHERE Name = ? AND Lastname = ?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int staffId = result.getInt("StaffId");
                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM staff WHERE StaffId = ?");
                deleteStatement.setInt(1, staffId);
                deleteStatement.executeUpdate();
                System.out.println("Staff deleted successfully.");
            } else {
                System.out.println("Staff not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to find a staff by first name and last name
    Staff findStaff(String firstName, String lastName) {
        for (Staff staff : this.staff) {
            if (staff.getName().equals(firstName) && staff.getLastname().equals(lastName)) {
                return staff;
            }
        }
        return null;
    }

    // Method to display all staffs' information
    public void displayStaff() {
        System.out.println("List of staffs:");
        try {
            String sql = "SELECT * FROM staff";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println("First Name: " + result.getString("Name"));
                System.out.println("Last Name: " + result.getString("Lastname"));
                System.out.println("Job Role: " + result.getString("JobRole"));
                System.out.println("Work Days: " + result.getString("WorkDays"));
                System.out.println("Work Hours: " + result.getString("WorkHours"));

                System.out.println("------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getStaff() {
        return staff;
    }

    // Method to schedule lesson for staff
    public void scheduleLessons() {
        System.out.println("Enter first name of the staff to add lesson:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of the staff to add lesson:");
        String lastName = scanner.nextLine();
        Staff staffToAddLessons = findStaff(firstName, lastName);

        if (staffToAddLessons.getJobRole().equals("Trainer")) {
            boolean flag = true;
            while (flag) {
                System.out.println("If you want to exit adding lessons type 'EXIT', if continue type anything ");
                String choice = scanner.nextLine();
                if (choice.toUpperCase().equals("EXIT")) {
                    flag = false;
                } else {
                    if (staff.contains(staffToAddLessons)) {
                        if (staffToAddLessons.getLessonTimeList().isEmpty()) {
                            System.out.println(staffToAddLessons.name + "'s schedule is empty.. You can choose from " + staffToAddLessons.getWorkDays() + " between " + staffToAddLessons.getWorkHours());
                        } else {
                            System.out.printf("%-15s", "Busy times listed below:");
                            System.out.println();
                            System.out.printf("%-15s", "-----------------------");
                            System.out.println();


                            for (Map.Entry<String, ArrayList<String>> entry : staffToAddLessons.getLessonTimeListWithDays().entrySet()) {
                                System.out.printf("%-15s", entry.getKey() + " -> ");
                                System.out.printf("%-15s", entry.getValue());
                                System.out.println();
                            }
                        }

                        System.out.println("Enter the week of the day you want to add lesson:");
                        String weekOfTheDay = scanner.nextLine();

                        System.out.println("Enter the start hour:");
                        int hour = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter the minute:");
                        int minute = scanner.nextInt();
                        scanner.nextLine();


                        int month = LocalDateTime.now().getMonthValue();
                        int day = LocalDateTime.now().getDayOfMonth();


                        LocalDateTime startTime = LocalDateTime.of(2023, month, day + 1, hour, minute);
                        LocalDateTime endTime = LocalDateTime.of(2023, month, day + 1, hour + 1, minute);

                        staffToAddLessons.schedulePrivateLesson(startTime, endTime, weekOfTheDay);

                    } else {
                        System.out.println("staff couldn not found");
                    }

                }

            }
        } else {
            System.out.println("Trainer could not be found.");
        }
    }

    // Method to start the gym management system
    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Gym Management System");
            System.out.println("1. Add Staff");
            System.out.println("2. Update Staff");
            System.out.println("3. Delete Staff");
            System.out.println("4. Display Staff");
            System.out.println("5. Member Operations");
            System.out.println("6. Equipment and Maintenance Operations");
            System.out.println("7. Add Private Lesson");
            System.out.println("8. List available classes and quotas");
            System.out.println("9. Display Class Attendance Report");
            System.out.println("10. Display Equipment Usage Report");
            System.out.println("11. Display Membership Status Report");
            System.out.println("12. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading an int

            switch (choice) {
                case 1:
                    addStaff();
                    break;
                case 2:
                    updateStaff();
                    break;
                case 3:
                    deleteStaff();
                    break;
                case 4:
                    displayStaff();
                    break;
                case 5:
                    startMemberOperations();
                    break;
                case 6:
                    Maintenance.displayEquipmentAndMaintenanceHistory();
                    break;
                case 7:
                    scheduleLessons();
                    break;
                case 8:
                    ClassSystem.listClasses();
                    break;
                case 9:
                    generateClassAttendanceReport();
                    break;
                case 10:
                    generateEquipmentUsageReport();
                    break;
                case 11:
                    generateMembershipStatusReport();
                    break;
                case 12:
                    exit = true;
                    System.out.println("Thank you for using Gym Management System. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Method to start member operations
    public void startMemberOperations() {
        boolean flag = false;
        while (!flag) {
            System.out.println("Choose an operation: ");
            System.out.println("A) Create Member");
            System.out.println("B) Update Member");
            System.out.println("C) Delete Member");
            System.out.println("D) Show Member Details");
            System.out.println("E) Exit Member Operations");
            String operation = scanner.nextLine();
            switch (operation.toUpperCase()) {
                case "A":
                    MemberRecords.createMember();
                    break;
                case "B":
                    MemberRecords.findToUpdateMember();
                    break;
                case "C":
                    MemberRecords.deleteMembership();
                    break;
                case "D":
                    if (!MemberRecords.getRecords().isEmpty()) {
                        for (MemberRecords memberRecord : MemberRecords.getRecords()) {
                            memberRecord.showMemberRecords();
                        }
                    } else {
                        System.out.println("Member list is empty.");
                    }
                    break;
                case "E":
                    flag = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    //Method to generate class attendance report
    public void generateClassAttendanceReport() {
        System.out.println("Class Attendance Report:");
        for (Staff staffMember : staff) {

            if (staffMember.getJobRole().equals("Trainer")) {
                System.out.println("Trainer: " + staffMember.getName() + " " + staffMember.getLastname());
                System.out.println("Class Attendance:");
                for (Map.Entry<String, ArrayList<String>> entry : staffMember.getLessonTimeListWithDays().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().size() + " attendees");
                }
                System.out.println("------------");
            }
        }
        for (MemberRecords memberRecord : MemberRecords.getRecords()) {

            System.out.println("Name: " + memberRecord.getPersonalDetails().getName());
            System.out.println("Last Name: " + memberRecord.getPersonalDetails().getLastname());
            System.out.println("------------");
        }
    }

    // Method to generate equipment usage report
    public static void generateEquipmentUsageReport() {
        System.out.println("Equipment Usage Report:");
        List<Equipment> equipmentList = Maintenance.displayEquipmentAndMaintenanceHistory(); // Assuming getEquipmentList() is a static method in the Maintenance class
        if (equipmentList != null) {
            for (Equipment equipment : equipmentList) {
                System.out.println("Name: " + equipment.getName());
                System.out.println("Usage Hours: " + equipment.getMaintenanceHistory());

                System.out.println("------------");

                List<Maintenance> maintenanceHistory = equipment.getMaintenanceHistory();
                for (Maintenance maintenance : maintenanceHistory) {
                    System.out.println("Description: " + maintenance.getDescription());
                    System.out.println("Date: " + maintenance.getDate());
                    System.out.println("Cost: $" + maintenance.getCost());
                    System.out.println("Technician: " + maintenance.getTechnician());
                    System.out.println("------------");
                }
            }
        } else {
            System.out.println("No equipment found.");
        }
    }

    // Method to generate membership status report
    public static void generateMembershipStatusReport() {
        System.out.println("Membership Status Report:");
        MemberRecords.getRecords().add((new MemberRecords(new PersonalDetails("ecem", "konca", "1234", "Izmir"), new Membership(), new PaymentHistory(0, 0, true))));

        for (MemberRecords memberRecord : MemberRecords.getRecords()) {

            System.out.println("Name: " + memberRecord.getPersonalDetails().getName());
            System.out.println("Last Name: " + memberRecord.getPersonalDetails().getLastname());
            System.out.println("Adress: " + memberRecord.getPersonalDetails().getAddress());
            System.out.println("Phone Number: " + memberRecord.getPersonalDetails().getPhoneNo());
            System.out.println("Membership Type :" + memberRecord.getMembership().getType());
            System.out.println("Membership Status: Active");
            System.out.println("------------");
        }
    }
}
