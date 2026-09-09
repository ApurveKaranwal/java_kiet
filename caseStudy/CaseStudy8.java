/**
 * Case Study 8: Employee Inheritance
 * Employee (name, salary) as base class.
 * Manager extends Employee and additionally receives a bonus.
 */
public class CaseStudy8 {

    static class Employee {
        protected String name;
        protected double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public void display() {
            System.out.println("Name   : " + name);
            System.out.println("Salary : Rs. " + salary);
        }
    }

    static class Manager extends Employee {
        private double bonus;

        public Manager(String name, double salary, double bonus) {
            super(name, salary);
            this.bonus = bonus;
        }

        public double getTotalIncome() {
            return salary + bonus;
        }

        @Override
        public void display() {
            super.display();
            System.out.println("Bonus          : Rs. " + bonus);
            System.out.println("Total Income   : Rs. " + getTotalIncome());
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Amit Sharma", 60000);
        Manager m1 = new Manager("Priya Singh", 80000, 25000);

        System.out.println("===== EMPLOYEE =====");
        e1.display();
        System.out.println();

        System.out.println("===== MANAGER =====");
        m1.display();
    }
}
