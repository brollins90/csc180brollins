package lab2_rollins_blake;

public class ContactFactory {

    /**
     * Creates a contact if the inputs are all valid.
     * 
     * @param name The name for the contact
     * @param phone The phone number for the contact
     * @param email The email address for the contact
     * @param birthday The birthday for the contact
     * @return A new contact if inputs are valid, else null
     */
    public static Contact createContact(String name, String phone, String email, String birthday) {

        Contact newContact = null;
        boolean allValid = true;


        if (!Contact.validateName(name)) {
            // System.out.println("Name is invalid: " + name);
            allValid = false;
        }
        if (!Contact.validatePhone(phone)) {
            // System.out.println("Phone is invalid: " + phone);
            allValid = false;
        }
        if (!Contact.validateEmail(email)) {
            // System.out.println("Email is invalid: " + email);
            allValid = false;
        }
        if (!Contact.validateBirthday(birthday)) {
            // System.out.println("Birthday is invalid: " + birthday);
            allValid = false;
        }

        if (allValid) {
            newContact = new Contact(name, phone, email, birthday);
        } else {
            //System.out.println(name);
        }
        return newContact;
    }
}
