import java.util.Scanner;


public class SmallestValue {

    public static void main(String[] args) {
        SmallestValue finder = new SmallestValue(); 
        finder.run();
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the number of values to input: ");
        int totalValues = input.nextInt();
        
        if (totalValues <= 0) {
            System.out.println("No values to process.");
            return;
        }

        int smallestValue = findSmallest(totalValues, input);
        System.out.println("The smallest integer is: " + smallestValue);
    }

    public int findSmallest(int count, Scanner scanner) {
        System.out.print("Enter value 1: ");
        int smallest = scanner.nextInt();

        for (int i = 2; i <= count; i++) {
            System.out.print("Enter value " + i + ": ");
            int current = scanner.nextInt();
            if (current < smallest) {
                smallest = current;
            }
        }
        return smallest;
    }
}