package exercise1;

import java.util.Scanner;

public class Calculator {

	private Scanner scan;

	/**
	 * @author Blake Rollins
	 */

	/**
	 * MathOps is an enumeration of all the operations that the calculator can preform
	 * @author Blake Rollins
	 *
	 */
	public enum MathOps {
		ADD {
			@Override
			public double operation(double d1, double d2) {
				return d1 + d2;
			}
		},
		SUBTRACT {
			@Override
			public double operation(double d1, double d2) {
				return d1 - d2;
			}
		},
		MULTIPLY {
			@Override
			public double operation(double d1, double d2) {
				return d1 * d2;
			}
		},
		DIVIDE {
			@Override
			public double operation(double d1, double d2) {
				if (d2 != 0) {
					return d1 / d2;					
				}
				return 0;
			}
		};
		public abstract double operation(double d1, double d2);
	}

	/**
	 * Runs the calculator
	 * @param args	Not needed
	 */
	public static void main(String[] args) {
		Calculator calc = new Calculator();

		// 1. Create an instance of a Scanner.
		calc.initScanner();

		// 2. Use the scanner to prompt the user for a double.
		// Use exception handling to catch incorrect input.
		// In the event of incorrect input, require the user
		// to type their number again until it is valid.
		double firstNumber = calc.getDoubleFromQuestion("Type in the first number:");

		// 3. Ask the user what mathematical operation to perform.
		// To do this, grab a list of the operations from the
		// MathOps enum and then display each of the operations
		// using a for-each loop. Then use your scanner to ask
		// the user for a string. Convert this string into a
		// variable of the enumerated type MathOps using the
		// valueOf method. Again, use exception handling to
		// catch incorrect input. If the user provides an
		// invalid string that cannot be converted into a MathOps
		// type, ask the user to type it again until it is valid.
		MathOps op = calc.getOpFromQuestion("What operation would you like to perform?");

		// 4. Use your scanner to prompt the user for a second
		// double. Repeat the same exception handling as you
		// did for the first number.
		double secondNumber = calc.getDoubleFromQuestion("Type in the second number:");

		// 5. Invoke the "operation" method using your MathOps
		// variable to perform the correct calculation. Display
		// the result to verify its correctness.
		double result = op.operation(firstNumber, secondNumber);
		System.out.println("The result is: " + result);

		// Note: For all exception handling, the exception you catch
		// must be specific. Catching "Exception" is not allowed.
		// There should be three try-catch blocks in total.
	}

	/**
	 * Asks the user a question and returns a double as a response
	 * @param question 	The question to ask the user
	 * @return	The double that the user entered
	 */
	private double getDoubleFromQuestion(String question) {
		System.out.println(question);
		boolean isValid = false;
		do {
			String tempLine = readLine();
			try {
				Double retVal = Double.parseDouble(tempLine);
				return retVal;
			} catch (NumberFormatException e) {
				isValid = false;
				System.out.println("The number was not valid.  Try again:");
			}
		} while (!isValid);

		return 0;
	}

	/**
	 * Asks the user a question and returns a MathOps as a response
	 * @param question	The question to ask the user
	 * @return	The operation that the user wants to perform
	 */
	private MathOps getOpFromQuestion(String question) {
		System.out.println(question);
		printEnum();
		boolean isValid = false;
		do {
			String tempLine = readLine();
			try {
				MathOps retVal = MathOps.valueOf(tempLine);
				return retVal;
			} catch (IllegalArgumentException e) {
				isValid = false;
				System.out.println("The operation was not valid.  Try again:");
			}
		} while (!isValid);

		return null;
	}

	/**
	 * Initializes the Scanner object
	 */
	private void initScanner() {
		scan = new Scanner(System.in);
	}

	/**
	 * Prints all the Enum options to the console
	 */
	private void printEnum() {
		for (MathOps a : MathOps.values()) {
			System.out.println(a);
		}
	}

	private String readLine() {
		return scan.next();
	}
}
