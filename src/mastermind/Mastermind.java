package mastermind;

import java.util.Scanner;

public class Mastermind {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Zgadnij 4-cyfrowy kod składający się z liczb od 1 do 6:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        scanner.close();
    }
}
