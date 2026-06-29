import java.util.Scanner;

public class PreciseRounding {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a decimal number (or 'exit' to stop): ");
        while (input.hasNextDouble()) {
            double val = input.nextDouble();
            
            System.out.printf("Original Value: %f%n", val);
            System.out.printf("Nearest Integer: %.0f%n", roundToInteger(val));
            System.out.printf("Nearest Tenth: %.1f%n", roundToTenths(val));
            System.out.printf("Nearest Hundredth: %.2f%n", roundToHundredths(val));
            System.out.printf("Nearest Thousandth: %.3f%n%n", roundToThousandths(val));
            
            System.out.print("Enter next number: ");
        }
        input.close();
    }

    public static double roundToInteger(double number) {
        return Math.floor(number + 0.5);
    }

    public static double roundToTenths(double number) {
        return Math.floor(number * 10 + 0.5) / 10;
    }

    public static double roundToHundredths(double number) {
        return Math.floor(number * 100 + 0.5) / 100;
    }

    public static double roundToThousandths(double number) {
        return Math.floor(number * 1000 + 0.5) / 1000;
    }
}