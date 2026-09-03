class Employee {
    String name;
    int age;

    // Parameterized constructor (2 parameters: name, age)
    Employee(String empName, int empAge) {
        name = empName;
        age = empAge;
    }

    void display() {
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        // Invoking the 2-parameter constructor
        Employee emp = new Employee("Alice", 28);
        emp.display();
    }
}
