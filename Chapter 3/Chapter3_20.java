import java.util.Scanner;

public class Chapter3_20{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		double hours;
		double rate;
		double pay;
		
		for (int i = 1; i <= 3; i++){
			System.out.println("Employee " + i + ":");
			System.out.print("Enter hours worked: ");
			hours = input.nextDouble();
			System.out.print("Enter hourly rate: ");
			rate = input.nextDouble();
			
			if (hours <= 40){
				pay = hours * rate;
			}
			else{
				pay = (40 * rate) + ((hours - 40) * rate * 1.5);
			}
			
			System.out.println("Gross pay: $" + pay);
			System.out.println("");
		}
	}
}
