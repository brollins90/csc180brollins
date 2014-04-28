package lab2_rollins_blake;

import java.util.Scanner;

/**
 * A class that controls the Contact Manager and interfaces with the user
 * 
 * @author Blake Rollins
 * 
 */
public class ContactManager {

    private ContactList contacts;
    private Scanner scan;

    /**
     * Creates an empty ContactManager object
     */
    public ContactManager() {
        contacts = new ContactList();
        scan = new Scanner(System.in);

    }

    /**
     * Starts the program by asking the user for input
     */
    public void runContactManager() {

        System.out.println("Welcome to the Contact Manager!");
        boolean running = true;

        while (running) {
            displayOptions();
            int option = getIntFromQuestion("Your choice: ");
            switch (option) {
                case 1: // Print contacts sorted by Name
                    this.contacts.sortList(ContactList.SortType.Name);
                    displayContacts();
                    break;
                case 2: // Print contacts sorted by Phone number
                    this.contacts.sortList(ContactList.SortType.Phone);
                    displayContacts();
                    break;
                case 3: // Print contacts sorted by Email address
                    this.contacts.sortList(ContactList.SortType.Email);
                    displayContacts();
                    break;
                case 4: // Print contacts sorted by Birthday
                    this.contacts.sortList(ContactList.SortType.Birthday);
                    displayContacts();
                    break;
                case 5: // Find a contact by name
                    String searchName = getStringFromQuestion("Enter a name:");
                    displayContactFromName(searchName);
                    // int contactIndex = getIntFromQuestion("Enter the contact #: ");
                    // displayContactAt(contactIndex);

                    break;
                case 0: // Quit
                    running = false;
                    displayGoodbye();
                    break;
                default:
                    displayInvalidChoice();
                    break;
            }

        }

    }

    /**
     * Adds a new contact by asking the user for input
     */
    private void addContact() {
        System.out.println("");
        System.out.print("Name: ");
        String name = readLine();
        System.out.print("Phone: ");
        String phone = readLine();
        System.out.print("Email: ");
        String email = readLine();
        System.out.print("Birthday: ");
        String birthday = readLine();
        contacts.add(name, phone, email, birthday);
    }

    /**
     * Displays the contact at the specified index. (This is the index that is shown when the contacts are displayed, not the index that they are stored at by the ContactList)
     * 
     * @param index the index of the Contact
     */
    private void displayContactAt(int index) {
        // Since we want the output to begin at 1, we need to alter the user's input here
        index = index - 1;
        try {
            Contact c = contacts.get(index);
            System.out.println("");
            System.out.println("Name: " + c.getName());
            System.out.println("Phone: " + c.getPhone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Birthday: " + c.getBirthday());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That contact does not exist.");
        }
    }
    
    private void displayContactFromName(String searchName) {
        for (Contact c : this.contacts) {
            if (c.getName().toLowerCase().indexOf(searchName.toLowerCase()) != -1) {
                System.out.println(c);
            }
        }
    }

    /**
     * Prints out all the Contacts to the console
     */
    private void displayContacts() {
        System.out.println("");
        System.out.println("All Contacts:");
        int contactsSize = contacts.size();
        if (contactsSize == 0) {
            System.out.println("There are no contacts in the list.");
        } else {
            for (int i = 0; i < contactsSize; i++) {
                Contact c = contacts.get(i);

                // Since we want the output to begin at 1, we need to alter the output before we print
                System.out.println((i + 1) + " - " + c);
                // System.out.println((i + 1) + " - " + c.getBirthday() + " " + c.getEmail() + " " + c.getPhone() + " " + c.getName());
            }
        }
    }

    /**
     * Displays the goodbye output to the user
     */
    private void displayGoodbye() {
        System.out.println("");
        System.out.println("Goodbye.");
    }

    /**
     * Displays that the previous choice was not valid
     */
    private void displayInvalidChoice() {
        System.out.println("");
        System.out.println("Invalid option.  Try again.");
    }

    /**
     * Displays the options to the user
     */
    private void displayOptions() {
        System.out.println("");
        System.out.println("What do you want to do?");
        System.out.println("1) Print contacts sorted by Name.");
        System.out.println("2) Print contacts sorted by Phone number.");
        System.out.println("3) Print contacts sorted by Email address.");
        System.out.println("4) Print contacts sorted by Birthday.");
        System.out.println("5) Find a contact by name.");
        System.out.println("0) Quit");
    }

    /**
     * Asks the user a question and returns an int. Repeats the question until a valid int is entered.
     * 
     * @param question The question to ask.
     * @return The inputted int.
     */
    private int getIntFromQuestion(String question) {
        System.out.print(question);
        do {
            String temp = readLine();
            try {
                return Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                System.out.println("Input was not valid, try again:");
            }
        } while (true);

    }

    /**
     * Asks the user a question and returns an String.
     * 
     * @param question The question to ask.
     * @return The inputted String.
     */
    private String getStringFromQuestion(String question) {
        System.out.print(question);
        do {
            String temp = readLine();
            return temp;
        } while (true);

    }

    /**
     * Returns a String from the console
     * 
     * @return The String entered at the console.
     */
    private String readLine() {
        return scan.next();
    }
}
