import java.util.Stack;

/**
 * Case Study 10: ATM Withdrawal System using Stack
 * 
 * An ATM maintains recent transactions using a Stack.
 * The user has an initial balance of Rs. 20,000.
 * Withdrawal transactions to process:
 * 1. Rs. 5,000
 * 2. Rs. 3,000
 * 3. Rs. 8,000
 * 4. Rs. 6,000
 * 
 * Conditions:
 * - Allow withdrawal only when sufficient balance is available.
 * - Successfully completed transactions are pushed to the stack.
 * - The latest successful transaction should be accessible using peek().
 */
public class CaseStudy10 {

    private double balance;
    private Stack<Double> successfulTransactions;

    public CaseStudy10(double initialBalance) {
        this.balance = initialBalance;
        this.successfulTransactions = new Stack<>();
    }

    public boolean withdraw(double amount) {
        System.out.println("\n[Request] Attempting to withdraw: Rs. " + amount);
        System.out.printf("Current available balance: Rs. %.2f\n", balance);

        if (amount <= 0) {
            System.out.println("Status: FAILED - Invalid withdrawal amount.");
            return false;
        }

        if (amount <= balance) {
            balance -= amount;
            successfulTransactions.push(amount);
            System.out.printf("Status: SUCCESS - Rs. %.2f dispensed.\n", amount);
            System.out.printf("Remaining balance: Rs. %.2f\n", balance);
            return true;
        } else {
            System.out.printf("Status: REJECTED - Insufficient balance! (Short by Rs. %.2f)\n", (amount - balance));
            return false;
        }
    }

    // Access latest successful transaction using peek()
    public void displayLatestTransaction() {
        if (!successfulTransactions.isEmpty()) {
            System.out.println("\n--- Latest Successful Transaction (via peek()) ---");
            System.out.printf("Latest Withdrawal Amount: Rs. %.2f\n", successfulTransactions.peek());
        } else {
            System.out.println("\nNo successful transactions in stack.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("\n--- Transaction History (Stack: Bottom to Top) ---");
        if (successfulTransactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }
        for (int i = 0; i < successfulTransactions.size(); i++) {
            System.out.printf("  Txn %d: Rs. %.2f\n", (i + 1), successfulTransactions.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 10: ATM TRANSACTION SYSTEM (STACK) =====");

        double initialBalance = 20000.0;
        CaseStudy10 atm = new CaseStudy10(initialBalance);
        System.out.printf("Initial Account Balance: Rs. %.2f\n", initialBalance);

        // Required transactions to process:
        // Rs. 5,000, Rs. 3,000, Rs. 8,000, Rs. 6,000
        double[] withdrawalRequests = { 5000.0, 3000.0, 8000.0, 6000.0 };

        for (double req : withdrawalRequests) {
            atm.withdraw(req);
        }

        // Display latest successful transaction using peek()
        atm.displayLatestTransaction();

        // Display all successful transactions
        atm.displayTransactionHistory();

        System.out.printf("\nFinal Account Balance: Rs. %.2f\n", atm.balance);
    }
}
