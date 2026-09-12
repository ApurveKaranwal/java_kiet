import java.util.ArrayList;

/**
 * Case Study 5: Online Shopping Discount System
 * 
 * An online shopping system stores product prices in an ArrayList.
 * The customer receives a discount based on the total purchase amount:
 * - Total >= Rs. 10,000 -> 20% discount
 * - Total >= Rs. 5,000  -> 10% discount
 * - Total >= Rs. 2,000  -> 5% discount
 * - Otherwise           -> No discount (0%)
 * 
 * The program calculates the discount and final payable amount.
 */
public class CaseStudy5 {

    private ArrayList<Double> productPrices;

    public CaseStudy5() {
        productPrices = new ArrayList<>();
    }

    public void addProductPrice(double price) {
        if (price > 0) {
            productPrices.add(price);
        } else {
            System.out.println("Invalid product price: " + price);
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (double price : productPrices) {
            total += price;
        }
        return total;
    }

    public static double getDiscountPercentage(double total) {
        if (total >= 10000) return 20.0;
        if (total >= 5000)  return 10.0;
        if (total >= 2000)  return 5.0;
        return 0.0;
    }

    public void generateBill(String customerName) {
        double total = calculateTotal();
        double discountPercent = getDiscountPercentage(total);
        double discountAmount = (discountPercent / 100.0) * total;
        double finalPayable = total - discountAmount;

        System.out.println("\n----------------- INVOICE: " + customerName + " -----------------");
        System.out.println("Itemized Product Prices:");
        for (int i = 0; i < productPrices.size(); i++) {
            System.out.printf("  Item %d: Rs. %.2f\n", (i + 1), productPrices.get(i));
        }
        System.out.println("-".repeat(48));
        System.out.printf("Total Purchase Amount : Rs. %.2f\n", total);
        System.out.printf("Discount Applied (%.0f%%) : Rs. %.2f\n", discountPercent, discountAmount);
        System.out.println("-".repeat(48));
        System.out.printf("Final Payable Amount  : Rs. %.2f\n", finalPayable);
        System.out.println("-".repeat(48));
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 5: ONLINE SHOPPING DISCOUNT CALCULATION =====");

        // Scenario 1: Total >= 10,000 (Eligible for 20% discount)
        CaseStudy5 order1 = new CaseStudy5();
        order1.addProductPrice(4500.0);
        order1.addProductPrice(3200.0);
        order1.addProductPrice(2800.0);
        order1.addProductPrice(1500.0);
        order1.generateBill("Customer 1 (Total >= 10,000)");

        // Scenario 2: Total >= 5,000 (Eligible for 10% discount)
        CaseStudy5 order2 = new CaseStudy5();
        order2.addProductPrice(2500.0);
        order2.addProductPrice(3500.0);
        order2.generateBill("Customer 2 (Total >= 5,000)");

        // Scenario 3: Total >= 2,000 (Eligible for 5% discount)
        CaseStudy5 order3 = new CaseStudy5();
        order3.addProductPrice(1200.0);
        order3.addProductPrice(1100.0);
        order3.generateBill("Customer 3 (Total >= 2,000)");

        // Scenario 4: Total < 2,000 (No discount)
        CaseStudy5 order4 = new CaseStudy5();
        order4.addProductPrice(850.0);
        order4.addProductPrice(600.0);
        order4.generateBill("Customer 4 (Total < 2,000)");
    }
}
