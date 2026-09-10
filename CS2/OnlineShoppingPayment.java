import java.util.Scanner;

/**
  * Case Study 4: Online Shopping and Payment
  * An online shopping system calculates the final amount after applying a discount.
  * Handles:
  *  1. Invalid product price (price <= 0)
  *  2. Invalid quantity (quantity <= 0)
  *  3. Invalid discount percentage (discount < 0 or > 100)
  *  4. Payment amount less than the bill
  *  5. Invalid input (non-numeric text entered)
  */
public class OnlineShoppingPayment {

    // Custom Exception Classes
    public static class InvalidProductPriceException extends Exception {
        public InvalidProductPriceException(String message) {
            super(message);
        }
    }

    public static class InvalidQuantityException extends Exception {
        public InvalidQuantityException(String message) {
            super(message);
        }
    }

    public static class InvalidDiscountException extends Exception {
        public InvalidDiscountException(String message) {
            super(message);
        }
    }

    public static class PaymentException extends Exception {
        public PaymentException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void processPayment() throws InvalidProductPriceException, InvalidQuantityException, InvalidDiscountException, PaymentException, InvalidInputException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product price (in Rs.): ");
        double price;
        try {
            price = Double.parseDouble(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Product price must be a valid number.");
        }

        if (price <= 0) {
            throw new InvalidProductPriceException("Invalid product price: Price must be greater than 0 (entered: Rs. " + price + ").");
        }

        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Quantity must be a valid integer.");
        }

        if (quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity: Quantity must be greater than 0 (entered: " + quantity + ").");
        }

        System.out.print("Enter discount percentage (0 - 100): ");
        double discountPercent;
        try {
            discountPercent = Double.parseDouble(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Discount percentage must be a valid number.");
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountException("Invalid discount percentage: Must be between 0% and 100% (entered: " + discountPercent + "%).");
        }

        double totalBill = price * quantity;
        double discountAmount = (totalBill * discountPercent) / 100.0;
        double finalAmount = totalBill - discountAmount;

        System.out.println("\n-------------------------------------------");
        System.out.println("Total Bill Before Discount : Rs. " + String.format("%.2f", totalBill));
        System.out.println("Discount Applied (" + discountPercent + "%)    : Rs. " + String.format("%.2f", discountAmount));
        System.out.println("Net Payable Amount         : Rs. " + String.format("%.2f", finalAmount));
        System.out.println("-------------------------------------------");

        System.out.print("Enter payment amount (in Rs.): ");
        double paymentAmount;
        try {
            paymentAmount = Double.parseDouble(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Payment amount must be a valid number.");
        }

        if (paymentAmount < finalAmount) {
            throw new PaymentException("Payment failed: Paid amount (Rs. " + String.format("%.2f", paymentAmount) + ") is less than the payable bill (Rs. " + String.format("%.2f", finalAmount) + "). Short by Rs. " + String.format("%.2f", (finalAmount - paymentAmount)) + ".");
        }

        double change = paymentAmount - finalAmount;
        System.out.println("\n============= PAYMENT RECEIPT =============");
        System.out.println("Payment Status  : Paid Successfully");
        System.out.println("Total Billed    : Rs. " + String.format("%.2f", finalAmount));
        System.out.println("Amount Paid     : Rs. " + String.format("%.2f", paymentAmount));
        System.out.println("Change Returned : Rs. " + String.format("%.2f", change));
        System.out.println("===========================================");
    }

    public static void main(String[] args) {
        try {
            processPayment();
        } catch (InvalidProductPriceException | InvalidQuantityException | InvalidDiscountException | PaymentException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}