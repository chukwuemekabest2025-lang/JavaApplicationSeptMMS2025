public class CompoundInterest {
    public static void main(String[] args) {
        double principal = 1000.0; 
        int years = 10;

        // Outer loop modifies the interest rate from 5% to 10%
        for (int ratePercent = 5; ratePercent <= 10; ratePercent++) {
            double rate = ratePercent / 100.0;
            System.out.printf("%nInterest Rate: %d%%%n", ratePercent);
            System.out.printf("%-5s%s%n", "Year", "Amount on Deposit");

            for (int year = 1; year <= years; year++) {
                double amount = principal * Math.pow(1.0 + rate, year);
                System.out.printf("%-5d$%,14.2f%n", year, amount);
            }
        }
    }
}