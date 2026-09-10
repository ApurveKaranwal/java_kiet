import java.util.Scanner;

/**
 * Case Study 3: Student Examination Result
 * A university wants to calculate a student's average marks.
 * Handles:
 *  1. Marks outside the range 0–100 (MarksOutOfRangeException)
 *  2. Division by zero when no subjects are entered (DivisionByZeroException)
 *  3. Invalid input (InvalidInputException)
 */
public class StudentExaminationResult {

    // Custom Exception Classes
    public static class MarksOutOfRangeException extends Exception {
        public MarksOutOfRangeException(String message) {
            super(message);
        }
    }

    public static class DivisionByZeroException extends Exception {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void calculateAverage() throws MarksOutOfRangeException, DivisionByZeroException, InvalidInputException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int subjects;
        try {
            subjects = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Number of subjects must be a valid integer.");
        }

        if (subjects <= 0) {
            throw new DivisionByZeroException("Division by zero: Cannot calculate average when number of subjects is " + subjects + " (must be at least 1).");
        }

        double[] marks = new double[subjects];
        for (int i = 0; i < subjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            try {
                marks[i] = Double.parseDouble(sc.next());
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid input: Marks must be a valid number.");
            }

            if (marks[i] < 0 || marks[i] > 100) {
                throw new MarksOutOfRangeException("Marks out of range: Marks must be between 0 and 100 (entered: " + marks[i] + ").");
            }
        }

        double total = 0;
        for (double m : marks) {
            total += m;
        }
        double average = total / subjects;

        System.out.println("\n============= STUDENT REPORT CARD =============");
        System.out.println("Total Subjects  : " + subjects);
        for (int i = 0; i < subjects; i++) {
            System.out.println("  Subject " + (i + 1) + "     : " + String.format("%.2f", marks[i]) + " / 100");
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Total Marks     : " + String.format("%.2f", total) + " / " + (subjects * 100));
        System.out.println("Average Marks   : " + String.format("%.2f", average) + "%");
        System.out.println("Status          : " + (average >= 40.0 ? "PASSED" : "FAILED"));
        System.out.println("===============================================");
    }

    public static void main(String[] args) {
        try {
            calculateAverage();
        } catch (MarksOutOfRangeException | DivisionByZeroException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}