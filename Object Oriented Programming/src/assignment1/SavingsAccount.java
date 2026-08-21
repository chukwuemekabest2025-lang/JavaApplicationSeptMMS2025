
package assignment1;


public class SavingsAccount extends BankAccount {
    double interestRate;
    
    public SavingsAccount (String accountNumber, String accountHolder, 
            double balance, double interestRate){
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }
    
    @Override
    public void withdraw(double amount){
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Insufficient funds or invalid withdrawal "
                    + "amount in Savings Account.");
        }
    }
    
    @Override
    public void calculateInterest(){
        double interest = balance * interestRate;
        System.out.println("Savings Interest calculated: $" + interest + 
                " (Rate: " + (interestRate * 100) + "%)");
    }
}
