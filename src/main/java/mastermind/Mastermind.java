package mastermind;

import java.util.Random;
import java.util.Scanner;

/**
 * This is the mastermind game. User has to guess a secret code that consists of 4 digits ranging from 1 to 6.
 * These parameters can be adjusted by changing the constants CODE_LENGTH and MAX_DIGIT. The number of attempts can be
 * set by changing the ATTEMPTS_LIMIT constant. When it's set to 0 the number of attempts is unlimited.
 *
 * @author panpawelw
 */
public class Mastermind {

    public static final int ATTEMPTS_LIMIT = 0;
    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {
        long attemptCounter = 1;
        int attemptsLimit = ATTEMPTS_LIMIT;
        int codeLength = CODE_LENGTH;
        int maxDigit = MAX_DIGIT;

        String parametersMessage = "Obecne parametry to: ilość prób: %s, długość kodu: %d, maksymalna cyfra: %d.";
        String newLoopMessage = "Zgadnij %d-cyfrowy kod składający się z cyfr od 1 do %d " +
                "lub wprowadź 'q' aby wyjść. Próba %d > ";
        String resultMessage = "Cyfry we właściwym miejscu: %d %nCyfry w niewłaściwym miejscu: %d %n";
        String input;
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[codeLength], maxDigit);
        int[] attempt;

        System.out.printf(parametersMessage + " Wpisz 't' aby je zmienić: ",
                attemptsLimit == 0 ? "bez ograniczeń" : attemptsLimit, codeLength, maxDigit);
        char answer = scanner.next().charAt(0);
        if (answer == 't') {
            attemptsLimit = getInt(scanner, "Podaj limit prób (0 - bez ograniczeń): "
                    , 0, 2147483647);
            codeLength = getInt(scanner, "Podaj długość kodu (1-9): ", 1, 9);
            maxDigit = getInt(scanner, "Podaj maksymalną cyfrę (1-9): ", 1, 9);
            System.out.printf(parametersMessage + "%n", attemptsLimit == 0 ? "bez ograniczeń" : attemptsLimit,
                    codeLength, maxDigit);
        }

        System.out.printf("%n" + newLoopMessage, codeLength, maxDigit, attemptCounter);
        input = scanner.nextLine();
        while (!input.equals("q") && attemptsLimit - attemptCounter != 0) {
            try {
                attempt = verifyInput(input, codeLength, maxDigit);
                Result result = compareCodes(secretCode, attempt);
                if (result.inPlace == codeLength) {
                    System.out.println("Zgadłeś kod! Gratulacje!!!");
                    break;
                } else {
                    System.out.printf(resultMessage, result.inPlace, result.outOfPlace);
                }
                attemptCounter++;
                System.out.printf(newLoopMessage, codeLength, maxDigit, attemptCounter);
            } catch (IllegalArgumentException e) {
                System.out.printf("To %s od 1 do %d! Próba %d > ",
                        formatErrorMessage(codeLength), maxDigit, attemptCounter);
            }
            input = scanner.nextLine();
        }

        System.out.println("Do zobaczenia!");
        scanner.close();
    }

    /**
     * Returns a secret code to be guessed later by the player.
     *
     * @param code     an empty int[] array for the code, it's size determines the length of the code.
     * @param maxDigit the maximum the single digit of the code can be.
     * @return the int array containing the secret code.
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
     * @param input      String containing user input.
     * @param codeLength length of the code user is trying to guess.
     * @param maxDigit   the maximum the single digit of the code can be.
     * @return int array containing user's guess.
     * @throws IllegalArgumentException when user's input doesn't match the length of the secret code or specifically
     *                                  NumberFormatException when user enters anything else than digits.
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
     *
     * @param codeLength length of the secret code.
     * @return formatted part of the error message.
     */
    static String formatErrorMessage(int codeLength) {
        return switch (codeLength) {
            case 1 -> String.format("musi być %d cyfra", codeLength);
            case 2, 3, 4 -> String.format("muszą być %d cyfry", codeLength);
            default -> String.format("musi być %d cyfr", codeLength);
        };
    }

    /**
     * Displays a prompt and gets an integer within range between minium and maximum from console.
     * Input is repeated until the integer is within range.
     *
     * @param scanner   Scanner object.
     * @param message   The message to be displayed.
     * @param minimum   minimum.
     * @param maximum   maximum.
     * @return  the integer.
     */
    static int getInt(Scanner scanner, String message, int minimum, int maximum) {
        int result = -2147483648;
        System.out.print(message);
        while (result < minimum || result > maximum) {
            while (!scanner.hasNextInt()) scanner.next();
            result = scanner.nextInt();
            scanner.nextLine();
        }
       return result;
    }

    /**
     * Compares user's guess to the secret code and returns information on how many correct digits were in the right
     * position and how many digits were correct but in a wrong position.
     *
     * @param secretCode the secret code user is trying to guess.
     * @param attempt    user's guess.
     * @return Result record containing two integers.
     */
    static Result compareCodes(int[] secretCode, int[] attempt) {
        int codeLength = secretCode.length;
        int inPlace = 0;
        int outOfPlace = 0;
        boolean[] countedInSecretCode = new boolean[codeLength];
        boolean[] countedInAttempt = new boolean[codeLength];
        for (int i = 0; i < codeLength; i++) {
            if (secretCode[i] == attempt[i]) {
                inPlace++;
                countedInSecretCode[i] = true;
                countedInAttempt[i] = true;
            }
        }
        for (int i = 0; i < codeLength; i++) {
            if (!countedInAttempt[i]) {
                for (int j = 0; j < codeLength; j++) {
                    if (!countedInSecretCode[j] && attempt[i] == secretCode[j]) {
                        outOfPlace++;
                        countedInAttempt[i] = true;
                        countedInSecretCode[j] = true;
                    }
                }
            }
        }
        return new Result(inPlace, outOfPlace);
    }

    /**
     * A record used to return the result of compareCodes(). Contains two integers - how many digits were guessed
     * correctly and in the right position and how many digits were guessed correctly but in the wrong position.
     */
    private record Result(int inPlace, int outOfPlace) {}
}
