/*write a java program to accept 3 numbers, find the sum of the three numbers and check if the sum of the three numbers 
is an even number and is divisible by 10, if true accept three numbers and find the product of the 3 numbers and check 
if the product is greater than 200, if true display hurray if false display ongoing but if the sum of the three numbers 
is not an even number and not divisible by ten display i must solve this program on my own*/
import java.util.Scanner;

public class NumberLogic{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter three numbers:");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();

        int sum = num1 + num2 + num3;

        if (sum % 2 == 0 && sum % 10 == 0) {
            
            System.out.println("Sum condition met. Enter three more numbers:");
            int num4 = scanner.nextInt();
            int num5 = scanner.nextInt();
            int num6 = scanner.nextInt();
			int product = num4 * num5 * num6;
            if (product > 200) {
                System.out.println("hurray");
            } else {
                System.out.println("ongoing");
            }
            
        } else {
            System.out.println("i must solve this program on my own");
        }

        scanner.close();
    }
}