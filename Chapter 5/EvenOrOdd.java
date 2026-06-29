import java.util.Scanner;

public class EvenOrOdd {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter numbers to check (Type 'exit' to stop):");
        while (input.hasNextInt()) {
            int num = input.nextInt();
            if (isEven(num)) {
                System.out.printf("%d is even.%n", num);
            } else {
                System.out.printf("%d is odd.%n", num);
            }
        }
        input.close();
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}