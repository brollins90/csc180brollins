package exercise3;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * This program will deserialize an int and an object from the secret.data file
 * Then it will use the object to decrypt an encoded string in the object and print the String
 * @author Blake Rollins
 *
 */
public class Controller {
	
	/**
	 * Runs the program
	 * @param args	nothing
	 */
	public static void main(String[] args) {
		
		Integer i;
		SecretObject s;
		
		try {
			ObjectInputStream objStream = new ObjectInputStream(new FileInputStream(new File("bin/exercise3/secret.data")));
			i = (Integer) objStream.readObject();
			s = (SecretObject) objStream.readObject();
			objStream.close();
			
			System.out.println(i);
			System.out.println(s.decryptMessage(i));
			
		} catch (Exception e) {
			System.out.println("Failure:");
			e.printStackTrace();
		}		
	}
}
