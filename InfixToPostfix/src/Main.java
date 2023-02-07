import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		InfixToPostfix test = new InfixToPostfix();

		String testInput = test.getInput();

		String output = test.InfixToPostfix(testInput);

		float result = test.calculatePostfixExpression(output);

		String text = "Infix Notation: " + testInput + "\n" + "Postfix Notation: " + output + "\n" + "Result: " + result;

		JOptionPane.showMessageDialog(null, text);
	}
}