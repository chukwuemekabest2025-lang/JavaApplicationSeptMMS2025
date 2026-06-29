import java.util.Scanner;

public class HypotenuseCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Hypotenuse Calculator (Enter 0 for side 1 to exit)");
        while (true) {
            System.out.print("Enter Side 1: ");
            double side1 = input.nextDouble();
            if (side1 == 0) break;
            
            System.out.print("Enter Side 2: ");
            double side2 = input.nextDouble();
            
            double result = hypotenuse(side1, side2);
            System.out.printf("Hypotenuse length: %.2f%n%n", result);
        }
        input.close();
    }

    public static double hypotenuse(double side1, double side2) {
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
    }
}