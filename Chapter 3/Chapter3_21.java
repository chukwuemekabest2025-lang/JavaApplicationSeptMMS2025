import java.util.Scanner;

public class Chapter3_21{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		int counter = 1;
		int number;
		int largest = 0;
		
		while (counter <= 10){
			System.out.print("Enter integer: ");
			number = input.nextInt();
			
			if (number > largest){
				largest = number;
			}
			
			counter++;
		}
		
		System.out.println("Largest number: " + largest);
	}
}
