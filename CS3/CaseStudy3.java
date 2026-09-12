import java.util.ArrayList;
import java.util.Collections;

/**
 * Case Study 3: Employee Salary List
 * 
 * A company stores employee salaries using an ArrayList<Integer>.
 * The program:
 * 1. Stores salaries.
 * 2. Displays all salaries.
 * 3. Finds the highest salary.
 * 4. Calculates the total salary.
 * 5. Calculates the average salary.
 */
public class CaseStudy3 {

    private ArrayList<Integer> salaries;

    public CaseStudy3() {
        salaries = new ArrayList<>();
    }

    // 1. Store salary
    public void addSalary(int salary) {
        if (salary > 0) {
            salaries.add(salary);
            System.out.println("Recorded Salary: Rs. " + salary);
        } else {
            System.out.println("Invalid salary: " + salary);
        }
    }

    // 2. Display all salaries
    public void displaySalaries() {
        System.out.println("\n--- All Employee Salaries ---");
        if (salaries.isEmpty()) {
            System.out.println("No salary records found.");
            return;
        }
        for (int i = 0; i < salaries.size(); i++) {
            System.out.println("Employee " + (i + 1) + ": Rs. " + salaries.get(i));
        }
        System.out.println("Total employee records: " + salaries.size());
    }

    // 3. Find highest salary
    public int findHighestSalary() {
        if (salaries.isEmpty()) {
            System.out.println("No salaries available.");
            return 0;
        }
        return Collections.max(salaries);
    }

    // 4. Calculate total salary
    public int calculateTotalSalary() {
        int total = 0;
        for (int s : salaries) {
            total += s;
        }
        return total;
    }

    // 5. Calculate average salary
    public double calculateAverageSalary() {
        if (salaries.isEmpty()) {
            return 0.0;
        }
        return (double) calculateTotalSalary() / salaries.size();
    }

    public static void main(String[] args) {
        CaseStudy3 salaryList = new CaseStudy3();

        System.out.println("===== CASE STUDY 3: EMPLOYEE SALARY LIST =====");

        // 1. Store salaries
        System.out.println("\n[Action] Storing employee salaries...");
        salaryList.addSalary(45000);
        salaryList.addSalary(78000);
        salaryList.addSalary(92000);
        salaryList.addSalary(56000);
        salaryList.addSalary(64000);
        salaryList.addSalary(85000);

        // 2. Display all salaries
        salaryList.displaySalaries();

        // 3. Find highest salary
        int highest = salaryList.findHighestSalary();
        System.out.println("\n--- Salary Statistics ---");
        System.out.println("Highest Salary : Rs. " + highest);

        // 4. Calculate total salary
        int total = salaryList.calculateTotalSalary();
        System.out.println("Total Salary   : Rs. " + total);

        // 5. Calculate average salary
        double average = salaryList.calculateAverageSalary();
        System.out.println("Average Salary : Rs. " + String.format("%.2f", average));
    }
}
