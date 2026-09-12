import java.util.ArrayList;

/**
 * Case Study 6: Employee Salary Bonus System
 * 
 * A company stores employee salaries in an ArrayList.
 * Bonuses are awarded according to salary:
 * - Salary >= Rs. 80,000 -> 15% bonus
 * - Salary >= Rs. 50,000 -> 10% bonus
 * - Salary >= Rs. 30,000 -> 5% bonus
 * - Salary < Rs. 30,000  -> No bonus (0%)
 * 
 * Calculates the bonus and revised salary for every employee.
 */
public class CaseStudy6 {

    static class Employee {
        private int empId;
        private String name;
        private double salary;

        public Employee(int empId, String name, double salary) {
            this.empId = empId;
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public double getBonusPercentage() {
            if (salary >= 80000) return 15.0;
            if (salary >= 50000) return 10.0;
            if (salary >= 30000) return 5.0;
            return 0.0;
        }

        public double getBonusAmount() {
            return (getBonusPercentage() / 100.0) * salary;
        }

        public double getRevisedSalary() {
            return salary + getBonusAmount();
        }

        public void displayRow() {
            String bonusPctStr = String.format("%.0f%%", getBonusPercentage());
            System.out.printf("%-8d %-16s %-14.2f %-10s %-14.2f %-14.2f\n",
                    empId, name, salary, bonusPctStr, getBonusAmount(), getRevisedSalary());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 6: EMPLOYEE SALARY BONUS CALCULATION =====");

        ArrayList<Employee> employees = new ArrayList<>();

        // Adding employees spanning all bonus tiers
        employees.add(new Employee(101, "Amitabh Roy",   95000.0)); // >= 80,000 (15%)
        employees.add(new Employee(102, "Neha Singhal",  82000.0)); // >= 80,000 (15%)
        employees.add(new Employee(103, "Rajesh Kumar",  60000.0)); // >= 50,000 (10%)
        employees.add(new Employee(104, "Pooja Hegde",   45000.0)); // >= 30,000 (5%)
        employees.add(new Employee(105, "Manoj Tiwari",  22000.0)); // < 30,000  (0%)

        System.out.println("\n" + "=".repeat(82));
        System.out.printf("%-8s %-16s %-14s %-10s %-14s %-14s\n",
                "Emp ID", "Name", "Base Salary", "Bonus %", "Bonus (Rs.)", "Revised Salary");
        System.out.println("=".repeat(82));

        double totalBaseSalary = 0;
        double totalBonusPayout = 0;
        double totalRevisedSalary = 0;

        for (Employee emp : employees) {
            emp.displayRow();
            totalBaseSalary += emp.getSalary();
            totalBonusPayout += emp.getBonusAmount();
            totalRevisedSalary += emp.getRevisedSalary();
        }

        System.out.println("=".repeat(82));
        System.out.printf("%-25s %-14.2f %-10s %-14.2f %-14.2f\n",
                "TOTALS", totalBaseSalary, "", totalBonusPayout, totalRevisedSalary);
        System.out.println("=".repeat(82));
    }
}
