package mastermind;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH]);
        System.out.println("Zgadnij " + CODE_LENGTH +"-cyfrowy kod składający się z cyfr od 1 do " + MAX_DIGIT
                + " lub wprowadź 'q' aby wyjść :");
        int counter = 0;
        while(true) {
            counter ++;
            System.out.print("Próba " + counter + " >");
            System.out.println("Podałeś kod: " + getUsersGuess(scanner, counter));
        }
    }

    private static int[] generateSecretCode(int[] code) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT);
        }
        return code;
    }

    private static int getUsersGuess(Scanner scanner, int currentTry) {
        String usersGuessString = getXCharString(scanner, currentTry);
        int usersGuessInt = 0;
        while (!guessIsValid(usersGuessString)) {
            System.out.println("To muszą być cyfry od 1 do " + MAX_DIGIT + "!");
            System.out.print("Próba " + currentTry + " >");
            usersGuessString = getXCharString(scanner, currentTry);
        }
        try {
            usersGuessInt = Integer.parseInt(usersGuessString);
        } catch (NumberFormatException e) {
            System.out.println("To muszą być cyfry od 1 do " + MAX_DIGIT + "!");
        }
        return usersGuessInt;
    }

    private static String getXCharString(Scanner scanner, int currentTry) {
        String xCharString = "";
        while (xCharString.length() != CODE_LENGTH) {
            xCharString = scanner.nextLine();

            if (xCharString.equals("q")) {
                scanner.close();
                System.out.println("Do zobaczenia!");
                System.exit(0);
            }

            if (xCharString.length() != CODE_LENGTH) {
                System.out.print("Kod musi mieć " + CODE_LENGTH + " cyfr");
                if (CODE_LENGTH < 5) {
                    System.out.println("y!");
                } else {
                    System.out.println("!");
                }
                System.out.print("Próba " + currentTry + " >");
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
