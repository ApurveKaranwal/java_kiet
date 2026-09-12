//employee name constructor
//make 2 program, one with no parameter, other with 2 parameter(name, age)
//one with no argument constructor, other with argument constructor

class Employee {
    String name;
    int age;

    // No-argument (default) constructor
    Employee() {
        name = "Unknown";
        age = 0;
    }

    void display() {
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        // Invoking the no-argument constructor
        Employee emp = new Employee();
        emp.display();
    }
}