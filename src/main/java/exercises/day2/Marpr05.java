package exercises.day2;

import java.util.Scanner;

public class Marpr05 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Podaj swój wiek:");
    int age = scanner.nextInt();
    if (age >= 18) System.out.println("Jesteś pełnoletni.");
    else System.out.println("Jesteś niepełnoletni.");
    scanner.close();
  }
}