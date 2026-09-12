import java.util.Scanner;

/**
 * Case Study 1: Student Result Management System
 * Accepts name and marks of 5 subjects, calculates total & percentage,
 * displays grade and pass/fail status.
 */
public class CaseStudy1 {

    static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        int[] marks = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            marks[i] = sc.nextInt();
        }

        int total = 0;
        for (int m : marks) total += m;
        double percentage = total / 5.0;

        String grade = calculateGrade(percentage);
        boolean passed = percentage >= 50;

        System.out.println("\n===== RESULT =====");
        System.out.println("Student Name : " + name);
        System.out.println("Total Marks  : " + total + " / 500");
        System.out.println("Percentage   : " + String.format("%.2f", percentage) + "%");
        System.out.println("Grade        : " + grade);
        System.out.println("Status       : " + (passed ? "PASS" : "FAIL"));
    }
}
