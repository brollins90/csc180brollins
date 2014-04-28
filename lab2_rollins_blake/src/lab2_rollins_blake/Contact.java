package lab2_rollins_blake;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores the information for a Contact
 * 
 * @author Blake Rollins
 * 
 */
public class Contact implements Comparable<Contact> {

    private String name;
    private String phone;
    private String phoneClean;
    private String email;
    private String birthday;
    private String birthdayClean;

    /**
     * Creates an empty Contact
     */
    public Contact() {

    }

    /**
     * Creates a Contact with the specified information
     * 
     * @param name The Contact's name
     * @param email The Contact's email
     * @param phone The Contact's phone number
     */
    public Contact(String name, String phone, String email, String birthday) {
        setName(name);
        setPhone(phone);
        setEmail(email);
        setBirthday(birthday);
    }

    /**
     * Creates a Contact that is identical to the input Contact.
     * 
     * @param c The input Contact
     */
    public Contact(Contact c) {
        setName(c.name);
        setPhone(c.phone);
        setEmail(c.email);
        setBirthday(c.birthday);
    }

    /**
     * Returns the Contact's birthday
     * 
     * @return The Contact's birthday
     */
    public String getBirthday() {
        return this.birthday;
    }

    /**
     * Returns the Contact's email
     * 
     * @return The Contact's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the Contact's name
     * 
     * @return The Contact's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Contact's phone
     * 
     * @return The Contact's phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets the Contact's birthday
     * 
     * @param birthday The new birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
        this.birthdayClean = cleanBirthday(birthday);
    }

    /**
     * Sets the Contact's email
     * 
     * @param email The new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the Contact's name
     * 
     * @param email The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Contact's phone
     * 
     * @param email The new phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
        this.phoneClean = cleanPhone(phone);
    }

    /**
     * Returns a string representation of a birthday that is easier to sort by.
     * 
     * @param birthday The current birthday
     * @return The clean birthday
     */
    public String cleanBirthday(String birthday) {
        String clean = "";
        Pattern p = Pattern.compile("(0?[1-9]|1[012])[/\\.\\-](0?[1-9]|[12][0-9]|3[01])[/\\.\\-](19[0-9]{2}|1[01234]|0?[0-9])"); // (0?[1-9]|1[012])[/\.\-](0?[1-9]|[12][0-9]|3[01])[/\.\-](19[0-9]{2}|1[01234]|0?[0-9])
        Matcher m = p.matcher(birthday);
        boolean b = m.matches();

        if (b) {
            int count = m.groupCount();
            if (count == 3) {

                String MM = m.group(1);
                if (MM.length() == 1) {
                    MM = "0" + MM;
                }
                String DD = m.group(2);
                if (DD.length() == 1) {
                    DD = "0" + DD;
                }
                String YYYY = m.group(3);
                if (YYYY.length() == 2) {
                    YYYY = "20" + YYYY;
                }

                clean = YYYY + MM + DD;
            }
        }

        return clean;
    }

    /**
     * Returns a string representation of a phone number that is easier to sort by.
     * 
     * @param phone The current phone number
     * @return The clean phone number
     */
    public String cleanPhone(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }

    /**
     * Returns if the input birthday is valid
     * 
     * @param testString The birthday to test
     * @return If it is valid
     */
    public static boolean validateBirthday(String testString) {
        // A birth date is valid only if it matches the patterns:
        // MM/DD/YYYY
        // MM.DD.YYYY
        // MM-DD-YYYY
        // where MM represents a one or two digit month numbered 1 through 12
        // DD represents a one or two digit date numbered 1 through 31
        // YYYY represents a two or four digit year numbered 1900 through 2014 (Exception: All years
        // in the 2000s will always be two digits)
        Pattern p = Pattern.compile("(0?[1-9]|1[012])[/\\.\\-](0?[1-9]|[12][0-9]|3[01])[/\\.\\-](19[0-9]{2}|1[01234]|0?[0-9])"); // (0?[1-9]|1[012])[/\.\-](0?[1-9]|[12][0-9]|3[01])[/\.\-](19[0-9]{2}|1[01234]|0?[0-9])
        Matcher m = p.matcher(testString);
        boolean b = m.matches();

        return b;
    }

