import java.util.Scanner;

public class Chapter2_6{
	public static void main(String[] args) {
		//Program to Calculate the product of 3 integers
		Scanner input = new Scanner(System.in);
		int x, y, z;
		System.out.print("Enter the first integer: ");
		x = input.nextInt();
		System.out.print("Enter the second integer: ");
		y = input.nextInt();
		System.out.print("Enter the third integer: ");
		z = input.nextInt();
		
		int result = x * y * z;
		
		System.out.printf("The Product is %d",result);
	}
}