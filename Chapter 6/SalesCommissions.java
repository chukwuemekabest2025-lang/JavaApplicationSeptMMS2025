import java.util.Scanner;

public class SalesCommissions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[] salaryRanges = new int[9]; 

        System.out.println("Enter salesperson gross sales (-1 to end):");
        double grossSales = input.nextDouble();

        while (grossSales != -1) {
            int salary = 200 + (int) (0.09 * grossSales);
            System.out.printf("Salary is: $%d%n", salary);

            int rangeIndex = (salary - 200) / 100;

            if (rangeIndex > 8) {
                rangeIndex = 8; 
            }

            if (rangeIndex >= 0) {
                salaryRanges[rangeIndex]++;
            } else {
                System.out.println("Invalid salary calculated.");
            }

            System.out.println("Enter next gross sales (-1 to end):");
            grossSales = input.nextDouble();
        }

        System.out.printf("%n%-15s%s%n", "Salary Range", "Number of Salespeople");
        System.out.println("-------------------------------------");
        for (int i = 0; i < salaryRanges.length - 1; i++) {
            System.out.printf("$%d-$%d          %d%n", 200 + (i * 100), 299 + (i * 100), salaryRanges[i]);
        }
        System.out.printf("$1000 and over     %d%n", salaryRanges[8]);
        
        input.close();
    }
}