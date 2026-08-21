
package assignment1;


public abstract class BankAccount {
    String accountNumber;
    String accountHolder;
    double balance;
    
    public BankAccount
        (String accountNumber,String accountHolder,double balance){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
        
    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
            System.out.println("Successfully deposited $" + 
                    amount + " into accont" + accountNumber);
        }
        else{
            System.out.println("Deposit amount must be positive.");
        }
    }
    
    public void displayBalance(){
        System.out.println("Account: " + accountNumber + " | Holder: " + 
                accountHolder + " | Current Balance: $" + balance);
    }
    public abstract void withdraw(double amount);
    public abstract void calculateInterest();
    }

