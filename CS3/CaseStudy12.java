import java.util.Stack;

/**
 * Case Study 12: College Scholarship Eligibility System using Stack
 * 
 * A college maintains scholarship-eligible students using a Stack.
 * Scholarship slabs based on percentage:
 * - Percentage >= 90 -> Rs. 20,000
 * - Percentage >= 80 -> Rs. 15,000
 * - Percentage >= 70 -> Rs. 10,000
 * - Percentage >= 60 -> Rs. 5,000
 * - Below 60         -> No scholarship (Rs. 0)
 * 
 * Students are processed from the top of the stack, following LIFO order.
 */
public class CaseStudy12 {

    static class Student {
        private int rollNumber;
        private String name;
        private double percentage;

        public Student(int rollNumber, String name, double percentage) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.percentage = percentage;
        }

        public int getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        public double getPercentage() {
            return percentage;
        }

        public double calculateScholarship() {
            if (percentage >= 90.0) return 20000.0;
            if (percentage >= 80.0) return 15000.0;
            if (percentage >= 70.0) return 10000.0;
            if (percentage >= 60.0) return 5000.0;
            return 0.0;
        }

        @Override
        public String toString() {
            return String.format("[Roll: %d] %-16s | %.2f%%", rollNumber, name, percentage);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 12: SCHOLARSHIP PROCESSING (STACK - LIFO) =====");

        Stack<Student> studentStack = new Stack<>();

        // Enrolling / pushing students onto the Stack
        System.out.println("\n[Action] Pushing students onto the Stack...");
        Student s1 = new Student(101, "Aarav Gupta",    92.5); // >= 90 (Rs. 20,000)
        Student s2 = new Student(102, "Ishita Sharma",  84.0); // >= 80 (Rs. 15,000)
        Student s3 = new Student(103, "Manish Kumar",   73.5); // >= 70 (Rs. 10,000)
        Student s4 = new Student(104, "Priya Nair",     63.0); // >= 60 (Rs. 5,000)
        Student s5 = new Student(105, "Vikas Verma",    54.0); // < 60  (No scholarship)
        Student s6 = new Student(106, "Divya Agarwal",  95.0); // >= 90 (Rs. 20,000)

        studentStack.push(s1);
        System.out.println("Pushed: " + s1);
        studentStack.push(s2);
        System.out.println("Pushed: " + s2);
        studentStack.push(s3);
        System.out.println("Pushed: " + s3);
        studentStack.push(s4);
        System.out.println("Pushed: " + s4);
        studentStack.push(s5);
        System.out.println("Pushed: " + s5);
        studentStack.push(s6);
        System.out.println("Pushed: " + s6 + " <-- TOP OF STACK");

        System.out.println("\nTotal students queued on Stack: " + studentStack.size());

        // Processing students in LIFO order (popping from top of stack)
        System.out.println("\n[Action] Processing students from Top of Stack (LIFO Order):");
        System.out.println("=".repeat(75));
        System.out.printf("%-6s %-10s %-18s %-12s %-20s\n",
                "Order", "Roll No", "Student Name", "Percentage", "Scholarship Awarded");
        System.out.println("=".repeat(75));

        int processOrder = 1;
        double totalDisbursed = 0.0;
        int recipientsCount = 0;

        while (!studentStack.isEmpty()) {
            Student current = studentStack.pop();
            double scholarship = current.calculateScholarship();
            totalDisbursed += scholarship;

            String awardStatus = (scholarship > 0)
                    ? String.format("Rs. %,.2f", scholarship)
                    : "No scholarship";

            if (scholarship > 0) {
                recipientsCount++;
            }

            String pctStr = String.format("%.2f%%", current.getPercentage());
            System.out.printf("#%-5d %-10d %-18s %-12s %-20s\n",
                    processOrder++, current.getRollNumber(), current.getName(), pctStr, awardStatus);
        }

        System.out.println("=".repeat(75));
        System.out.println("\n--- SCHOLARSHIP DISBURSEMENT SUMMARY ---");
        System.out.println("Total Students Processed     : " + (processOrder - 1));
        System.out.println("Scholarship Recipients Count : " + recipientsCount);
        System.out.printf("Total Scholarship Disbursed  : Rs. %,.2f\n", totalDisbursed);
        System.out.println("Stack Status                 : " + (studentStack.isEmpty() ? "EMPTY (All processed)" : "Pending"));
        System.out.println("=".repeat(75));
    }
}
