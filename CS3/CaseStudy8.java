import java.util.Vector;

/**
 * Case Study 8: Bus Reservation System Passenger Fare Calculation
 * 
 * A bus reservation system stores passenger ages in a Vector.
 * Ticket fare is Rs. 500 per passenger.
 * Discount conditions:
 * - Age below 5        -> Free (100% discount, Rs. 0 fare)
 * - Age 5–12           -> 50% discount (Rs. 250 fare)
 * - Age 13–59          -> Full fare (0% discount, Rs. 500 fare)
 * - Age 60 or above    -> 30% discount (Rs. 350 fare)
 * 
 * Calculates the fare for every passenger.
 */
public class CaseStudy8 {

    public static final double BASE_FARE = 500.0;

    static class Passenger {
        private int passengerId;
        private String name;
        private int age;

        public Passenger(int passengerId, String name, int age) {
            this.passengerId = passengerId;
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getCategory() {
            if (age < 5) return "Infant (Free)";
            if (age <= 12) return "Child (50% Off)";
            if (age <= 59) return "Adult (Full Fare)";
            return "Senior (30% Off)";
        }

        public double getDiscountPercentage() {
            if (age < 5) return 100.0;
            if (age <= 12) return 50.0;
            if (age <= 59) return 0.0;
            return 30.0;
        }

        public double calculateFare() {
            double discountAmount = (getDiscountPercentage() / 100.0) * BASE_FARE;
            return BASE_FARE - discountAmount;
        }

        public void displayRow() {
            String discStr = String.format("%.0f%%", getDiscountPercentage());
            System.out.printf("%-12d %-16s %-6d %-20s %-12s %-12.2f\n",
                    passengerId, name, age, getCategory(), discStr, calculateFare());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 8: BUS RESERVATION FARE SYSTEM (VECTOR) =====");
        System.out.println("Base Ticket Fare: Rs. " + BASE_FARE);

        // Storing passenger ages in a Vector (as specified)
        Vector<Integer> passengerAges = new Vector<>();
        passengerAges.add(3);   // Below 5 -> Free
        passengerAges.add(9);   // 5 - 12  -> 50% discount
        passengerAges.add(24);  // 13 - 59 -> Full fare
        passengerAges.add(45);  // 13 - 59 -> Full fare
        passengerAges.add(67);  // 60+     -> 30% discount
        passengerAges.add(72);  // 60+     -> 30% discount
        passengerAges.add(11);  // 5 - 12  -> 50% discount
        passengerAges.add(2);   // Below 5 -> Free

        // Storing passenger records in a Vector
        Vector<Passenger> passengers = new Vector<>();
        String[] names = {
            "Baby Aarav", "Master Rohan", "Neha Sharma", "Rajesh Verma",
            "Dharampal Singh", "Savitri Devi", "Meera Sen", "Baby Kabir"
        };

        for (int i = 0; i < passengerAges.size(); i++) {
            passengers.add(new Passenger(i + 1, names[i], passengerAges.get(i)));
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-12s %-16s %-6s %-20s %-12s %-12s\n",
                "Pass ID", "Name", "Age", "Category", "Discount", "Final Fare (Rs.)");
        System.out.println("=".repeat(80));

        double totalFare = 0.0;
        for (Passenger p : passengers) {
            p.displayRow();
            totalFare += p.calculateFare();
        }

        System.out.println("=".repeat(80));
        System.out.printf("%-55s %-12.2f\n", "TOTAL FARE COLLECTED (" + passengers.size() + " Passengers): Rs.", totalFare);
        System.out.println("=".repeat(80));
    }
}
