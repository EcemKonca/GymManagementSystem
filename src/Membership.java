import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Membership {
    private String type;
    private static List<Membership> membershipList = new ArrayList<>();
    static final String TYPE_ECO = "economic"; // monthly price 200.0
    static final String TYPE_PREM = "premium"; // monthly price 300.0
    private double price;
    private String dueDate;

    private int fitnessGoal;

    static final String DUE_1MON = "1 months";
    static final String DUE_3MON = "3 months";
    static final String DUE_6MON = "6 months";
    static final String DUE_12MON = "12 months";

    public Membership() { // DEFAULT MEMBERSHIP
        this.type = TYPE_ECO;
        this.price = 200.00;
        this.dueDate = DUE_6MON;
        this.fitnessGoal = 10;
    }

    public void setFitnessGoal(int fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getFitnessGoal() {
        return fitnessGoal;
    }

    @Override
    public String toString() {
        return "Membership: " + "type='" + type + '\'' +
                ", price=" + price +
                ", fitness goal=" + fitnessGoal +
                "kg, dueDate= " + dueDate + '\'';
    }

    // Method to create membership in terms of due date, fitness goal, and type
    public static void createMembership(Membership membership, String answer) {
        Scanner scanner = new Scanner(System.in);
        if (answer.equals("y")) {
            System.out.println("Default membership is selected");
        } else if (answer.equals("n")) {
            System.out.println("How long do you want options: 1/3/6/12 months \nenter 1, 3, 6 or 12");
            String choice1 = scanner.nextLine();
            switch (choice1) {
                case "1":
                    membership.setDueDate(Membership.DUE_1MON);
                    break;
                case "3":
                    membership.setDueDate(Membership.DUE_3MON);
                    break;
                case "6":
                    membership.setDueDate(Membership.DUE_6MON);
                    break;
                case "12":
                    membership.setDueDate(Membership.DUE_12MON);
                    break;
                default:
                    System.out.println("Invalid Answer");
                    System.out.println("Set as 6 Months"); // default value
                    break;
            }
            System.out.println("How much weight is your goal to lose");
            int weightGoal = scanner.nextInt();
            membership.setFitnessGoal(weightGoal);
            scanner.nextLine();

            System.out.println("Economic or premium ? (e / p)");
            String choice = scanner.nextLine();
            if (choice.equals("e")) {
                membership.setType(Membership.TYPE_ECO);
                membership.setPrice(200.0);
            } else if (choice.equals("p")) {
                membership.setType(Membership.TYPE_PREM);
                membership.setPrice(300.0);
            } else {
                System.out.println("Invalid Answer");
                System.out.println("Set as Economic"); // default value
            }
        }
    }

    //Method to update membership in terms of due date, fitness goal, and type
    public static void updateMembership(Membership membership) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How long do you want options: 1/3/6/12 months \nenter 1, 3, 6 or 12");
        String choice1 = scanner.nextLine();
        switch (choice1) {
            case "1":
                membership.setDueDate(Membership.DUE_1MON);
                break;
            case "3":
                membership.setDueDate(Membership.DUE_3MON);
                break;
            case "6":
                membership.setDueDate(Membership.DUE_6MON);
                break;
            case "12":
                membership.setDueDate(Membership.DUE_12MON);
                break;
            default:
                System.out.println("Invalid Answer");
                System.out.println("Set as 6 Months"); // default value
                break;
        }
        System.out.println("How much weight is your goal to lose: ");
        int updateWeightGoal = scanner.nextInt();
        membership.setFitnessGoal(updateWeightGoal);
        scanner.nextLine();

        System.out.println("Economic or premium ? (e / p)");
        String choice = scanner.nextLine();
        if (choice.equals("e")) {
            membership.setType(Membership.TYPE_ECO);
            membership.setPrice(200.0);
        } else if (choice.equals("p")) {
            membership.setType(Membership.TYPE_PREM);
            membership.setPrice(300.0);
        } else {
            System.out.println("Invalid Answer");
            System.out.println("Set as Economic"); // default value

        }
        System.out.println("Membership is updated.");
    }
}
