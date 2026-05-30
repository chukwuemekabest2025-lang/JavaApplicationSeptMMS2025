import java.util.Scanner;

public class NumberCalculation{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
		int[] num = new int[10];

        System.out.println("Enter 10 numbers:");
        for (int i = 0; i < 10; i++) {
            num[i] = input.nextInt();
        }

        int sum1 = num[0] + num[4] + num[9];
        int sum2 = num[2] + num[7] + num[1];
        int product = sum1 * sum2;
        int sum3 = num[3] + num[6] + num[5] + num[8];
        int result = sum3 - product;

        System.out.println("Result = " + result);

        if (result >= 100) {
            System.out.println("Hurray I did it");
        } else {
            System.out.println("I still need to learn more in Java");
        }

        input.close();
    }
}