import java.util.Vector;

/**
 * Case Study 7: Electricity Bill Calculation System
 * 
 * An electricity board stores the electricity consumption of customers in a Vector.
 * Tariff structure:
 * - First 100 units      -> Rs. 2/unit
 * - Next 100 units       -> Rs. 3/unit
 * - Above 200 units      -> Rs. 5/unit
 * Surcharge:
 * - If total bill > Rs. 1,000, an additional 10% surcharge is applied.
 */
public class CaseStudy7 {

    static class CustomerBill {
        private String customerId;
        private String customerName;
        private int unitsConsumed;

        public CustomerBill(String customerId, String customerName, int unitsConsumed) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.unitsConsumed = unitsConsumed;
        }

        public int getUnitsConsumed() {
            return unitsConsumed;
        }

        // Slab-wise bill calculation
        public double calculateBaseBill() {
            double bill;
            if (unitsConsumed <= 100) {
                bill = unitsConsumed * 2.0;
            } else if (unitsConsumed <= 200) {
                bill = (100 * 2.0) + ((unitsConsumed - 100) * 3.0);
            } else {
                bill = (100 * 2.0) + (100 * 3.0) + ((unitsConsumed - 200) * 5.0);
            }
            return bill;
        }

        // Surcharge of 10% if bill exceeds Rs. 1,000
        public double calculateSurcharge() {
            double baseBill = calculateBaseBill();
            if (baseBill > 1000.0) {
                return baseBill * 0.10;
            }
            return 0.0;
        }

        public double calculateTotalBill() {
            return calculateBaseBill() + calculateSurcharge();
        }

        public void displayRow() {
            System.out.printf("%-10s %-16s %-12d %-12.2f %-12.2f %-12.2f\n",
                    customerId, customerName, unitsConsumed, calculateBaseBill(), calculateSurcharge(), calculateTotalBill());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 7: ELECTRICITY CONSUMPTION & BILLING (VECTOR) =====");

        // Electricity consumption stored in a Vector
        Vector<CustomerBill> customerBills = new Vector<>();

        customerBills.add(new CustomerBill("EB101", "Suresh Raina",  80));  // First slab (<= 100)
        customerBills.add(new CustomerBill("EB102", "Pooja Sharma",  150)); // Second slab (<= 200)
        customerBills.add(new CustomerBill("EB103", "Kunal Kapoor",  250)); // Third slab (> 200, Bill <= 1000)
        customerBills.add(new CustomerBill("EB104", "Vikram Malhotra", 350)); // Third slab (> 200, Bill > 1000 with surcharge)
        customerBills.add(new CustomerBill("EB105", "Anita Roy",      500)); // Large consumption with surcharge

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-10s %-16s %-12s %-12s %-12s %-12s\n",
                "Cust ID", "Customer Name", "Units (kWh)", "Base Bill", "Surcharge", "Total (Rs.)");
        System.out.println("=".repeat(80));

        double totalRevenue = 0.0;
        int totalUnits = 0;

        for (CustomerBill cb : customerBills) {
            cb.displayRow();
            totalRevenue += cb.calculateTotalBill();
            totalUnits += cb.getUnitsConsumed();
        }

        System.out.println("=".repeat(80));
        System.out.printf("%-27s %-12d %-24s %-12.2f\n", "TOTALS", totalUnits, "", totalRevenue);
        System.out.println("=".repeat(80));
    }
}
