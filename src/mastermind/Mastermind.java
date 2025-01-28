package mastermind;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH]);
        int[] userGuess;
        long attemptCounter = 0;
        String mainLoopFinalMessage;

        do {
            attemptCounter++;
            System.out.printf("Zgadnij %d-cyfrowy kod składający się z cyfr od 1 do %d lub wprowadź " +
                    "'q' aby wyjść. Próba %d > ", CODE_LENGTH, MAX_DIGIT, attemptCounter);
            userGuess = getAttempt(scanner, attemptCounter);

            mainLoopFinalMessage = userGuess[0] != 0
                    ? "Wpisałeś kod: " + Arrays.toString(userGuess) + "\n"
                    : "Do zobaczenia!";
            System.out.println(mainLoopFinalMessage);
        } while (userGuess[0] != 0);

        scanner.close();
    }

    private static int[] generateSecretCode(int[] code) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT);
        }
        return code;
    }

    private static int[] getAttempt(Scanner scanner, long attemptCounter) {
        String inputString = getXCharString(scanner, attemptCounter);
        int[]userGuess = new int[CODE_LENGTH];
        while (!attemptIsValid(inputString, userGuess)) {
            if (inputString.equals("q")) {
                userGuess[0] = 0;
                return userGuess;
            }
            System.out.printf("To muszą być cyfry od 1 do %d! Próba %d > ", MAX_DIGIT, attemptCounter);
            inputString = getXCharString(scanner, attemptCounter);
        }
        return userGuess;
    }

    private static String getXCharString(Scanner scanner, long attemptCounter) {
        String xCharString = "";
        while (xCharString.length() != CODE_LENGTH) {
            xCharString = scanner.nextLine();

            if (xCharString.equals("q")) return "q";

            if (xCharString.length() != CODE_LENGTH) {
                System.out.print(formatLengthErrorMessage(attemptCounter));
            }
        }
        return xCharString;
    }

    @SuppressWarnings("DataFlowIssue")
    private static String formatLengthErrorMessage(long attemptCounter) {
        return switch (Mastermind.CODE_LENGTH) {
            case 1 -> String.format("Kod musi mieć %d cyfrę! Próba %d > ", CODE_LENGTH, attemptCounter);
            case 2,3,4 -> String.format("Kod musi mieć %d cyfry! Próba %d > ", CODE_LENGTH, attemptCounter);
            default -> String.format("Kod musi mieć %d cyfr! Próba %d > ", CODE_LENGTH, attemptCounter);
        };
    }

    private static boolean attemptIsValid(String guess, int[] userGuess) {
        for (int i = 0; i < guess.length(); i++) {
            userGuess[i] = Character.getNumericValue(guess.charAt(i));
            if (userGuess[i] < 1 || userGuess[i] > MAX_DIGIT) {
                return false;
            }
        }
        return true;
    }
}
