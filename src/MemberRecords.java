import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberRecords {

    private static List<MemberRecords> records = new ArrayList<>();
    private PersonalDetails personalDetails;    // Composition -> to get personal details
    private Membership membership;  // Composition -> to get membership info
    private PaymentHistory paymentHistory;  // Composition -> to get payment info

    public Membership getMembership() {
        return membership;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }


    // Constructor
    public MemberRecords(PersonalDetails personalDetails, Membership membership, PaymentHistory paymentHistory) {
        this.personalDetails = personalDetails;
        this.membership = membership;
        this.paymentHistory = paymentHistory;
    }
    public MemberRecords() {

    }
    public static void setRecords(List<MemberRecords> memberRecordsList) {
        records = memberRecordsList;
    }

    public static List<MemberRecords> getRecords() {
        return records;
    }

    // Method to create member
    public static MemberRecords createMember() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Start with personal information");

            PersonalDetails details = PersonalDetails.createPersonalDetails(); // get personal information first

            Membership membership = new Membership();

            System.out.println("Do you want to go with default membership or choose?: (y for default / n for choose / d for delete )");

            if(scanner.hasNextLine()) {
                String answer = scanner.nextLine();
                if (answer.equals("y") || answer.equals("n")) {
                    Membership.createMembership(membership, answer); // decide membership type
                    System.out.println("Membership is created.");
                } else if (answer.equals("d")) {
                    deleteMembership();
                } else {
                    System.out.println("Invalid selection. Please try again.");
                    continue; // return to top
                }
            }

            PaymentHistory history = new PaymentHistory(membership);// create payment history
            MemberRecords record = new MemberRecords(details, membership, history);
            records.add(record);
            return record;
        }
    }
    // Method to find a member by first name and last name
    public static void findToUpdateMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name of the member to update:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of the member to update:");
        String lastName = scanner.nextLine();
        for (MemberRecords record : records) {
            if (record.personalDetails.getName().equals(firstName) && record.personalDetails.getLastname().equals(lastName)) {
                record.personalDetails.updateMemberPersonalDetails(firstName, lastName);
                Membership.updateMembership(record.membership);
            } else {
                System.out.println("Record could not found...");

            }
        }
    }

    // Method to delete membership
    public static void deleteMembership() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name of the member to delete:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of the member to delete:");
        String lastName = scanner.nextLine();

        if (records.isEmpty()) {
            System.out.println("Member list is empty");
        } else {
            MemberRecords recordToRemove = null;
            for (MemberRecords record : records) {
                if (record.personalDetails.getName().equals(firstName) && record.personalDetails.getLastname().equals(lastName)) {
                    recordToRemove = record;
                    break;
                }
            }
            if (recordToRemove == null) {
                System.out.println("Record not found");
            } else {
                records.remove(recordToRemove);
                System.out.println("Record is deleted");
            }
        }
    }

    //Method to show all the details about members'
    public void showMemberRecords() {
        System.out.println(personalDetails.toString());
        System.out.println();
        System.out.println(membership.toString());
        System.out.println();
        System.out.println(paymentHistory.toString());
    }
}
