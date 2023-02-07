import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfixToPostfix {

	List<Character> numbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	List<Character> operators = Arrays.asList('(', ')', '^', '*', '/', '+', '-');
	List<Character> allAllowed = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '^', '*', '/', '+', '-');

	public int getPrecedenceScore(char input) {
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
			for (int i = 0; i < input.length(); i++) {
				char character = input.charAt(i);
				if (!allAllowed.contains(character)) {
					input = JOptionPane.showInputDialog("Error: Invalid character, please enter a new infix expression");
					break;
				}
			}
			break;
		}
		return input;
	}

	public String InfixToPostfix(String input) {
		ArrayStack operators = new ArrayStack();
		String output = "";

		for (int i = 0; i < input.length(); i++) {

			char character = input.charAt(i);

			if (numbers.contains(character)) {
				output += character;
			} else if (operators.isEmpty()) {
				operators.push(character);
			} else if (getPrecedenceScore(character) > getPrecedenceScore((char) operators.top()) || (char) operators.top() == '(' || character == '(') {
				operators.push(character);
			} else {
				while (getPrecedenceScore((char) operators.top()) >= getPrecedenceScore(character) && (char) operators.top() != '(' && (char) operators.top() != ')') {
					output += (char) operators.pop();

					if (operators.isEmpty()) {
						break;
					}
				}
				operators.push(character);
			}

			if (character == ')') {
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

		while (operators.isEmpty() == false) {
			output += (char) operators.pop();
		}

		return output;
	};

	public float calculatePostfixExpression(String input) {
		ArrayStack operands = new ArrayStack();

		for (int i = 0; i < input.length(); i++) {
			char character = input.charAt(i);

			if (numbers.contains(character)) {
				operands.push((float) Character.getNumericValue(character));
			} else {
				float firstOperand = (float) operands.pop();
				float secondOperand = (float) operands.pop();

				float result = switch (character) {
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
