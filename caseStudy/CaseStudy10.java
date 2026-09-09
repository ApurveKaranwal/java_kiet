/**
 * Case Study 10: E-Commerce Payment System — Polymorphism
 * Payment interface with pay() method.
 * Implemented by CreditCard, UPI, and NetBanking classes.
 */
public class CaseStudy10 {

    interface Payment {
        void pay(double amount);
    }

    static class CreditCard implements Payment {
        private String cardNumber;
        private String cardHolder;

        public CreditCard(String cardNumber, String cardHolder) {
            this.cardNumber = cardNumber;
            this.cardHolder = cardHolder;
        }

        @Override
        public void pay(double amount) {
            System.out.println("=== Credit Card Payment ===");
            System.out.println("Card Holder : " + cardHolder);
            System.out.println("Card Number : " + cardNumber);
            System.out.println("Amount Paid : Rs. " + amount);
            System.out.println("Status      : Payment Successful");
        }
    }

    static class UPI implements Payment {
        private String upiId;

        public UPI(String upiId) {
            this.upiId = upiId;
        }

        @Override
        public void pay(double amount) {
            System.out.println("=== UPI Payment ===");
            System.out.println("UPI ID    : " + upiId);
            System.out.println("Amount    : Rs. " + amount);
            System.out.println("Status    : Payment Successful (via UPI)");
        }
    }

    static class NetBanking implements Payment {
        private String bankName;
        private String accountNumber;

        public NetBanking(String bankName, String accountNumber) {
            this.bankName = bankName;
            this.accountNumber = accountNumber;
        }

        @Override
        public void pay(double amount) {
            System.out.println("=== Net Banking Payment ===");
            System.out.println("Bank        : " + bankName);
            System.out.println("Account No. : " + accountNumber);
            System.out.println("Amount      : Rs. " + amount);
            System.out.println("Status      : Payment Successful");
        }
    }

    public static void processPayment(Payment payment, double amount) {
        payment.pay(amount);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("===== E-COMMERCE PAYMENT SYSTEM =====\n");

        Payment creditCard = new CreditCard("4111-1111-1111-1111", "Rahul Sharma");
        Payment upi = new UPI("rahul@upi");
        Payment netBanking = new NetBanking("SBI", "XXXXXXXXXXXX1234");

        processPayment(creditCard, 3500.00);
        processPayment(upi, 1200.50);
        processPayment(netBanking, 10000.00);
    }
}
