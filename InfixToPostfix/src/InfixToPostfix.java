import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/*
	InfixToPostfix.java

	This class defines the InfixToPostfix object, and the associated methods, including:
	- getPrecedenceScore() : Internal function which returns integer scores for operator precedence
	- getInput() : Public function which fetches and validates input from the user indefinitely until a valid input is found
	- convertToPostfix() : Public function which accepts a pre-validated String with an Infix expression and follows
	the algorithm to convert it to Postfix notation and return it as a String
	- calculatePostfixExpression() : Accepted a Postfix expression and calculates its resultant, and returns it as a float
 */

public class InfixToPostfix {

	// An immutable list of characters which are allowed (0-9 + operators)
	private static final List<Character> allAllowed = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '^', '*', '/', '+', '-');

	// This internal function takes a character input, if it's an operator
	// it will return an appropriate value for its precedence.
	// Otherwise, it will return 0
	private int getPrecedenceScore(char input) {
		return switch (input) {
				default -> 0;
				case '(', ')' -> 4;
				case '^' -> 3;
				case '*', '/' -> 2;
				case '+', '-' -> 1;
		};
	}

	// This function fetches and validates an input infix expression from the user
	public String getInput() {
		// Use the built-in Java UI library to display an input box and fetch the input
		String input = JOptionPane.showInputDialog("Enter an infix expression (3 to 20 characters)");

		// This loop validates the length of the input and, if invalid, requests a new one.
		// This check guarantees that the input is of the right length.
		while (input.length() < 3 || input.length() > 20) {
			input = JOptionPane.showInputDialog("Error: Invalid size, please enter a new infix expression");
		}

		// This loop validates that the content of the string is a legal maths expression
		// This check guarantees that the input can be computed and is a valid infix expression
		while (true) {
			// Instantiate a counter for the number of brackets
			int bracketCount = 0;
			// This flag is checked at the end of validation, by default the expression is valid
			boolean invalid = false;

			// A for loop to loop over the entire input string and run checks
			for (int i = 0; i < input.length(); i++) {
				// Grab the current character being checked
				char c = input.charAt(i);

				// If it is a bracket, adjust the count.
				// This count is used to ensure every bracket that is opened, is also closed
				switch (c) {
					case '(' -> bracketCount += 1;
					case ')' -> bracketCount -= 1;
				}

				// If the character is not in the allowed characters list, OR
				// The bracket count can only go negative if you do something like: )(
				// which is illegal and thus throws the invalid flag
				if (!allAllowed.contains(c) || bracketCount < 0) {
					invalid = true;
				}
			}

			// If the bracket count is not zero, then brackets are mismatched.
			// Flag.
			if (bracketCount != 0) {
				invalid = true;
			}

			// If the invalid flag is thrown, something is wrong, get a new input - loop again.
			if (invalid) {
				System.out.println(input);
				input = JOptionPane.showInputDialog("Error: Invalid expression, please enter a new infix expression");
			} else {
				// Otherwise hurray the expression is valid and we can break out of the infinite loop
				break;
			}
		}
		// and then we can return the input string.
		return input;
	}

	// This function converts the infix expression to postfix notation and returns it as a string
	public String convertToPostfix(String input) {
		// Initialise an ArrayStack to hold operators
		ArrayStack operators = new ArrayStack();
		// Initialise the output string for concatenation
		String output = "";

		// Loop over the infix input characters
		for (int i = 0; i < input.length(); i++) {

			// Fetch the current character for processing
			char c = input.charAt(i);

			// If it's a digit, put it on the output
			if (Character.isDigit(c)) {
				output += c;
			// There are no numbers now
			// If the stack is empty, push the operator to the stack
			} else if (operators.isEmpty()) {
				operators.push(c);
			// Otherwise check the precedence of the operator in question, if it's greater than
			// The one on the stack, or the top of the stack is an opening bracket, or the character
			// IS an opening bracket, push it to the stack
			} else if (getPrecedenceScore(c) > getPrecedenceScore((char) operators.top()) || (char) operators.top() == '(' || c == '(') {
				operators.push(c);
			} else {
				// Otherwise, loop until a lower or equal precedence operator is found on the stack
				// (As long as the operator on the stack is not an opening bracket or closing bracket)
				while (getPrecedenceScore((char) operators.top()) >= getPrecedenceScore(c) && (char) operators.top() != '(' && (char) operators.top() != ')') {
					// If the loop conditions succeed, pop the stack and push to the output
					output += (char) operators.pop();

					// If the stack is empty, stop
					if (operators.isEmpty()) {
						break;
					}
				}
				// Then finally push the operator in question onto the stack
				operators.push(c);
			}

			// If the character is a closing bracket
			if (c == ')') {
				// Loop until we get back to the corresponding opening bracket
				while (true) {
					char lastPopped = (char) operators.pop();
					if (lastPopped == '(') {
						break;
					}
					if (lastPopped != ')') {
						// and append the popped operator to the output
						output += lastPopped;
					}
				}
			}
		}

		// Lastly, empty the stack and append everything on it to the output
		while (!operators.isEmpty()) {
			output += (char) operators.pop();
		}

		// And return the output string.
		return output;
	}

	// This function calculates the resultant of the input Postfix expression
	public float calculatePostfixExpression(String input) {
		// Initialise an array stack to hold operands
		ArrayStack operands = new ArrayStack();

		// Loop over the input string to process it
		for (int i = 0; i < input.length(); i++) {
			// Grab the character to be processed
			char c = input.charAt(i);

			// If it is a digit - it is an operand, add it to the stack.
			// It is cast to a float due to a really *STUPID* 'feature' of Java where
			// it will *cast* an object back to the primitive Object instead of just holding it
			// as the type it has. Which is why the Int has to be converted to a Float first.
			// i.e Int -> Float is a legal cast
			// and Int -> Object -> Float is an illegal cast because for some reason,
			// it has forgotten that this is an int (AND IT STILL IS IN MEMORY), and so refuses to cast it to a float.
			if (Character.isDigit(c)) {
				operands.push((float) Character.getNumericValue(c));
			} else {
				// Otherwise, the character must be an operator and an operator must act on 2 operands
				float firstOperand = (float) operands.pop();
				float secondOperand = (float) operands.pop();

				// A switch statement matches the operator to the appropriate Java operator
				// and calculates the expression, and returns the result
				float result = switch (c) {
					default -> 0;

					case '^' -> (float) Math.pow(secondOperand, firstOperand);
					case '*' -> firstOperand * secondOperand;
					case '/' -> secondOperand / firstOperand;
					case '+' -> firstOperand + secondOperand;
					case '-' -> secondOperand - firstOperand;
				};

				// The result is then pushed to the operand stack
				operands.push(result);
			}
		}

		// Once this loop is complete the final result will be on top of the stack,
		// which can be popped and returned
		return (float) operands.pop();
	}
}
