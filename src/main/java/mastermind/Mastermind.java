package mastermind;

import java.util.Random;
import java.util.Scanner;

/**
 * This is the mastermind game. User has to guess a secret code that consists of 4 digits ranging from 1 to 6.
 * These parameters can be adjusted by changing the constants CODE_LENGTH and MAX_DIGIT. In this particular
 * implementation user had unlimited number of guesses.
 *
 * @author panpawelw
 */
public class Mastermind {

    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {
        long attemptCounter = 1;
        String newLoopMessage = "Zgadnij %d-cyfrowy kod składający się z cyfr od 1 do %d " +
                "lub wprowadź 'q' aby wyjść. Próba %d > ";
        String resultMessage = "Cyfry we właściwym miejscu: %d %nCyfry w niewłaściwym miejscu: %d %n";
        String input;
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH], MAX_DIGIT);
        int[] attempt;

        System.out.printf(newLoopMessage, CODE_LENGTH, MAX_DIGIT, attemptCounter);
        input = scanner.nextLine();
        while (!input.equals("q")) {
            try {
                attempt = verifyInput(input, CODE_LENGTH, MAX_DIGIT);
                Result result = compareCodes(secretCode, attempt);
                if (result.inPlace == CODE_LENGTH) {
                    System.out.println("Zgadłeś kod! Gratulacje!!!");
                    break;
                } else {
                    System.out.printf(resultMessage, result.inPlace, result.outOfPlace);
                }
                attemptCounter++;
                System.out.printf(newLoopMessage, CODE_LENGTH, MAX_DIGIT, attemptCounter);
            } catch (IllegalArgumentException e) {
                System.out.printf("To %s od 1 do %d! Próba %d > ",
                        formatErrorMessage(CODE_LENGTH), MAX_DIGIT, attemptCounter);
            }
            input = scanner.nextLine();
        }

        System.out.println("Do zobaczenia!");
        scanner.close();
    }

    /**
     * Returns a secret code to be guessed later by the player.
     *
     * @param code      an empty int[] array for the code, it's size determines the length of the code.
     * @param maxDigit  the maximum the single digit of the code can be.
     * @return          the int array containing the secret code.
     */
    static int[] generateSecretCode(int[] code, int maxDigit) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(maxDigit) + 1;
        }
        return code;
    }

    /**
     * Converts the input string into an int array checking for input length and maximum digit.
     *
     * @param input         String containing user input.
     * @param codeLength    length of the code user is trying to guess.
     * @param maxDigit      the maximum the single digit of the code can be.
     * @return              int array containing user's guess.
     * @throws IllegalArgumentException when user's input doesn't match the length of the secret code or
     *                                  specifically NumberFormatException when user enters anything else
     *                                  than digits.
     */
    static int[] verifyInput(String input, int codeLength, int maxDigit)
            throws IllegalArgumentException {

        if (input.length() != codeLength) throw new IllegalArgumentException();

        int[] attempt = new int[codeLength];
        for (int i = 0; i < input.length(); i++) {
            attempt[i] = Character.getNumericValue(input.charAt(i));

            if (attempt[i] < 1 || attempt[i] > maxDigit) throw new NumberFormatException();

        }
        return attempt;
    }

    /**
     * Formats a part of the error message in accordance with Polish grammar rules.
     * @param codeLength    length of the secret code.
     * @return              formatted part of the error message.
     */
    static String formatErrorMessage(int codeLength) {
        return switch (codeLength) {
            case 1 -> String.format("musi być %d cyfra", codeLength);
            case 2, 3, 4 -> String.format("muszą być %d cyfry", codeLength);
            default -> String.format("musi być %d cyfr", codeLength);
        };
    }

    /**
     * Compares user's guess to the secret code and returns information on how many correct digits were
     * in the right position and how many digits were correct but in a wrong position.
     *
     * @param secretCode    the secret code user is trying to guess.
     * @param attempt       user's guess.
     * @return              Result object containing two int arrays.
     */
    static Result compareCodes(int[] secretCode, int[] attempt) {
        Result result = new Result(0, 0);
        int codeLength = secretCode.length;
        boolean[] countedInSecretCode = new boolean[codeLength];
        boolean[] countedInAttempt = new boolean[codeLength];
        for (int i = 0; i < codeLength; i++) {
            if (secretCode[i] == attempt[i]) {
                result.inPlace++;
                countedInSecretCode[i] = true;
                countedInAttempt[i] = true;
            }
        }
        for (int i = 0; i < codeLength; i++) {
            if (!countedInAttempt[i]) {
                for (int j = 0; j < codeLength; j++) {
                    if (!countedInSecretCode[j] && attempt[i] == secretCode[j]) {
                        result.outOfPlace++;
                        countedInAttempt[i] = true;
                        countedInSecretCode[j] = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Inner class used to return the result of compareCodes(). Contains two int arrays - how many digits
     * were guessed correctly and in the right position and how many digits were guessed correctly but in
     * the wrong position.
     */
    private static class Result {
        int inPlace;
        int outOfPlace;

        public Result(int inPlace, int outOfPlace) {
            this.inPlace = inPlace;
            this.outOfPlace = outOfPlace;
        }
    }

}
