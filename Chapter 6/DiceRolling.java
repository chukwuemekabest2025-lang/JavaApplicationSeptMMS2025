import java.util.Random;

public class DiceRolling {
    public static void main(String[] args) {
        Random randomNumbers = new Random();
        
        int[] frequencies = new int[13]; 
        final int ROLLS = 36_000_000;

        for (int i = 0; i < ROLLS; i++) {
            int die1 = 1 + randomNumbers.nextInt(6);
            int die2 = 1 + randomNumbers.nextInt(6);
            int sum = die1 + die2;
            frequencies[sum]++;
        }

        System.out.printf("%s%15s%n", "Sum", "Frequency");
        System.out.println("----------------------");
        for (int sum = 2; sum < frequencies.length; sum++) {
            System.out.printf("%2d%16d%n", sum, frequencies[sum]);
        }
    }
}