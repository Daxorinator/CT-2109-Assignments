import java.util.Scanner;
import static java.lang.System.exit;

public class Typeracer {

//    A character array to hold the letters of the alphabet.
    private static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//    A Scanner, used to get input from the user through the standard input (stdin)
    public static Scanner scanner = new Scanner(System.in);

//    play() is the method which handles a full play through of the game in all game-modes.
//    It accepts a boolean as the selector between game-modes as this is an easy type to do checks on later.
//    It prints the necessary game-related messages and triggers the timers used to time the game.
    public static void play(Boolean reverse) {
        System.out.print("START! Type: ");
//        Save the time since Unix Epoch (in milliseconds) before the game has started
        long startTime = System.currentTimeMillis();

//        Check the desired game-mode, if reverse is set, the game asks the alphabet in reverse
        if (!reverse) {
            // Simple for loop, looping through 0 to 25 i.e. 26 letters
            for (int i = 0; i < alphabet.length; i++) {
                alphabetLoop(i);
            }
        } else if (reverse) {
            // This loop is slightly more complicated as `i` can be zero and cannot be greater than 25
            // It loops backwards use the post-decrement operator
            for (int i = alphabet.length - 1; i >= 0; i--) {
                alphabetLoop(i);
            }
        }
        // Save the time since Unix Epoch (in milliseconds) after the game has ended
        long endTime = System.currentTimeMillis();
        // Calculate the difference between the times (the length of the game)
        long deltaTime = endTime - startTime;

        System.out.print("Congratulations! Your time was: ");
        System.out.println(deltaTime / 1000.0f + " seconds");
    }

//    AlphabetLoop takes in an integer representing the current letter of the alphabet the game is on
//    It prints out the letter to be tested, grabs a character from the user and will not continue until the correct character is input
//    This was spun off into a separate method to keep the above for loops tidy, and reduce the amount of repetitive code
    public static void alphabetLoop(int i) {
        System.out.println(alphabet[i]);
//        Get the first character from the newest line input by the user
        char input = scanner.next().charAt(0);
//        While the input is wrong, print an error and keep getting and checking the input from the user
        while (input != alphabet[i]) {
            System.out.println("No! Type " + alphabet[i]);
            input = scanner.next().charAt(0);
        }
        System.out.print("Good! Now do: ");
    }

    public static void main(String args[]) {


        System.out.println("Type the alphabet in order, hitting space between each letter.");

//        The game runs on an infinite loop, transitioning between game-states as it goes.
//        To exit the game, the user can type E, or opt to play again.
        while (true) {
            System.out.println("Forwards, Backwards or Exit? (f/b/e): ");
//            Get the first character of the newest line input by the user
            char input = scanner.next().charAt(0);

//            A switch can be used to filter down what key was pressed and take action
//            based on the key. This is because ASCII characters can be represented as ints.
            switch (input) {
//                If the user inputs an f, play the game normally and then return to the main screen
                case 'f':
                    play(false);
                    break;

//                If the user inputs a b, play the game in reverse and then return to the main screen
                case 'b':
                    play(true);
                    break;

//                If the user inputs an e, exit the game gracefully
                case 'e':
                    System.out.println("Goodbye!");
                    exit(0);

//                Otherwise an error is returned as all valid inputs have been checked.
                default:
                    System.out.println("Invalid. You must enter either \'f\' or \'b\' to play, or \'e\' to exit.");
            }
        }
    }
}
