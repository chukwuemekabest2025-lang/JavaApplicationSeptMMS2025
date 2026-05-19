
import java.util.Scanner;

public class ClassWork1{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int num1 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num2 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num3 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num4 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num5 = input.nextInt();
		
		System.out.println("");
		System.out.println("=====================================");
		
		int addition = num1 + num2 + num3 + num4 + num5;
		int average = (num1 + num2 + num3 + num4 + num5)/5;
		int product = num1 * num2 * num3 * num4 * num5;
		
		System.out.printf("The sum of the numbers is %d%n",addition);
		System.out.printf("The average of the numbers is %d%n",average);
		System.out.printf("The product of the numbers is %d%n",product);
		
		
		
		
		
		
		
		
		
		//Write a java program that will accept 5 numbers from the user and find the sum, 
		//average and product of the five numbers
		//Write a java program to accept a number from a user and check if the number is an even number
		//Write a java program to accept name, age and address and format the data you have received to 
		//give a meaningful message on the screen
		//e.g Hello jack, you are ... years old
		
		
	}	
}	