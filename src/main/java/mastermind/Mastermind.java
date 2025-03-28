package mastermind;

import java.util.Random;
import java.util.Scanner;

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
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH]);
        int[] attempt;

        System.out.printf(newLoopMessage, CODE_LENGTH, MAX_DIGIT, attemptCounter);
        input = scanner.nextLine();
        while (!input.equals("q")) {
            try {
                attempt = verifyInput(input);
                Result result = compareCodes(attempt, secretCode);
                if (result.inPlace == CODE_LENGTH) {
                    System.out.println("Zgadłeś kod! Gratulacje!!!");
                    break;
                } else {
                    System.out.printf(resultMessage, result.inPlace, result.outOfPlace);
                }
                attemptCounter++;
                System.out.printf(newLoopMessage, CODE_LENGTH, MAX_DIGIT, attemptCounter);
            } catch (IllegalArgumentException e) {
                System.out.printf("To %s od 1 do %d! Próba %d > ", formatErrorMessage(), MAX_DIGIT, attemptCounter);
            }
            input = scanner.nextLine();
        }

        System.out.println("Do zobaczenia!");
        scanner.close();
    }

    static int[] generateSecretCode(int[] code) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT) + 1;
        }
        return code;
    }

    static int[] verifyInput(String input) throws IllegalArgumentException {

        if (input.length() != CODE_LENGTH) throw new IllegalArgumentException();

        int[] attempt = new int[CODE_LENGTH];
        for (int i = 0; i < input.length(); i++) {
            attempt[i] = Character.getNumericValue(input.charAt(i));

            if (attempt[i] < 1 || attempt[i] > MAX_DIGIT) throw new NumberFormatException();

        }
        return attempt;
    }

    @SuppressWarnings("DataFlowIssue")
    static String formatErrorMessage() {
        return switch (Mastermind.CODE_LENGTH) {
            case 1 -> String.format("musi być %d cyfra", CODE_LENGTH);
            case 2, 3, 4 -> String.format("muszą być %d cyfry", CODE_LENGTH);
            default -> String.format("musi być %d cyfr", CODE_LENGTH);
        };
    }

    static Result compareCodes(int[] secretCode, int[] attempt) {
        Result result = new Result(0, 0);
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (secretCode[i] != attempt[i]) {
                result.inPlace += 1;
            }
        }
        return result;
    }

    static class Result {
        int inPlace;
        int outOfPlace;

        public Result(int inPlace, int outOfPlace) {
            this.inPlace = inPlace;
            this.outOfPlace = outOfPlace;
        }
    }

}
