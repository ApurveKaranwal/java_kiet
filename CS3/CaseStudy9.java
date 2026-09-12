import java.util.Vector;

/**
 * Case Study 9: University Student Marks and Classification System
 * 
 * A university stores student marks in a Vector.
 * Classification:
 * - Marks >= 90 -> Outstanding
 * - Marks >= 75 -> Distinction
 * - Marks >= 60 -> First Division
 * - Marks >= 50 -> Second Division
 * - Marks < 50  -> Fail
 * 
 * Aggregates:
 * - Total number of students
 * - Number of passed students
 * - Number of failed students
 * - Class average
 */
public class CaseStudy9 {

    public static String getClassification(int marks) {
        if (marks >= 90) return "Outstanding";
        if (marks >= 75) return "Distinction";
        if (marks >= 60) return "First Division";
        if (marks >= 50) return "Second Division";
        return "Fail";
    }

    public static void main(String[] args) {
        System.out.println("===== CASE STUDY 9: UNIVERSITY STUDENT MARKS & PERFORMANCE (VECTOR) =====");

        // Student marks stored in a Vector
        Vector<Integer> marksVector = new Vector<>();

        // Add sample student marks
        marksVector.add(94); // Outstanding
        marksVector.add(81); // Distinction
        marksVector.add(67); // First Division
        marksVector.add(54); // Second Division
        marksVector.add(42); // Fail
        marksVector.add(88); // Distinction
        marksVector.add(72); // First Division
        marksVector.add(48); // Fail
        marksVector.add(91); // Outstanding
        marksVector.add(59); // Second Division

        int totalStudents = marksVector.size();
        int passedCount = 0;
        int failedCount = 0;
        int totalMarks = 0;

        System.out.println("\n" + "=".repeat(60));
        System.out.printf("%-12s %-12s %-20s %-10s\n", "Student ID", "Marks", "Classification", "Status");
        System.out.println("=".repeat(60));

        for (int i = 0; i < marksVector.size(); i++) {
            int marks = marksVector.get(i);
            String classification = getClassification(marks);
            boolean passed = marks >= 50;

            if (passed) {
                passedCount++;
            } else {
                failedCount++;
            }
            totalMarks += marks;

            System.out.printf("STD-%03d      %-12d %-20s %-10s\n",
                    (i + 1), marks, classification, (passed ? "PASS" : "FAIL"));
        }

        double classAverage = (totalStudents > 0) ? (double) totalMarks / totalStudents : 0.0;

        System.out.println("=".repeat(60));
        System.out.println("\n--- UNIVERSITY SUMMARY REPORT ---");
        System.out.println("Total Number of Students : " + totalStudents);
        System.out.println("Number of Passed Students: " + passedCount);
        System.out.println("Number of Failed Students: " + failedCount);
        System.out.printf("Class Average Marks      : %.2f\n", classAverage);
        System.out.println("=".repeat(60));
    }
}
