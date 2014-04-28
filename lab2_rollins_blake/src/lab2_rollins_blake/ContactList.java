package lab2_rollins_blake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * The ContactList is a container for the Contact objects. Can load and Save the Contacts to a text file.
 * 
 * @author Blake Rollins
 * 
 */
public class ContactList implements Iterable<Contact> {

    private List<Contact> contacts;
    private String filePath;

    /**
     * The different ways that we can sort our list
     * 
     * @author Blake Rollins
     * 
     */
    public enum SortType {
        Name, Phone, Email, Birthday
    }

    /**
     * Creates the default ContactList
     */
    public ContactList() {
        this("contacts.csv");
    }

    /**
     * Creates a ContactList and loads the list with Contacts from the input file
     * 
     * @param fileName The name of the input file.
     */
    public ContactList(String fileName) {
        this.contacts = new ArrayList<Contact>();
        this.filePath = /*
                         * "C:\\_\\src\\Neumont\\CSC180-SB\\lab1_rollins_blake\\bin\\" +
                         */fileName;
        loadFile();
    }

    /**
     * Adds a Contact to the Contact List
     * 
     * @param name The name of the contact
     * @param email The email of the contact
     * @param phone The phone number of the contact
     */
    public void add(String name, String phone, String email, String birthday) {
        this.add(name, phone, email, birthday, true);
    }

    /**
     * Adds a Contact to the Contact List
     * 
     * @param name The name of the contact
     * @param email The email of the contact
     * @param phone The phone number of the contact
     * @param saveNewFile Tells us whether or not to save the new contact to the file
     */
    public void add(String name, String phone, String email, String birthday, Boolean saveNewFile) {
        Contact contactToAdd = ContactFactory.createContact(name, phone, email, birthday);
        if (contactToAdd != null) {
            this.contacts.add(new Contact(name, phone, email, birthday));
            if (saveNewFile) {
                this.saveFile();
            }
        }
    }

    /**
     * Returns the Contact at the specified index.
     * 
     * @param index The index of the Contact to return.
     * @return The Contact at the index.
     */
    public Contact get(int index) {
        return new Contact(contacts.get(index));
    }

    /**
     * Loads contacts from the input file.
     */
    public void loadFile() {

        if (this.filePath != null) {
            BufferedReader br = null;
            try {
                String path = this.filePath;
                // System.out.println(path);
                br = new BufferedReader(new FileReader(path));

                String line = "";
                while ((line = br.readLine()) != null) {
                    // System.out.println(line);

                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        this.add(parts[0], parts[1], parts[2], parts[3], false);
                    } else {
                        // System.out.println("Bad line.  Skipping");
                    }
                }

                br.close();

            } catch (FileNotFoundException e) {
                System.out.println("Unable to open the file at: " + this.filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file is not set...");
        }
    }

    /**
     * Saves the Contacts to the output file.
     */
    private void saveFile() {

        if (this.filePath != null) {
            BufferedWriter bw = null;

            try {
                File f = new File(this.filePath);

                if (!f.exists()) {
                    f.createNewFile();
                }
                bw = new BufferedWriter(new FileWriter(f.getAbsolutePath()));

                for (Contact c : this.contacts) {
                    bw.write(c.getName() + "," + c.getEmail() + "," + c.getPhone() + "\n");
                }

                bw.close();

            } catch (IOException e) {

            }

        } else {
            System.out.println("The file is not set...");
        }
    }

    /**
     * Returns the number of Contacts in the list.
     * 
     * @return The number of contacts in the list.
     */
    public int size() {
        return contacts.size();
    }

    /**
     * Sorts the contact list based on the SortType that is inputed
     * 
     * @param sortByType The way we want to sort the list
     */
    public void sortList(SortType sortByType) {

        Comparator<Contact> c = new Contact.NameComparator();
        switch (sortByType) {
            case Birthday:
                c = new Contact.BirthdayComparator();
                break;
            case Email:
                c = new Contact.EmailComparator();
                break;
            case Phone:
                c = new Contact.PhoneNumberComparator();
                break;
            case Name:
            default:
                // already set to a NameComparator
                break;
        }

        Collections.sort(this.contacts, c);
    }



    /**
     * Returns a Contact Iterator.
     */
    @Override
    public Iterator<Contact> iterator() {
        Iterator<Contact> iContact = contacts.iterator();
        return iContact;
    }

}
