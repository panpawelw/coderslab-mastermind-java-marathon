package exercises.day4;

import java.util.Scanner;

public class Marpr11 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj pierwszą liczbę całkowitą:");
        int firstNumber = scanner.nextInt();
        System.out.println("Podaj drugą liczbę całkowitą:");
        int secondNumber = scanner.nextInt();

        try {
            double result = (double) firstNumber / secondNumber;
            System.out.println(result);

        } catch(ArithmeticException e) {
            System.out.println("Dzielenie przez zero nie jest możliwe!");
        }
        scanner.close();
    }
}
