
package assignment1;


public class MainApp {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("1111111111", 
                "Diogo",120000, 0.5);
        CurrentAccount current = new CurrentAccount("11368075225", 
                "Andres", 43000, 2000);

        System.out.println("================ Initial State ================");
        savings.displayBalance();
        current.displayBalance();

        System.out.println("\n=========== Performing Operations ===========");
        savings.deposit(500.0);
        savings.withdraw(200.0);

        current.deposit(150.0);
        current.withdraw(8000);

        System.out.println("\n============== Display Balances =============");
        savings.displayBalance();
        current.displayBalance();

        System.out.println("\n=========== Calculating Interest ============");
        savings.calculateInterest();
        current.calculateInterest();
    }
}