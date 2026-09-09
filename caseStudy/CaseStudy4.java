/**
 * Case Study 4: Online Shopping Cart
 * Product with ID, name, price, quantity.
 * Total cost per product and total bill.
 * If total bill > Rs. 5000, apply 10% discount.
 */
public class CaseStudy4 {

    static class Product {
        private int productId;
        private String productName;
        private double price;
        private int quantity;

        public Product(int productId, String productName, double price, int quantity) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public double calculateCost() {
            return price * quantity;
        }

        public void displayProduct() {
            System.out.println("Product ID : " + productId);
            System.out.println("Name       : " + productName);
            System.out.println("Price      : Rs. " + price);
            System.out.println("Quantity   : " + quantity);
            System.out.println("Cost       : Rs. " + String.format("%.2f", calculateCost()));
        }
    }

    public static void main(String[] args) {
        Product[] cart = {
            new Product(1, "Laptop", 45000, 1),
            new Product(2, "Mouse", 800, 2),
            new Product(3, "Keyboard", 1200, 1)
        };

        double totalBill = 0;
        System.out.println("===== SHOPPING CART =====");
        for (Product p : cart) {
            p.displayProduct();
            System.out.println("-------------------------");
            totalBill += p.calculateCost();
        }

        double discount = 0;
        if (totalBill > 5000) {
            discount = totalBill * 0.10;
        }
        double finalBill = totalBill - discount;

        System.out.println("\nTotal Bill          : Rs. " + String.format("%.2f", totalBill));
        System.out.println("Discount (10%)      : Rs. " + String.format("%.2f", discount));
        System.out.println("Final Amount to Pay : Rs. " + String.format("%.2f", finalBill));
    }
}
