import java.util.Scanner;

public class Chapter3_38{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		int choice;
		int num;
		int d1, d2, d3, d4;
		
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.print("Choose: ");
		choice = input.nextInt();
		
		System.out.print("Enter four-digit integer: ");
		num = input.nextInt();
		
		d1 = num / 1000;
		d2 = (num / 100) % 10;
		d3 = (num / 10) % 10;
		d4 = num % 10;
		
		if (choice == 1){
			d1 = (d1 + 7) % 10;
			d2 = (d2 + 7) % 10;
			d3 = (d3 + 7) % 10;
			d4 = (d4 + 7) % 10;
			
			System.out.println("Encrypted: " + d3 + d4 + d1 + d2);
		}
		else if (choice == 2){
			int orig1 = d3;
			int orig2 = d4;
			int orig3 = d1;
			int orig4 = d2;
			
			orig1 = (orig1 + 3) % 10;
			orig2 = (orig2 + 3) % 10;
			orig3 = (orig3 + 3) % 10;
			orig4 = (orig4 + 3) % 10;
			
			System.out.println("Decrypted: " + orig1 + orig2 + orig3 + orig4);
		}
	}
}
