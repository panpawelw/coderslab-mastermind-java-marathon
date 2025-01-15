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

        while(true) {
            attemptCounter++;
            System.out.print("Zgadnij " + CODE_LENGTH +"-cyfrowy kod składający się z cyfr od 1 do " + MAX_DIGIT
                    + " lub wprowadź 'q' aby wyjść. Próba " + attemptCounter + " > ");
            int attempt = getUsersGuess(scanner, attemptCounter);

            if (attempt == 0) break;

            System.out.println("Podałeś kod: " + attempt);
        }

        scanner.close();
        System.out.println("Do zobaczenia!");
    }

    private static int[] generateSecretCode(int[] code) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT);
        }
        return code;
    }

    private static int getUsersGuess(Scanner scanner, long attemptCounter) {
        String usersGuessString = getXCharString(scanner, attemptCounter);
        int usersGuessInt = 0;
        while (!guessIsValid(usersGuessString)) {

            if (usersGuessString.equals("q")) return 0;

            System.out.print("To muszą być cyfry od 1 do " + MAX_DIGIT + "! Próba " + attemptCounter + " > ");
            usersGuessString = getXCharString(scanner, attemptCounter);
        }
        try {
            usersGuessInt = Integer.parseInt(usersGuessString);
        } catch (NumberFormatException e) {
            System.out.print("To muszą być cyfry od 1 do " + MAX_DIGIT + "! Próba " + attemptCounter + " > ");
        }
        return usersGuessInt;
    }

    private static String getXCharString(Scanner scanner, long attemptCounter) {
        String xCharString = "";
        while (xCharString.length() != CODE_LENGTH) {
            xCharString = scanner.nextLine();

            if (xCharString.equals("q")) return "q";

            if (xCharString.length() != CODE_LENGTH) {
                System.out.print("Kod musi mieć " + CODE_LENGTH + " cyfr");
                if (CODE_LENGTH < 5) {
                    System.out.print("y! Próba " + attemptCounter + " > ");
                } else {
                    System.out.print("! Próba " + attemptCounter + " > ");
                }
            }
        }
        return xCharString;
    }

    private static boolean guessIsValid(String guess) {
        for (char c : guess.toCharArray()) {
            if (c < '1' || c > ((char) MAX_DIGIT + '0')) {
                return false;
            }
        }
        return true;
    }
}
