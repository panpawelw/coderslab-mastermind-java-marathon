package exercises.day3;

import java.util.Random;

public class Marpr09 {

    public static boolean partOfArrayContains(int[] array, int index) {
        for (int i = 0; i < index; i++) {
            if (array[i] == array[index]) return true;
        }
        return false;
    }
    public static void main(String[] args) {

        int[] array = new int[5];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            do {
                array[i] = random.nextInt(50) + 1;
            } while (partOfArrayContains(array, i));
        }

        for (int j : array) {
            System.out.println(j);
        }
    }
}
