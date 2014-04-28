package lab1_rollins_blake;

/**
 * Stores the information for a Contact
 * @author Blake Rollins
 *
 */
public class Contact {
	private String email;
	private String name;
	private String phone;

	/**
	 * Creates an empty Contact
	 */
	public Contact() {
		
	}
	
	/**
	 * Creates a Contact with the specified information
	 * @param name	The Contact's name
	 * @param email	The Contact's email
	 * @param phone	The Contact's phone number
	 */
	public Contact(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	/**
	 * Creates a Contact that is identical to the input Contact.
	 * @param c	The input Contact
	 */
	public Contact(Contact c) {
		this.name = c.name;
		this.email = c.email;
		this.phone = c.phone;
	}
	
	/**
	 * Returns the Contact's email
	 * @return	The Contact's email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Returns the Contact's name
	 * @return	The Contact's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the Contact's phone
	 * @return	The Contact's phone
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * Sets the Contact's email
	 * @param email	The new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the Contact's name
	 * @param email	The new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the Contact's phone
	 * @param email	The new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
