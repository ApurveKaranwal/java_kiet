/**
 * Case Study 2: Bank Account
 * Class with account number, holder name, balance.
 * Supports deposit, withdraw (not allowed if > balance), and displayBalance.
 */
public class CaseStudy2 {

    static class BankAccount {
        private long accountNumber;
        private String holderName;
        private double balance;

        public BankAccount(long accountNumber, String holderName, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Successfully deposited: Rs. " + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Invalid withdrawal amount.");
            } else if (amount > balance) {
                System.out.println("Withdrawal failed! Insufficient balance. Current balance: Rs. " + balance);
            } else {
                balance -= amount;
                System.out.println("Successfully withdrawn: Rs. " + amount);
            }
        }

        public void displayBalance() {
            System.out.println("Account Holder : " + holderName);
            System.out.println("Account Number : " + accountNumber);
            System.out.println("Current Balance: Rs. " + balance);
        }
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount(1023456789L, "Rahul Sharma", 10000.0);

        acc.displayBalance();
        acc.deposit(5000);
        acc.withdraw(2000);
        acc.withdraw(15000); // should fail - insufficient
        System.out.println();
        acc.displayBalance();
    }
}
