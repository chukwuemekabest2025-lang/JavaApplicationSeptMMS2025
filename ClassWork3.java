import java.util.Scanner;

public class ClassWork3{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
/*Write a java program to accept name, age and address and format the data you have received to give a 
meaningful message on the screen e.g Hello jack, you are ... years old*/
		System.out.print("Enter your name: ");
		String name = input.nextLine();
		
		System.out.print("Enter your age: ");
		int age = input.nextInt();
		input.nextLine();
		
		System.out.print("Enter your address: ");
		String address = input.nextLine();
		
		System.out.println("");
		System.out.println("=====================================");
		
		System.out.printf("Hello %s, you are %d years old%nAnd you live at %s",name,age,address);
		System.out.println("");
		
	}
}
