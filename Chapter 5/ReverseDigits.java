import java.util.Scanner;

public class ReverseDigits {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter an integer to reverse: ");
        int number = input.nextInt();
        
        int reversed = reverse(number);
        System.out.printf("Reversed number: %d%n", reversed);
        
        input.close();
    }

    public static int reverse(int number) {
        int reversed = 0;
        while (number != 0) {
            int remainder = number % 10;
            reversed = reversed * 10 + remainder;
            number /= 10;
        }
        return reversed;
    }
}