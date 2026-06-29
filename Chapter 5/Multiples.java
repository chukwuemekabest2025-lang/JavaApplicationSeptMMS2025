import java.util.Scanner;

public class Multiples {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter pairs of integers to test (Type 'exit' to stop):");
        while (true) {
            System.out.print("Enter first integer: ");
            if (!input.hasNextInt()) break;
            int first = input.nextInt();
            
            System.out.print("Enter second integer: ");
            int second = input.nextInt();
            
            if (isMultiple(first, second)) {
                System.out.printf("%d IS a multiple of %d%n%n", second, first);
            } else {
                System.out.printf("%d IS NOT a multiple of %d%n%n", second, first);
            }
        }
        input.close();
    }

    public static boolean isMultiple(int first, int second) {
        if (first == 0) return false; // Prevent division by zero
        return second % first == 0;
    }
}