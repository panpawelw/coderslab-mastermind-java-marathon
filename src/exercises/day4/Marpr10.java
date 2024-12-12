package exercises.day4;

import java.util.Scanner;

public class Marpr10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę:");
        int integer;
        try {
            integer = Integer.parseInt(scanner.nextLine());
            System.out.println("Podałeś liczbę " + integer);
        } catch (NumberFormatException e) {
            System.out.println("To nie jest liczba!");
        }
        scanner.close();
    }
}