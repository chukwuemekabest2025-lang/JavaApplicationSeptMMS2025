import java.util.Scanner;

public class ParkingGarage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double totalReceipts = 0.0;
        
        System.out.println("Enter hours parked (or enter -1 to quit):");
        while (true) {
            System.out.print("Enter hours: ");
            double hours = input.nextDouble();
            if (hours == -1) {
                break;
            }
            
            double charge = calculateCharges(hours);
            totalReceipts += charge;
            
            System.out.printf("Current Customer Charge: $%.2f%n", charge);
            System.out.printf("Running Total of Receipts: $%.2f%n%n", totalReceipts);
        }
        System.out.println("Program terminated.");
        input.close();
    }

    public static double calculateCharges(double hours) {
        double minFee = 2.00;
        double maxFee = 10.00;
        
        if (hours <= 3.0) {
            return minFee;
        }
        double extraHours = Math.ceil(hours - 3.0);
        double fee = minFee + (extraHours * 0.50);
        
        return Math.min(fee, maxFee);
    }
}