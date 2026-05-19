import java.util.Scanner;

public class ClassWork2{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
//Write a java program to accept a number from a user and check if the number is an even number
		
		System.out.print("Enter a number: ");
		int num1 = input.nextInt();
		
		System.out.println("");
		System.out.println("=====================================");		
		
		boolean EvenNumber = num1 % 2 == 0;		
		System.out.printf("is %d an even number (true/false): %b%n",num1,EvenNumber);
		
		
	}
}	