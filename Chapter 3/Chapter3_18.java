import java.util.Scanner;

public class Chapter3_18{
	public static void main(String[] args) {
		Scanner input = new Scaner(System.in);
		int account, balance, charges, credits, limit, newBalance;
		
		System.out.print("Enter account number: ");
		account = input.nextInt();
		
		while (account != -1) {
			System.out.print("Enter beginning balance: ");
			balance = input.nextInt();
			
			System.out.print("Enter total charges: ");
			charges = input.nextInt();
			
			System.out.print("Enter total credits: ");
			credits = input.nextInt();
			
			System.out.print("Enter credit limit: ");
			limit = input.nextInt();
			
			newBalance = balance  + charges - credits;
			
			System.out.printf("New balance: %d", newBalance);
			
			if (newBalance > limit) {
				System.out.println("Balance exceeded credit limit");
			}
			
			System.out.print("Enter account number: ");
			account = input.nextInt();
		}
		
		System.out.println("Done");
	}
}