    /**
     * Returns if the input email address is valid
     * 
     * @param testString The email address to test
     * @return If the email address is valid
     */
    public static boolean validateEmail(String testString) {
        // An email address is valid if it matches the following pattern, where W represents any
        // number and combination of characters a-z, A-Z, underscore, or period, and L represents
        // any number and combination of characters a-z or A-Z
        // W@L.L
        Pattern p = Pattern.compile("([0-9A-z_\\.]+)@([0-9A-z.]+)");
//        Pattern p = Pattern.compile("([0-9A-z_\\.]*)@([0-9A-z]*).([0-9A-z]*)");
        Matcher m = p.matcher(testString);
        boolean b = m.matches();

        return b;
    }

    /**
     * Returns if the input name is valid
     * 
     * @param testString The name to test
     * @return if the name is valid
     */
    public static boolean validateName(String testString) {
        // A name is valid if it has a first and a last name, with an optional middle initial
        // followed
        // by a period.
        // All first, last, and middle initials should start with a capital letter.
        // All other names are invalid.
        Pattern p = Pattern.compile("(([A-Z]{1}['A-z]* )([A-Z]\\. )?([A-Z]{1}['A-z]*))");
        Matcher m = p.matcher(testString);
        boolean b = m.matches();

        return b;
    }

    /**
     * Returns if the phone number is valid
     * 
     * @param testString The phone number to test
     * @return if the phone number is valid
     */
    public static boolean validatePhone(String testString) {
        // A phone number is only valid if it matches one of the following three patterns
        // (###) ###-####
        // ###-###-####
        // ###.###.####
        Pattern p = Pattern.compile("(\\d{3}\\.\\d{3}\\.\\d{4}|\\d{3}-\\d{3}-\\d{4}|\\(\\d{3}\\) \\d{3}-\\d{4})");
        Matcher m = p.matcher(testString);
        boolean b = m.matches();

        return b;
    }

    /**
     * Compares the contact based on Name ignoring case
     * @param c The Contact to compare against
     */
    @Override
    public int compareTo(Contact c) {
        if (c == null) 
        {
            return 1; // This object is greater than the nothing that the other is.
        }
        return this.name.compareToIgnoreCase(c.name);
    }

    /**
     * Creates a String representation of the Contact
     */
    @Override
    public String toString() {
        return "Name: " + this.name + ", Phone: " + this.phone + ", Email: " + this.email + ", Birthday: " + this.birthday;
    }

    /**
     * NameComparator compares Contacts based on their name
     * 
     * @author Blake Rollins
     * 
     */
    protected static class NameComparator implements Comparator<Contact> {

        /**
         * Compares two Contacts based on name ignoring case
         */
        @Override
        public int compare(Contact c1, Contact c2) {
            if (c1 == null && c2 == null) {
                return 0;
            }
            else if (c1 == null && c2 != null) {
                return -1;
            } else if (c1 != null && c2 == null) {
                return 1;
            } else {
                return c1.name.compareToIgnoreCase(c2.name);
            }
        }

    }

    /**
     * PhoneComparator compares Contacts based on their phone number
     * 
     * @author Blake Rollins
     * 
     */
    protected static class PhoneNumberComparator implements Comparator<Contact> {

        /**
         * Compares two Contacts based on phone number ignoring case
         */
        @Override
        public int compare(Contact c1, Contact c2) {
            if (c1 == null && c2 == null) {
                return 0;
            }
            else if (c1 == null && c2 != null) {
                return -1;
            } else if (c1 != null && c2 == null) {
                return 1;
            } else {
                return c1.phoneClean.compareToIgnoreCase(c2.phoneClean);
            }
        }

    }

    /**
     * EmailComparator compares Contacts based on their email address
     * 
     * @author Blake Rollins
     * 
     */
    protected static class EmailComparator implements Comparator<Contact> {

        /**
         * Compares two Contacts based on email ignoring case
         */
        @Override
        public int compare(Contact c1, Contact c2) {
            if (c1 == null && c2 == null) {
                return 0;
            }
            else if (c1 == null && c2 != null) {
                return -1;
            } else if (c1 != null && c2 == null) {
                return 1;
            } else {
                return c1.email.compareToIgnoreCase(c2.email);
            }
        }

    }

    /**
     * BirthdayComparator compares Contacts based on their birthday
     * 
     * @author Blake Rollins
     * 
     */
    protected static class BirthdayComparator implements Comparator<Contact> {

        /**
         * Compares two Contacts based on birthday ignoring case
         */
        @Override
        public int compare(Contact c1, Contact c2) {
            if (c1 == null && c2 == null) {
                return 0;
            }
            else if (c1 == null && c2 != null) {
                return -1;
            } else if (c1 != null && c2 == null) {
                return 1;
            } else {
                return c1.birthdayClean.compareToIgnoreCase(c2.birthdayClean);
            }
        }

    }

}
