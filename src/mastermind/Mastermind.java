package mastermind;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH]);
        System.out.println("Zgadnij 4-cyfrowy kod składający się z cyfr od 1 do 6:");
        String usersGuessString;
        int usersGuessInt = 0;
        while (usersGuessInt < 1 || usersGuessInt > 6666) {
            System.out.print(">");
            usersGuessString = getFourCharString(scanner);
            try {
                usersGuessInt = Integer.parseInt(usersGuessString);
            } catch (NumberFormatException e) {
                System.out.println("To muszą być cyfry!");
            }

        }
        System.out.println("Podałeś kod:" + usersGuessInt);
        scanner.close();
    }

    private static int[] generateSecretCode(int[] code) {
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT);
        }
        return code;
    }

    private static String getFourCharString(Scanner scanner) {
        String fourCharString = "";
        while (fourCharString.length() != 4) {
            fourCharString = scanner.nextLine();
            if (fourCharString.length() != 4) {
                System.out.println("Kod musi mieć 4 cyfry!");
                System.out.print(">");
            }
        }
        return fourCharString;
    }
}
