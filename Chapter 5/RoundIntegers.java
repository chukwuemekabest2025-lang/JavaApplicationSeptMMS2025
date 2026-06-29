import java.util.Scanner;

public class RoundIntegers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter double values to round (type 'exit' to stop):");
        while (input.hasNextDouble()) {
            double x = input.nextDouble();
            double y = Math.floor(x + 0.5);
            
            System.out.printf("Original: %f | Rounded: %.0f%n%n", x, y);
        }
        input.close();
    }
}