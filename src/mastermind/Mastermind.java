package mastermind;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static final int CODE_LENGTH = 5;
    public static final int MAX_DIGIT = 6;
    public static final int MAX_TRIES = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] secretCode = generateSecretCode(new int[CODE_LENGTH]);
        System.out.println("Zgadnij " + CODE_LENGTH +"-cyfrowy kod składający się z cyfr od 1 do " + MAX_DIGIT + " :");
        String usersGuessString;
        int usersGuessInt = 0;
        for (int i = 1; i <= MAX_TRIES; i++) {
            System.out.print(">");
            usersGuessString = getXCharString(scanner);
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

    private static String getXCharString(Scanner scanner) {
        String fourCharString = "";
        while (fourCharString.length() != CODE_LENGTH) {
            fourCharString = scanner.nextLine();
            if (fourCharString.length() != CODE_LENGTH) {
                System.out.print("Kod musi mieć " + CODE_LENGTH + " cyfr");
                if (CODE_LENGTH < 5) {
                    System.out.println("y!");
                } else {
                    System.out.println("!");
                }
                System.out.print(">");
            }
        }
        return fourCharString;
    }

//    private static boolean
}
