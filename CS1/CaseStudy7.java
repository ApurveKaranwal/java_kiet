import java.util.Scanner;

/**
 * Case Study 7: ATM Simulation
 * - Checks PIN (max 3 incorrect attempts)
 * - Allows withdrawal, deposit, display balance
 */
public class CaseStudy7 {

    static class ATM {
        private final int CORRECT_PIN = 1234;
        private double balance;
        private int attempts;
        private boolean authenticated;

        public ATM(double balance) {
            this.balance = balance;
            this.attempts = 0;
            this.authenticated = false;
        }

        public boolean checkPIN(int pin) {
            if (pin == CORRECT_PIN) {
                authenticated = true;
                System.out.println("PIN verified. Access granted.");
                return true;
            } else {
                attempts++;
                int remaining = 3 - attempts;
                System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                if (attempts >= 3) {
                    System.out.println("Card blocked! Maximum attempts exceeded.");
                }
                return false;
            }
        }

        public void deposit(double amount) {
            if (authenticated && amount > 0) {
                balance += amount;
                System.out.println("Deposited: Rs. " + amount);
            } else if (!authenticated) {
                System.out.println("Please authenticate first.");
            } else {
                System.out.println("Invalid amount.");
            }
        }

        public void withdraw(double amount) {
            if (!authenticated) {
                System.out.println("Please authenticate first.");
            } else if (amount <= 0) {
                System.out.println("Invalid amount.");
            } else if (amount > balance) {
                System.out.println("Insufficient balance. Current balance: Rs. " + balance);
            } else {
                balance -= amount;
                System.out.println("Withdrawn: Rs. " + amount);
            }
        }

        public void displayBalance() {
            if (authenticated) {
                System.out.println("Current Balance: Rs. " + balance);
            } else {
                System.out.println("Please authenticate first.");
            }
        }

        public boolean isAuthenticated() {
            return authenticated;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(50000.0);

        System.out.println("=== ATM SIMULATION ===");
        // Simulate PIN entry with up to 3 tries
        int attempts = 0;
        while (attempts < 3 && !atm.isAuthenticated()) {
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();
            if (atm.checkPIN(pin)) {
                break;
            }
            attempts++;
        }

        if (atm.isAuthenticated()) {
            System.out.println("\n--- Menu ---");
            atm.displayBalance();
            atm.deposit(10000);
            atm.withdraw(5000);
            atm.displayBalance();
        } else {
            System.out.println("Transaction aborted. ATM card retained.");
        }
    }
}
