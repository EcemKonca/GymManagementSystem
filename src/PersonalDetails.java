import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalDetails {   // Inheritance -> Base class for both member's and staff's information
    String name;
    private static List<PersonalDetails> personalDetailsList = new ArrayList<>();
    private String lastname;
    private String phoneNo;
    private String address;

    public static void setPersonalDetailsList(List<PersonalDetails> personalDetailsList) {
        PersonalDetails.personalDetailsList = personalDetailsList;
    }

    // Constructor
    public PersonalDetails(String name, String lastname, String phoneNo, String address) {
        this.name = name;
        this.lastname = lastname;
        this.phoneNo = phoneNo;
        this.address = address;
    }


    // Method to create member personal details in terms of name, lastname, phone number, and address
    public static PersonalDetails createPersonalDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your lastname: ");
        String lastname = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNo = scanner.nextLine();
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();
        PersonalDetails newDetail = new PersonalDetails(name, lastname, phoneNo, address);
        personalDetailsList.add(newDetail);
        return newDetail;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
        }
        else {
            this.name = name;
        }
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname.isEmpty()) {
            System.out.println("Lastname cannot be empty!");
        }
        else {
            this.lastname = lastname;
        }
    }


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        if (phoneNo.isEmpty()) {
            System.out.println("Phone number cannot be empty!");
        }
        else {
            this.phoneNo = phoneNo;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.isEmpty()) {
            System.out.println("Address cannot be empty!");
        }
        else {
            this.address = address;
        }
    }

    // Method to find personal details
    static PersonalDetails findDetail(String name, String lastname) {
        for (PersonalDetails detail : personalDetailsList) {
            if (detail.getName().equals(name) && detail.getLastname().equals(lastname)) {
                return detail;
            }
        }
        return null;
    }

    // Method to update member personal details in terms of name and last name
    public static void updateMemberPersonalDetails(String firstName, String lastName) {
        Scanner scanner = new Scanner(System.in);

        PersonalDetails detailToUpdate = findDetail(firstName, lastName);
        if (detailToUpdate != null) {
            System.out.println("Enter new first name:");
            String newFirstName = scanner.nextLine();
            System.out.println("Enter new last name:");
            String newLastName = scanner.nextLine();

            detailToUpdate.setName(newFirstName);
            detailToUpdate.setLastname(newLastName);

            System.out.println("Personal Details updated successfully.");
        } else {
            System.out.println("Member could not found.");
        }
    }
public PersonalDetails(){}

    @Override
    public String toString() {
        return "PersonalDetails: " + "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' ;
    }
}
