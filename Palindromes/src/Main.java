import java.text.NumberFormat;

public class Main {

	protected static int TEST_LIMIT = 1000000;

	public static void main(String[] args) {
		resetCounters();
		validationTest();
		System.out.println();
		resetCounters();
		timingTestDecimal();
		System.out.println();
		resetCounters();
		timingTestBinary();
		System.out.println();
		resetCounters();
		doublePalindromeTest();
	}

	public static void validationTest() {

		// This test is a sanity checker for all four functions to ensure they are giving the same outputs throughout testing.

		System.out.println("== PALINDROME CHECKER - First " + NumberFormat.getIntegerInstance().format(TEST_LIMIT) + " (Decimal + Binary) ==");
		int prc_dec, prc_bin, pec_dec, pec_bin, pqs_dec, pqs_bin, prr_dec, prr_bin;
		prc_dec = prc_bin = pec_dec = pec_bin = pqs_dec = pqs_bin = prr_dec = prr_bin = 0;
		for (int i = 0; i <= TEST_LIMIT; i++) {
			if (Palindrome.reverseComparison(Integer.toString(i))) prc_dec++;
			if (Palindrome.reverseComparison(Palindrome.decimalToBinary(Integer.toString(i)))) prc_bin++;
			if (Palindrome.elementComparison(Integer.toString(i))) pec_dec++;
			if (Palindrome.elementComparison(Palindrome.decimalToBinary(Integer.toString(i)))) pec_bin++;
			if (Palindrome.queueStackComparison(Integer.toString(i))) pqs_dec++;
			if (Palindrome.queueStackComparison(Palindrome.decimalToBinary(Integer.toString(i)))) pqs_bin++;
			if (Palindrome.recursiveReverseComparison(Integer.toString(i))) prr_dec++;
			if (Palindrome.recursiveReverseComparison(Palindrome.decimalToBinary(Integer.toString(i)))) prr_bin++;
		}
		System.out.print(
				"-- Decimal Outputs --\n" +
				"Reverse Comparison: " + prc_dec + "\n" +
				"Element Comparison: " + pec_dec + "\n" +
				"QueueStack Comparison: " + pqs_dec + "\n" +
				"Recursive Reverse Comparison: " + prr_dec + "\n"
		);

		System.out.print(
				"-- Binary Outputs --\n" +
				"Reverse Comparison: " + prc_bin + "\n" +
				"Element Comparison: " + pec_bin + "\n" +
				"QueueStack Comparison: " + pqs_bin + "\n" +
				"Recursive Reverse Comparison: " + prr_bin + "\n"
		);
	}

	public static void timingTestDecimal() {
		System.out.println("== PALINDROME TIMING TEST - First " + NumberFormat.getIntegerInstance().format(TEST_LIMIT) + " (Decimal) ==");

		// START: Method One - Palindrome Reverse Comparison (First Million)
		long start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.reverseComparison(Integer.toString(i));
		}
		long end = System.currentTimeMillis();
		long delta = end - start;
		System.out.println("Method 1 - Reverse Comparison: " + delta + "ms" + " and " + Palindrome.reverse_comparison_operations + " operations");
		// END: Method One - Palindrome Reverse Comparison (First Million)

		// START: Method Two - Palindrome Element Comparison (First Million)
		start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.elementComparison(Integer.toString(i));
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println("Method 2 - Element Comparison: " + delta + "ms" + " and " + Palindrome.element_comparison_operations + " operations");
		// END: Method Two - Palindrome Element Comparison (First Million)

		// START: Method Three - Palindrome Queue-Stack Comparison (First Million)
		start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.queueStackComparison(Integer.toString(i));
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println("Method 3 - Queue-Stack Comparison: " + delta + "ms" + " and " + Palindrome.queue_stack_operations + " operations");
		// END: Method Three - Palindrome Queue-Stack Comparison (First Million)

		// START: Method Four - Palindrome Recursive Reversal Comparison (First Million)
		start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.recursiveReverseComparison(Integer.toString(i));
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println("Method 4 - Recursive Reversal Comparison: " + delta + "ms" + " and " + Palindrome.recursive_reverse_operations + " operations");
		// END: Method Four - Palindrome Recursive Reversal Comparison (First Million)
	}

	public static void timingTestBinary() {
		System.out.println("== PALINDROME TIMING TEST - First " + NumberFormat.getIntegerInstance().format(TEST_LIMIT) + " (Binary) ==");

		// START: Method One - Palindrome Reverse Comparison (First Million)
		long start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.reverseComparison(Palindrome.decimalToBinary(Integer.toString(i)));
		}
		long end = System.currentTimeMillis();
		long delta = end - start;
		System.out.println("Method 1 - Reverse Comparison: " + delta + "ms");
		// END: Method One - Palindrome Reverse Comparison (First Million)

		// START: Method Two - Palindrome Element Comparison (First Million)
		start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.elementComparison(Palindrome.decimalToBinary(Integer.toString(i)));
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println("Method 2 - Element Comparison: " + delta + "ms");
		// END: Method Two - Palindrome Element Comparison (First Million)

		// START: Method Three - Palindrome Queue-Stack Comparison (First Million)
		start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.queueStackComparison(Palindrome.decimalToBinary(Integer.toString(i)));
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println("Method 3 - Queue-Stack Comparison: " + delta + "ms");
		// END: Method Three - Palindrome Queue-Stack Comparison (First Million)

		// START: Method Four - Palindrome Recursive Reversal Comparison (First Million)
		start = System.currentTimeMillis();
		for (int i = 0; i <= TEST_LIMIT; i++) {
			Palindrome.recursiveReverseComparison(Palindrome.decimalToBinary(Integer.toString(i)));
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println("Method 4 - Recursive Reversal Comparison: " + delta + "ms");
		// END: Method Four - Palindrome Recursive Reversal Comparison (First Million)
	}

	public static void doublePalindromeTest() {
		System.out.println("== DOUBLE PALINDROME TEST - First " + NumberFormat.getIntegerInstance().format(TEST_LIMIT) + " (Decimal) ==");

		int count = 0;
		for (int i = 0; i <= TEST_LIMIT; i++) {
			String decimal_input = Integer.toString(i);
			String binary_input = Palindrome.decimalToBinary(decimal_input);
			boolean decimal = Palindrome.elementComparison(decimal_input);
			boolean binary = Palindrome.elementComparison(binary_input);

			if (decimal && binary) {
				count++;
				System.out.println(decimal_input + " " + binary_input);
			}
		}
		System.out.println("Double Palindromes found: " + count);
	}

	public static void resetCounters() {
		Palindrome.reverse_comparison_operations = 0;
		Palindrome.element_comparison_operations = 0;
		Palindrome.queue_stack_operations = 0;
		Palindrome.recursive_reverse_operations = 0;
	}
}
