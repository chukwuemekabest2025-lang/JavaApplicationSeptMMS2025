import java.util.Scanner;
import java.util.Random;

public class CrapsWager {
    private static final Random random = new Random();
    enum Status { CONTINUE, WON, LOST }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double bankBalance = 1000.0;
        
        System.out.printf("Welcome to Craps! Your starting balance is $%.2f%n", bankBalance);
        
        while (bankBalance > 0) {
            System.out.print("Enter your wager: $");
            double wager = input.nextDouble();
            
            while (wager <= 0 || wager > bankBalance) {
                System.out.print("Invalid wager. Re-enter within your balance: $");
                wager = input.nextDouble();
            }
            
            displayChatter();
            
            // Run one game logic
            int myPoint = 0;
            Status gameStatus;
            int sumOfDice = rollDice();

            switch (sumOfDice) {
                case 7: case 11:
                    gameStatus = Status.WON;
                    break;
                case 2: case 3: case 12:
                    gameStatus = Status.LOST;
                    break;
                default:
                    gameStatus = Status.CONTINUE;
                    myPoint = sumOfDice;
                    System.out.printf("Point is %d%n", myPoint);
                    break;
            }

            while (gameStatus == Status.CONTINUE) {
                sumOfDice = rollDice();
                if (sumOfDice == myPoint) {
                    gameStatus = Status.WON;
                } else if (sumOfDice == 7) {
                    gameStatus = Status.LOST;
                }
            }

            if (gameStatus == Status.WON) {
                bankBalance += wager;
                System.out.printf("You Win! New Balance: $%.2f%n%n", bankBalance);
            } else {
                bankBalance -= wager;
                System.out.printf("You Lose! New Balance: $%.2f%n%n", bankBalance);
                if (bankBalance <= 0) {
                    System.out.println("Sorry. You busted!");
                }
            }
            
            if (bankBalance > 0) {
                System.out.print("Keep playing? (true/false): ");
                if (!input.nextBoolean()) break;
            }
        }
        input.close();
    }

    public static int rollDice() {
        int die1 = 1 + random.nextInt(6);
        int die2 = 1 + random.nextInt(6);
        return die1 + die2;
    }

    public static void displayChatter() {
        String[] phrases = {
            "Oh, you're going for broke, huh?",
            "Aw c'mon, take a chance!",
            "You're up big. Now's the time to cash in your chips!",
            "Let the dice roll!"
        };
        System.out.println(phrases[random.nextInt(phrases.length)]);
    }
}