import java.util.Scanner;
public class AcceptDay{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		// write a java program to accept a day in number 
		int day;
		
		System.out.print("Enter a corresponding number to the week:");
		day = input.nextInt();
		
		System.out.println("");
		System.out.println("=====================================");
		
		if(day == 1){
			System.out.println("Monday");
		}
		else if(day == 2){
			System.out.println("Tuesday");
		}
		else if(day == 3){
			System.out.println("Wednesday");
		}
		else if(day == 4){
			System.out.println("Thursday");
		}
		else if(day == 5){
			System.out.println("Friday");
		}
		else if(day == 6){
			System.out.println("Saturday");
		}
		else if(day == 7){
			System.out.println("Sunday");
		}
	}
}

