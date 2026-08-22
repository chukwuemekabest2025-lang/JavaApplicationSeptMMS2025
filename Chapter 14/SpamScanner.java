import java.util.Scanner;

public class SpamScanner {
    private static final String[] spamKeywords = {
        "free", "click here", "winner", "congratulations", "earn money",
        "act now", "limited time", "risk free", "cash bonus", "100% free",
        "urgent", "double your income", "guaranteed", "no cost", "order now",
        "credit card", "investment", "cheap", "discount", "claim now",
        "passwords", "verify account", "lottery", "prize", "millions",
        "subscribers", "instant cash", "unbelievable", "pure profit", "apply now"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email message body:");
        String message = scanner.nextLine().toLowerCase();

        int score = 0;
        for (String keyword : spamKeywords) {
            int index = message.indexOf(keyword);
            while (index != -1) {
                score++;
                index = message.indexOf(keyword, index + 1);
            }
        }

        System.out.println("\nSpam Score: " + score);
        if (score == 0) {
            System.out.println("Likelihood: Very unlikely to be spam.");
        } else if (score <= 3) {
            System.out.println("Likelihood: Low spam probability.");
        } else if (score <= 6) {
            System.out.println("Likelihood: Moderate spam probability.");
        } else {
            System.out.println("Likelihood: HIGH SPAM PROBABILITY!");
        }
        scanner.close();
    }
}