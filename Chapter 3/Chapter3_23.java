import java.util.Scanner;

public class Chapter3_23{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		int counter = 1;
		int number;
		int largest = 0;
		int secondLargest = 0;
		
		while (counter <= 10){
			System.out.print("Enter integer: ");
			number = input.nextInt();
			
			if (number > largest){
				secondLargest = largest;
				largest = number;
			}
			else if (number > secondLargest){
				secondLargest = number;
			}
			
			counter++;
		}
		
		System.out.println("Largest: " + largest);
		System.out.println("Second largest: " + secondLargest);
	}
}
