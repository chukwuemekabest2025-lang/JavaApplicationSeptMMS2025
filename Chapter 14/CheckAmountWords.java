import java.util.Scanner;

public class CheckAmountWords {
    private static final String[] ones = {
        "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
        "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"
    };

    private static final String[] tens = {
        "", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numeric check amount (< 1000): ");
        double amount = scanner.nextDouble();

        int dollars = (int) amount;
        int cents = (int) Math.round((amount - dollars) * 100);

        StringBuilder words = new StringBuilder();

        if (dollars >= 100) {
            words.append(ones[dollars / 100]).append(" hundred ");
            dollars %= 100;
        }

        if (dollars >= 20) {
            words.append(tens[dollars / 10]).append(" ");
            dollars %= 10;
        }

        if (dollars > 0) {
            words.append(ones[dollars]).append(" ");
        }

        words.append("and ").append(cents).append("/100");

        System.out.println("Spelled out: " + words.toString().replaceAll("\\s+", " ").trim());
        scanner.close();
    }
}