import java.util.*;
import java.util.List;
import java.util.ArrayList;
//model
class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;
   
    // Constructor
    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
   
    // Getters and Setters
    public String getName() {
        return name;
    }
   
    public void setName(String name) {
        this.name = name;
    }
   
    public String getPhoneNumber() {
        return phoneNumber;
    }
   
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
   
    public String getEmailAddress() {
        return emailAddress;
    }
   
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
//view
class AddressBookView {
    public void displayContacts(List<Contact> contacts) {
        System.out.println("Address Book:");
       
        for (Contact contact : contacts) {
            System.out.println("Name: " + contact.getName());
            System.out.println("Phone: " + contact.getPhoneNumber());
            System.out.println("Email: " + contact.getEmailAddress());
        }
    }
   
    public Contact getContactInfoFromUser() {
        Scanner scanner = new Scanner(System.in);
       
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
       
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
       
        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();
       
        return new Contact(name, phoneNumber, emailAddress);
    }
   
    public void displayErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}
//controller
class AddressBookController {
    private List<Contact> contacts;
    private AddressBookView view;
   
    // Constructor
    public AddressBookController(AddressBookView view) {
        contacts = new ArrayList<>();
        this.view = view;
    }
   
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
   
    public void displayContacts() {
        view.displayContacts(contacts);
    }
   
    public void run() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
       
        while (running) {
            System.out.println("Address Book Application");
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
           
            int choice = scanner.nextInt();
           
            switch (choice) {
                case 1:
                    Contact newContact = view.getContactInfoFromUser();
                    addContact(newContact);
                    break;
                   
                case 2:
                    displayContacts();
                    break;
                   
                case 3:
                    running = false;
                    break;
                   
                default:
                    view.displayErrorMessage("Invalid choice. Please try again.");
            }
           
            System.out.println();
        }
    }
}
class Main {
    public static void main(String[] args) {
        AddressBookView view = new AddressBookView();
        AddressBookController controller = new AddressBookController(view);
        controller.run();
    }
}
