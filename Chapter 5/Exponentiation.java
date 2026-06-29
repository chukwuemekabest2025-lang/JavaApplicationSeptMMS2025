import java.util.Scanner;

public class Exponentiation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter base (integer): ");
        int base = input.nextInt();
        System.out.print("Enter exponent (positive integer): ");
        int exponent = input.nextInt();
        
        int result = integerPower(base, exponent);
        System.out.printf("%d to the power of %d is: %d%n", base, exponent, result);
        
        input.close();
    }

    public static int integerPower(int base, int exponent) {
        int total = 1;
        for (int i = 0; i < exponent; i++) {
            total *= base;
        }
        return total;
    }
}