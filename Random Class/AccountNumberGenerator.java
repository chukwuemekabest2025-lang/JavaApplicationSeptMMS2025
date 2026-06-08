import java.util.Random;

public class AccountNumberGenerator {
    public static void main(String[] args) {
        System.out.println("Generated Account Number: " + generateAccountNumber());
    }

    public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder("30");
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int nextDigit = random.nextInt(10);
            accountNumber.append(nextDigit);
        }

        return accountNumber.toString();
    }
}