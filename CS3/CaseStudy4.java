import java.util.ArrayList;
import java.util.Arrays;

/**
 * Case Study 4: Student Marks & Grade Evaluation System
 * 
 * A college maintains the marks of students using an ArrayList.
 * For each student, the system calculates total marks and percentage (5 subjects, max 100 each).
 * Grading rules:
 * - Percentage >= 90 -> Grade A
 * - Percentage >= 75 -> Grade B
 * - Percentage >= 60 -> Grade C
 * - Percentage >= 50 -> Grade D
 * - Percentage < 50  -> Fail
 */
public class CaseStudy4 {

    static class Student {
        private String name;
        private int rollNumber;
        private ArrayList<Integer> marks; // Marks for 5 subjects

        public Student(int rollNumber, String name, ArrayList<Integer> marks) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.marks = marks;
        }

        public int calculateTotal() {
            int total = 0;
            for (int m : marks) {
                total += m;
            }
            return total;
        }

        public double calculatePercentage() {
            return calculateTotal() / 5.0; // 5 subjects, max 100 each = 500 total
        }

        public String calculateGrade() {
            double pct = calculatePercentage();
            if (pct >= 90) return "Grade A";
            if (pct >= 75) return "Grade B";
            if (pct >= 60) return "Grade C";
            if (pct >= 50) return "Grade D";
            return "Fail";
        }

        public void displayReport() {
            System.out.printf("%-10d %-16s %-20s %-8d %-12.2f %-10s\n",
                    rollNumber, name, marks.toString(), calculateTotal(), calculatePercentage(), calculateGrade());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 4: STUDENT MARKS AND GRADE EVALUATION =====");

        ArrayList<Student> students = new ArrayList<>();

        // Adding students with marks across 5 subjects
        students.add(new Student(101, "Ananya Sharma", new ArrayList<>(Arrays.asList(92, 95, 88, 94, 91)))); // >= 90 (Grade A)
        students.add(new Student(102, "Rohan Verma",  new ArrayList<>(Arrays.asList(78, 82, 74, 80, 76)))); // >= 75 (Grade B)
        students.add(new Student(103, "Kavya Gupta",  new ArrayList<>(Arrays.asList(65, 62, 70, 58, 68)))); // >= 60 (Grade C)
        students.add(new Student(104, "Devendra Rao", new ArrayList<>(Arrays.asList(52, 54, 50, 56, 51)))); // >= 50 (Grade D)
        students.add(new Student(105, "Aryan Mishra", new ArrayList<>(Arrays.asList(42, 38, 45, 48, 40)))); // < 50 (Fail)

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-10s %-16s %-20s %-8s %-12s %-10s\n",
                "Roll No", "Name", "Marks (5 Subs)", "Total", "Percentage", "Grade");
        System.out.println("=".repeat(80));

        for (Student s : students) {
            s.displayReport();
        }
        System.out.println("=".repeat(80));
    }
}
