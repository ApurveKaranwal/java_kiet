/**
 * Case Study 3: Employee Salary Management
 * Employee ID, Name, Basic Salary.
 * HRA = 20% of basic, DA = 10% of basic, Gross = Basic + HRA + DA.
 */
public class CaseStudy3 {

    static class Employee {
        private int empId;
        private String name;
        private double basicSalary;

        public Employee(int empId, String name, double basicSalary) {
            this.empId = empId;
            this.name = name;
            this.basicSalary = basicSalary;
        }

        public double calculateHRA() {
            return 0.20 * basicSalary;
        }

        public double calculateDA() {
            return 0.10 * basicSalary;
        }

        public double calculateGrossSalary() {
            return basicSalary + calculateHRA() + calculateDA();
        }

        public void displaySalaryDetails() {
            System.out.println("===== SALARY DETAIL =====");
            System.out.println("Employee ID   : " + empId);
            System.out.println("Name          : " + name);
            System.out.println("Basic Salary  : Rs. " + basicSalary);
            System.out.println("HRA (20%)     : Rs. " + String.format("%.2f", calculateHRA()));
            System.out.println("DA (10%)      : Rs. " + String.format("%.2f", calculateDA()));
            System.out.println("Gross Salary  : Rs. " + String.format("%.2f", calculateGrossSalary()));
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Priya Verma", 50000);
        e1.displaySalaryDetails();

        System.out.println();

        Employee e2 = new Employee(102, "Amit Kumar", 75000);
        e2.displaySalaryDetails();
    }
}
