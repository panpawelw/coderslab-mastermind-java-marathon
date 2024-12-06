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

        System.out.println(Arrays.toString(secretCode));

        System.out.println("Zgadnij 4-cyfrowy kod składający się z liczb od 1 do 6:");

        String input = scanner.nextLine();

        scanner.close();
    }

    private static int[] generateSecretCode(int[] code) {

        Random random = new Random();

        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(Mastermind.MAX_DIGIT);
        }

        return code;
    }
}
