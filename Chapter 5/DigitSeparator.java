import java.util.Scanner;

public class DigitSeparator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter an integer between 1 and 99999: ");
        int number = input.nextInt();
        
        if (number >= 1 && number <= 99999) {
            displayDigits(number);
        } else {
            System.out.println("Number out of range!");
        }
        
        input.close();
    }

    public static int getQuotient(int a, int b) {
        return a / b;
    }

    public static int getRemainder(int a, int b) {
        return a % b;
    }

    public static void displayDigits(int number) {
        int divisor = 10000;
        while (getQuotient(number, divisor) == 0 && divisor > 1) {
            divisor = getQuotient(divisor, 10);
        }
        
        String result = "";
        while (divisor >= 1) {
            int digit = getQuotient(number, divisor);
            result += digit + "  ";
            number = getRemainder(number, divisor);
            divisor = getQuotient(divisor, 10);
        }
        System.out.println("Separated digits: " + result.trim());
    }
}