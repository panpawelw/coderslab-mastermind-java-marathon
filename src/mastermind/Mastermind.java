package mastermind;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static final int CODE_LENGTH = 4;
    public static final int MAX_DIGIT = 6;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int[] secretCode = new int[CODE_LENGTH];

        System.out.println("Hello World!");
        System.out.println("Zgadnij 4-cyfrowy kod składający się z liczb od 1 do 6:");

        String input = scanner.nextLine();

        for (int i = 0; i < CODE_LENGTH; i++) {
            secretCode[i] = random.nextInt(MAX_DIGIT) + 1;
        }

        scanner.close();
    }
}
