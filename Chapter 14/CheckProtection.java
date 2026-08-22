import java.util.Scanner;

public class CheckProtection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter dollar amount (e.g., 99.87): ");
        double amount = scanner.nextDouble();

        String formattedAmount = String.format("%.2f", amount);
        int leadingAsterisks = 9 - formattedAmount.length();

        StringBuilder protectedCheck = new StringBuilder();
        for (int i = 0; i < leadingAsterisks; i++) {
            protectedCheck.append("*");
        }
        protectedCheck.append(formattedAmount);

        System.out.println("Protected amount: " + protectedCheck.toString());
        scanner.close();
    }
}