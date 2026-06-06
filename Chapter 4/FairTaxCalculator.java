import java.util.Scanner;

public class FairTaxCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("FairTax Alternative Tax Estimator");
        System.out.println("Enter estimated monthly expenses for the following categories:\n");

        System.out.print("Housing: "); double housing = input.nextDouble();
        System.out.print("Food: "); double food = input.nextDouble();
        System.out.print("Clothing: "); double clothing = input.nextDouble();
        System.out.print("Transportation: "); double transportation = input.nextDouble();
        System.out.print("Education: "); double education = input.nextDouble();
        System.out.print("Health Care: "); double health = input.nextDouble();
        System.out.print("Vacations/Leisure: "); double vacations = input.nextDouble();

        double totalExpenses = housing + food + clothing + transportation + education + health + vacations;
        
        // FairTax calculation patterns
        double fairTaxInclusive = totalExpenses * 0.23;
        double fairTaxExclusive = totalExpenses * 0.30;

        System.out.printf("%nTotal Annual/Monthly Expenses: $%,.2f%n", totalExpenses);
        System.out.printf("Estimated FairTax (23%% retail-inclusive rate): $%,.2f%n", fairTaxInclusive);
        System.out.printf("Estimated FairTax (30%% retail-exclusive counter-argument rate): $%,.2f%n", fairTaxExclusive);
    }
}