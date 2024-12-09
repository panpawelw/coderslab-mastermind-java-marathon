package exercises.day_3;

import java.util.Random;

public class Marpr08 {
    public static void main(String[] args) {
        Random random = new Random();
        int number = random.nextInt(36) + 1;
        System.out.println(number);
    }
}
