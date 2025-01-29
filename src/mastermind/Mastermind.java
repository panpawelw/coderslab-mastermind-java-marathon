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
        int[] attempt;
        long attemptCounter = 0;
        String mainLoopFinalMessage;

        do {
            attemptCounter++;
            System.out.printf("Zgadnij %d-cyfrowy kod składający się z cyfr od 1 do %d lub wprowadź " +
                    "'q' aby wyjść. Próba %d > ", CODE_LENGTH, MAX_DIGIT, attemptCounter);
            attempt = getAttempt(scanner, attemptCounter);

            mainLoopFinalMessage = attempt[0] != 0
                    ? "Wpisałeś kod: " + Arrays.toString(attempt) + "\n"
                    : "Do zobaczenia!";
            System.out.println(mainLoopFinalMessage);
        } while (attempt[0] != 0);

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
        int[] attempt = new int[CODE_LENGTH];
        String inputString;
        boolean again;
        do {
            again = false;
            try {
                inputString = getXCharString(scanner);
                if (inputString.equals("q")) {
                    attempt[0] = 0;
                } else {
                    convertAttempt(inputString, attempt);
                }
            } catch (IllegalArgumentException e) {
                System.out.printf("To %s od 1 do %d! Próba %d > "
                        , formatErrorMessage(), MAX_DIGIT, attemptCounter);
                again = true;
            }
        } while(again);
        return attempt;
    }

    private static String getXCharString(Scanner scanner) throws IllegalArgumentException{
        String xCharString = "";
        while (xCharString.length() != CODE_LENGTH) {
            xCharString = scanner.nextLine();

            if (xCharString.equals("q")) return "q";

            if (xCharString.length() != CODE_LENGTH) {
               throw new IllegalArgumentException();
            }
        }
        return xCharString;
    }

    @SuppressWarnings("DataFlowIssue")
    private static String formatErrorMessage() {
        return switch (Mastermind.CODE_LENGTH) {
            case 1 -> String.format("musi być %d cyfra", CODE_LENGTH);
            case 2,3,4 -> String.format("muszą być %d cyfry", CODE_LENGTH);
            default -> String.format("musi być %d cyfr", CODE_LENGTH);
        };
    }

    private static void convertAttempt(String attemptString, int[] attemptArray) throws NumberFormatException{
        for (int i = 0; i < attemptString.length(); i++) {
            attemptArray[i] = Character.getNumericValue(attemptString.charAt(i));
            if (attemptArray[i] < 1 || attemptArray[i] > MAX_DIGIT) {
                throw new NumberFormatException();
            }
        }
    }
}
