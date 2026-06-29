import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        
        while (playAgain) {
            int secretNumber = 1 + random.nextInt(1000);
            int guessCount = 0;
            int guess = 0;
            
            System.out.println("Guess a number between 1 and 1000.");
            
            while (guess != secretNumber) {
                System.out.print("Enter your guess: ");
                guess = input.nextInt();
                guessCount++;
                
                if (guess > secretNumber) {
                    System.out.println("Too high. Try again.");
                } else if (guess < secretNumber) {
                    System.out.println("Too low. Try again.");
                }
            }
            
            // 5.30 Winning Statement
            System.out.println("Congratulations. You guessed the number!");
            System.out.printf("Total tries: %d%n", guessCount);
            
            // 5.31 Feedback Logic
            if (guessCount < 10) {
                System.out.println("Either you know the secret or you got lucky!");
            } else if (guessCount == 10) {
                System.out.println("Aha! You know the secret!");
            } else {
                System.out.println("You should be able to do better!");
            }
            
            System.out.print("Do you want to play again? (type true/false): ");
            playAgain = input.nextBoolean();
        }
        
        System.out.println("Thanks for playing!");
        input.close();
    }
}