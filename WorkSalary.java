//Write a java Program to accept employee name, hours worked, hourly rate then calculate the gross salary, tax 10% and net salary
import java.util.Scanner;

public class WorkSalary	 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = in.nextLine();

        System.out.print("Enter the hours you've worked: ");
        double hours = in.nextDouble();

        System.out.print("Enter the Hourly rate (pay per hour): ");
        double rate = in.nextDouble();

        double gross = hours * rate;
        double tax = gross * 0.1;
        double net = gross - tax;

		System.out.println("\nEmployee salary details");
		System.out.println("=======================");
        System.out.println("\nEmployee: " + name);
        System.out.println("Gross: " + gross);
        System.out.println("Tax: " + tax);
        System.out.println("Net: " + net);
    }
}