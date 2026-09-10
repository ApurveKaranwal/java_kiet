import java.util.Scanner;

/**
  * Case Study 2: Bank Account Withdrawal
  * A bank allows a customer to withdraw money from an account.
  * Handles:
  *  1. Insufficient balance
  *  2. Invalid withdrawal amount (amount <= 0)
  *  3. Invalid input (non-numeric text entered)
  */
public class BankAccountWithdrawal {

    // Custom Exception Classes
    public static class InsufficientBalanceException extends Exception {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

    public static class InvalidWithdrawalAmountException extends Exception {
        public InvalidWithdrawalAmountException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void withdrawAmount() throws InsufficientBalanceException, InvalidWithdrawalAmountException, InvalidInputException {
        Scanner sc = new Scanner(System.in);
        double balance = 10000.0; // Current account balance

        System.out.println("Current Account Balance: Rs. " + balance);
        System.out.print("Enter withdrawal amount: ");
        
        double amount;
        try {
            amount = Double.parseDouble(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Withdrawal amount must be a valid number.");
        }

        if (amount <= 0) {
            throw new InvalidWithdrawalAmountException("Invalid withdrawal amount: Amount must be greater than 0 (entered: Rs. " + amount + ").");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance: Attempted to withdraw Rs. " + amount + ", but available balance is only Rs. " + balance + ".");
        }

        // Withdrawal successful
        balance -= amount;
        System.out.println("\n========== TRANSACTION RECEIPT ==========");
        System.out.println("Transaction Status : Successful");
        System.out.println("Amount Withdrawn   : Rs. " + String.format("%.2f", amount));
        System.out.println("Remaining Balance  : Rs. " + String.format("%.2f", balance));
        System.out.println("=========================================");
    }

    public static void main(String[] args) {
        try {
            withdrawAmount();
        } catch (InsufficientBalanceException | InvalidWithdrawalAmountException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}