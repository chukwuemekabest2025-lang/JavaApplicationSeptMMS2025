
package assignment1;


public class CurrentAccount extends BankAccount {
    double overdraftLimit;
    public CurrentAccount(String accountNumber, String accountHolder, 
            double balance, double overdraftLimit){
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= -overdraftLimit){
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from Current Account " 
                    + accountNumber);
        }
        else{
            System.out.println("Withdrawal exceeds overdraft limit!");
        }
    }
    
    @Override
    public void calculateInterest(){
        System.out.println("Current Account (" + accountNumber + ") "
                + "does not earn interest");
    }
}
