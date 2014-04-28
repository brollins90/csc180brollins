package lab1_rollins_blake;

import java.util.Scanner;

/**
 * A class that controls the Contact Manager and interfaces with the user
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
			case 1: // See all contacts
				displayContacts();
				break;
			case 2: // See a specific contact
				int contactIndex = getIntFromQuestion("Enter the contact #: ");
				displayContactAt(contactIndex);
				break;
			case 3: // Enter a new contact
				addContact();
				break;
			case 4: // Edit a contact
				break;
			case 5: // Remove a contact
				break;
			case 0: // Quit
			default:
				running = false;
				displayGoodbye();
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
		System.out.print("Email: ");
		String email = readLine();
		System.out.print("Phone: ");
		String phone = readLine();
		contacts.add(name, email, phone);
	}
	
	/**
	 * Displays the contact at the specified index.
	 * (This is the index that is shown when the contacts are displayed, not the index that they
	 *  are stored at by the ContactList)
	 * @param index	the index of the Contact
	 */
	private void displayContactAt(int index) {
		// Since we want the output to begin at 1, we need to alter the user's input here
		index = index - 1;
		try {
			Contact c = contacts.get(index);
			System.out.println("");
			System.out.println("Name: " + c.getName());
			System.out.println("Email: " + c.getEmail());
			System.out.println("Phone: " + c.getPhone());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("That contact does not exist.");
		}
	}

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
				System.out.println((i + 1) + " - " + c.getName());
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
	 * Displays the options to the user
	 */
	private void displayOptions() {
		System.out.println("");
		System.out.println("Do you want to\n1) See all contacts.\n2) See a specific contact.\n3) Enter a new contact.\n0) Quit"); // 4) Edit a contact.\n5) Remove a contact.\n
	}

	/**
	 * Asks the user a question and returns an int.
	 * Repeats the question until a valid int is entered.
	 * @param question	The question to ask.
	 * @return	The inputted int.
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
	 * Returns a String from the console
	 * @return	The String entered at the console.
	 */
	private String readLine() {
		return scan.next();
	}
}
