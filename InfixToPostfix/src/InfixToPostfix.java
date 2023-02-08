import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class InfixToPostfix {

	private static final List<Character> allAllowed = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '^', '*', '/', '+', '-');

	private int getPrecedenceScore(char input) {
		return switch (input) {
				default -> 0;
				case '(', ')' -> 4;
				case '^' -> 3;
				case '*', '/' -> 2;
				case '+', '-' -> 1;
		};
	}

	public String getInput() {
		String input = JOptionPane.showInputDialog("Enter an infix expression (3 to 20 characters)");

		while (input.length() < 3 || input.length() > 20) {
			input = JOptionPane.showInputDialog("Error: Invalid size, please enter a new infix expression");
		}

		while (true) {
			int bracketCount = 0;
			boolean invalid = false;

			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);

				switch (c) {
					case '(' -> bracketCount += 1;
					case ')' -> bracketCount -= 1;
				}

				if (!allAllowed.contains(c) || bracketCount < 0) {
					invalid = true;
				}
			}

			if (bracketCount != 0) {
				invalid = true;
			}

			if (invalid) {
				input = JOptionPane.showInputDialog("Error: Invalid expression, please enter a new infix expression");
			} else {
				break;
			}
		}

		return input;
	}

	public String convertToPostfix(String input) {
		ArrayStack operators = new ArrayStack();
		String output = "";

		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);

			if (Character.isDigit(c)) {
				output += c;
			} else if (operators.isEmpty()) {
				operators.push(c);
			} else if (getPrecedenceScore(c) > getPrecedenceScore((char) operators.top()) || (char) operators.top() == '(' || c == '(') {
				operators.push(c);
			} else {
				while (getPrecedenceScore((char) operators.top()) >= getPrecedenceScore(c) && (char) operators.top() != '(' && (char) operators.top() != ')') {
					output += (char) operators.pop();

					if (operators.isEmpty()) {
						break;
					}
				}
				operators.push(c);
			}

			if (c == ')') {
				while (true) {
					char lastPopped = (char) operators.pop();
					if (lastPopped == '(') {
						break;
					}
					if (lastPopped != ')') {
						output += lastPopped;
					}
				}
			}
		}

		while (!operators.isEmpty()) {
			output += (char) operators.pop();
		}

		return output;
	}

	public float calculatePostfixExpression(String input) {
		ArrayStack operands = new ArrayStack();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isDigit(c)) {
				operands.push((float) Character.getNumericValue(c));
			} else {
				float firstOperand = (float) operands.pop();
				float secondOperand = (float) operands.pop();

				float result = switch (c) {
					default -> 0;

					case '^' -> (float) Math.pow(secondOperand, firstOperand);
					case '*' -> firstOperand * secondOperand;
					case '/' -> secondOperand / firstOperand;
					case '+' -> firstOperand + secondOperand;
					case '-' -> secondOperand - firstOperand;
				};

				operands.push(result);
			}
		}

		return (float) operands.pop();
	}
}
