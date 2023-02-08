import javax.swing.*;

/*
	main.java

	The entrypoint to the application.
	This class is responsible for instantiating the InfixToPostfix generator, and displaying the output
	on the screen once all operations are completed.
 */
public class Main {
	public static void main(String[] args) {

		// Instantiate a new InfixToPostfix generator
		InfixToPostfix test = new InfixToPostfix();

		// Get a validated input from the user to translate to Postfix notation
		String testInput = test.getInput();

		// Convert the input expression into Postfix notation
		String output = test.convertToPostfix(testInput);

		// Calculate the result of the Postfix expression
		float result = test.calculatePostfixExpression(output);

		// Print the Infix input expression, the Postfix output expression, and the resultant
		String text = "Infix Notation: " + testInput + "\n" + "Postfix Notation: " + output + "\n" + "Result: " + result;
		JOptionPane.showMessageDialog(null, text);
	}
}