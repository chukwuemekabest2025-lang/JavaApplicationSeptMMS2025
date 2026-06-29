import java.util.Scanner;

public class MinimumFinder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter three floating-point numbers separated by spaces: ");
        double num1 = input.nextDouble();
        double num2 = input.nextDouble();
        double num3 = input.nextDouble();
        
        double minimum = minimum3(num1, num2, num3);
        System.out.printf("The smallest value is: %f%n", minimum);
        
        input.close();
    }

    public static double minimum3(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }
}