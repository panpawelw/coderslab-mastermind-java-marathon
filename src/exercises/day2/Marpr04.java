package exercises.day2;

import java.util.Scanner;

public class Marpr04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj swoje imię:");
        String name = scanner.nextLine();
        System.out.println("Cześć, " + name + "!");
        scanner.close();
    }
}
