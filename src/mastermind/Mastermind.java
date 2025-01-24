package mastermind;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH]);
        long attemptCounter = 0;
        int attemptValue;
        String mainLoopFinalMessage;
        do {
            attemptCounter++;
            System.out.printf("Zgadnij %d-cyfrowy kod składający się z cyfr od 1 do %d lub wprowadź " +
                    "'q' aby wyjść. Próba %d > ", CODE_LENGTH, MAX_DIGIT, attemptCounter);
            attemptValue = getAttempt(scanner, attemptCounter);

            mainLoopFinalMessage = attemptValue != 0
                    ? "Wpisałeś kod: " + attemptValue
                    : "Do zobaczenia!";
            System.out.println(mainLoopFinalMessage);
        } while (attemptValue != 0);

        scanner.close();
    }

    private static int[] generateSecretCode(int[] code) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT);
        }
        return code;
    }

    private static int getAttempt(Scanner scanner, long attemptCounter) {
        String inputString = getXCharString(scanner, attemptCounter);
        int inputValue = 0;
        while (!attemptIsValid(inputString)) {

            if (inputString.equals("q")) return 0;

            System.out.printf("To muszą być cyfry od 1 do %d! Próba %d > ", MAX_DIGIT, attemptCounter);
            inputString = getXCharString(scanner, attemptCounter);
        }
        try {
            inputValue = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            System.out.printf("To muszą być cyfry od 1 do %d! Próba %d > ", MAX_DIGIT, attemptCounter);
        }
        return inputValue;
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

    private static boolean attemptIsValid(String guess) {
        for (char c : guess.toCharArray()) {
            if (c < '1' || c > ((char) MAX_DIGIT + '0')) {
                return false;
            }
        }
        return true;
    }
}
