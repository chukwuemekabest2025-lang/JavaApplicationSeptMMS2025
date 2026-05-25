import java.util.Scanner;

public class Chapter3_19{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		double totalSales = 0;
		double itemValue;
		double earnings;
		
		System.out.print("Enter value of item sold (-1 to quit): ");
		itemValue = input.nextDouble();
		
		while (itemValue != -1){
			totalSales += itemValue;
			
			System.out.print("Enter value of item sold (-1 to quit): ");
			itemValue = input.nextDouble();
		}
		
		earnings = 200 + (0.09 * totalSales);
		
		System.out.println("Total sales: $" + totalSales);
		System.out.println("Earnings: $" + earnings);
	}
}
