import java.util.Scanner;

public class Chapter2_3{
	public static void main(String[] args) {
		//question A
		int c;
		int thisIsAVariable;
		int q76354;
		int number;
		
		//question B and C
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an integer: ");
		int number1 = input.nextInt();
		
		//question D
		System.out.println("This is a Java Program");
		
		//question E
		System.out.printf("%s%n%s", "This is a java", "Program");
		
		//question F
		//In order for the if statement to work, you need to define the num identifier 
		//and make it accept input/integer
		
		System.out.print("\nEnter a number: ");
		int num_ = input.nextInt();
		if (num_ != 7) {
			System.out.print("The variable number is not equal to 7.\n");
		}
	}
}