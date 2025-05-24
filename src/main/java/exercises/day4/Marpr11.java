package exercises.day4;

import java.util.Scanner;

public class Marpr11 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Podaj pierwszą liczbę całkowitą: ");
    int firstNumber = scanner.nextInt();
    System.out.print("Podaj drugą liczbę całkowitą: ");
    int secondNumber = scanner.nextInt();

    try {
      int result = firstNumber / secondNumber;
      System.out.println("wynik wynosi: " + result);

    } catch(ArithmeticException e) {
      System.out.println("Dzielenie przez zero nie jest możliwe!");
    }
    scanner.close();
  }
}
