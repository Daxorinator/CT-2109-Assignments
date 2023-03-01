public class Palindrome {

	public static int reverse_comparison_operations = 0;
	public static int element_comparison_operations = 0;
	public static int queue_stack_operations = 0;
	public static int recursive_reverse_operations = 0;

	// Method One
	// This method checks if an input string is a palindrome.
	// A loop from the length to zero is used to grab each character in the string
	// in reverse, and append it to a string "reversed". This string is
	// compared with the input string to check for a valid palindrome.
	public static boolean reverseComparison(String input) {
		String reversed = ""; // One operation
		reverse_comparison_operations += 2;

		for (int i = input.length() - 1; i >= 0; i--) { // 1 + 3 operations
			reversed += input.charAt(i); // 2 operations
			reverse_comparison_operations += 5;
		}

		reverse_comparison_operations += 1;
		return input.equals(reversed);
	}

	// Method Two
	// This method checks if an input string is a palindrome.
	// A loop over the length of the input is used to index each character.
	// The inverse of the index with respect to the length, is used to index
	// from the opposite side of the string. If at any point the opposite sides
	// of the input string do not match, the method returns false.
	public static boolean elementComparison(String input) {
		int j = input.length() - 1; // 2 operations
		element_comparison_operations += 2;

		// 1 + 2 operations
		element_comparison_operations += 1;
		for (int i = 0; i < input.length(); i++) {
			element_comparison_operations += 2;
			// 1 operation + 3 + 3
			if (input.charAt(i) != input.charAt(j)) {
				element_comparison_operations += 6;
				return false;
			}
			j--; // 1 operation
			element_comparison_operations += 1;
		}

		return true;
	}

	// Method Three
	// This method checks if an input string is a palindrome.
	// A queue and a stack are defined, and as each character from the input
	// is read, it is added to both the stack and the queue. As a Stack is a
	// Last-in-First-out (LIFO) structure, and a Queue is a First-in-First-out (FIFO)
	// structure, the outputs of the Stack and Queue after all characters from the input
	// are added, will be the reverse of each other. Popping/dequeuing each element
	// and comparing them is adequate to confirm if the input is a palindrome.
	public static boolean queueStackComparison(String input) {
		Queue queue = new ArrayQueue(); // 3 operations
		Stack stack = new ArrayStack(); // 3 operations
		queue_stack_operations += 6;

		// Same as a for loop + 1 assignment, 3 + 1 operations
		queue_stack_operations += 1;
		for (char c : input.toCharArray()) {
			queue.enqueue(c); // 2 operations
			stack.push(c); // 2 operations
			queue_stack_operations += 7;
		}

		// 3 operations
		while (!queue.isEmpty() && !stack.isEmpty()) {
			queue_stack_operations += 3;
			// 8 + 1 + 4 operations
			if ((char) queue.dequeue() != (char) stack.pop()) {
				queue_stack_operations += 13;
				return false;
			}
		}

		return true;
	}

	// Method Four
	// This method checks if an input string is a palindrome.
	// A recursive helper method reverse() is used to acquire the reverse of
	// the input string. Then the strings are compared us .equals()
	public static boolean recursiveReverseComparison(String input) {
		// 1 operation
		String reversed = reverse(input);
		recursive_reverse_operations += 2;
		// also 1 operation
		return input.equals(reversed);
	}

	// Reverse
	// This method returns the reverse of the input string.
	// Recursion is used to reverse the input string.
	// The first character of the input is isolated from the
	// rest, and the remaining string is sent to the recursive call to reverse()
	// The first character is then appended at the end of the output and returned.
	// If the input is blank, then the original call
	public static String reverse(String input) {
		// If the input is empty then there's nothing left to reverse
		// so the recursion can unwind and build the final reversed string
		recursive_reverse_operations += 3;
		if (input.isEmpty()) { // 3 operations
			return input;
		} else {
			// Recursively reverse the remainder of the string (1 operation + at least 9 for substring lol)
			String reversed = reverse(input.substring(1));
			recursive_reverse_operations += 10;
			// Stick the first character onto the end to reverse the first character
			recursive_reverse_operations += 1;
			return reversed + input.charAt(0);
		}
	}

	// decimalToBinary
	// This method takes a numerical input string and
	// returns the binary representation of the input
	// as a string. First the input is parsed to an integer
	// and then a StringBuilder is instantiated to store the output
	// The conversion stays running until no integer is left.
	// The modulo 2 of the decimal is taken and inserted at the start
	// of the output string, otherwise the output would be in reverse.
	// Then the decimal number is divided by 2.
	public static String decimalToBinary(String input) {
		int decimal = Integer.parseInt(input);
		StringBuilder output = new StringBuilder();
		while (decimal > 0) {
			output.insert(0, decimal % 2);
			decimal /= 2;
		}
		return output.toString();
	}
}