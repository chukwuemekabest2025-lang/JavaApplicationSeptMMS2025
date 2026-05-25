import java.util.Scanner;

public class Chapter3_17{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int totalMiles = 0, totalGallons = 0;
		int miles, gallons;
		double milesPerGallon, totalMilesPerGallon;
		
		System.out.print("Enter the number of miles driven: ");
		miles = input.nextInt();
		
		while (miles != -1) {
			//miles != -1 is being used because this is a sentinel-controlled repititive
			// -1 acts as a stop value to define that miles should always be positive
			System.out.print("Enter the amount of gallons used: ");
			gallons = input.nextInt();
			
			milesPerGallon = (double) miles / (double) gallons;
			
			System.out.printf("Miles per gallon for this trip: %.2f%n", milesPerGallon);
			
			totalMiles += miles;
			totalGallons += gallons;
			
			if (totalGallons > 0) {
				totalMilesPerGallon = (double) totalMiles / (double) totalGallons;
				System.out.printf("Total Miles per gallon: %.2f%n", totalMilesPerGallon);
			}
			
			System.out.print("Enter number of miles drivern (-1 to quit): ");
			miles = input.nextInt();
			
		}
		
		if (totalGallons > 0) {
			totalMilesPerGallon = (double) totalMiles / (double) totalGallons;
			System.out.println("");
			System.out.println("----------------------------------------");
			System.out.printf("Total Miles per gallon: %.2f%n", totalMilesPerGallon);
		}
	}
}