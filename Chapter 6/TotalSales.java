import java.util.Scanner;

public class TotalSales {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] sales = new double[5][4];

        System.out.println("Enter sales slip data (Enter -1 for salesperson to stop):");
        while (true) {
            System.out.print("Enter salesperson number (1-4): ");
            int salesperson = input.nextInt();
            if (salesperson == -1) break;

            System.out.print("Enter product number (1-5): ");
            int product = input.nextInt();

            System.out.print("Enter total dollar value: ");
            double value = input.nextDouble();

            if (salesperson >= 1 && salesperson <= 4 && product >= 1 && product <= 5 && value >= 0) {
                sales[product - 1][salesperson - 1] += value;
            } else {
                System.out.println("Invalid entry. Please re-enter slip data.");
            }
            System.out.println();
        }

        System.out.printf("%n%-12s %10s %10s %10s %10s %12s%n", "Product", "Sales 1", "Sales 2", "Sales 3", "Sales 4", "Total Product");
        System.out.println("-----------------------------------------------------------------------------");

        double[] salespersonTotals = new double[4];

        for (int row = 0; row < sales.length; row++) {
            double productTotal = 0.0;
            System.out.printf("Product %-4d ", row + 1);

            for (int col = 0; col < sales[row].length; col++) {
                System.out.printf("%11.2f", sales[row][col]);
                productTotal += sales[row][col];
                salespersonTotals[col] += sales[row][col]; 
            }
            System.out.printf("%13.2f%n", productTotal);
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-12s ", "Total Sales");
        
        double grandTotal = 0.0;
        for (double total : salespersonTotals) {
            System.out.printf("%11.2f", total);
            grandTotal += total;
        }
        System.out.printf("%13.2f%n", grandTotal);
        
        input.close();
    }
}