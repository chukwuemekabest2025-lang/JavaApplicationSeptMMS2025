import java.util.Scanner;
import java.util.Random;

public class CoinToss {
    enum Coin { HEADS, TAILS }
    
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int headsCount = 0;
        int tailsCount = 0;
        
        while (true) {
            System.out.println("1. Toss Coin");
            System.out.println("2. Exit and Show Results");
            System.out.print("Choose option: ");
            int choice = input.nextInt();
            
            if (choice == 1) {
                Coin result = flip();
                if (result == Coin.HEADS) {
                    headsCount++;
                    System.out.println("Result: HEADS");
                } else {
                    tailsCount++;
                    System.out.println("Result: TAILS");
                }
                System.out.printf("Current Counts -> Heads: %d, Tails: %d%n%n", headsCount, tailsCount);
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid option. Try again.%n");
            }
        }
        
        System.out.println("--- Final Results ---");
        System.out.printf("Total Heads: %d%n", headsCount);
        System.out.printf("Total Tails: %d%n", tailsCount);
        input.close();
    }

    public static Coin flip() {
        if (random.nextBoolean()) {
            return Coin.HEADS;
        } else {
            return Coin.TAILS;
        }
    }
}