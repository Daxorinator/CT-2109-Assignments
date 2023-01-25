import java.util.Scanner;

import static java.lang.System.exit;

public class Typeracer {

    private static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static Scanner scanner = new Scanner(System.in);

    public static void play(Boolean reverse) {
        System.out.print("START! Type: ");
        long startTime = System.currentTimeMillis();

        if (!reverse) {
            for (int i = 0; i < alphabet.length; i++) {
                alphabetLoop(i);
            }
        } else if (reverse) {
            for (int i = alphabet.length - 1; i >= 0; i--) {
                alphabetLoop(i);
            }
        }
        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;

        System.out.print("Congratulations! Your time was: ");
        System.out.println(deltaTime / 1000.0f + " seconds");
    }

    public static void alphabetLoop(int i) {
        System.out.println(alphabet[i]);
        char input = scanner.next().charAt(0);
        while (input != alphabet[i]) {
            System.out.println("No! Type " + alphabet[i]);
            input = scanner.next().charAt(0);
        }
        System.out.print("Good! Now do: ");
    }

    public static void main(String args[]) {


        System.out.println("Type the alphabet in order, hitting space between each letter.");

        while (true) {
            System.out.println("Forwards, Backwards or Exit? (f/b/e): ");
            char input = scanner.next().charAt(0);

            switch (input) {
                case 'f':
                    play(false);
                    break;

                case 'b':
                    play(true);
                    break;

                case 'e':
                    System.out.println("Goodbye!");
                    exit(0);

                default:
                    System.out.println("Invalid. You must enter either \'f\' or \'b\' to play, or \'e\' to exit.");
            }
        }
    }
}
