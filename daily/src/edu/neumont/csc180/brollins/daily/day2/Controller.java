package edu.neumont.csc180.brollins.daily.day2;

public class Controller {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.testAddInt();
	}
	
	public void testAddInt() {
		C c = new C();
		System.out.println(c.addIntToParentInt(1));
		System.out.println(c.addIntToParentInt(2));
	}
}
