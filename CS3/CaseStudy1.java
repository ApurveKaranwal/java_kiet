import java.util.ArrayList;

/**
 * Case Study 1: Student Attendance Management
 * 
 * A teacher wants to maintain the names of students present in a class.
 * The system supports:
 * 1. Adding student names.
 * 2. Displaying all students.
 * 3. Searching for a student.
 * 4. Removing a student.
 * 5. Displaying the number of students present.
 */
public class CaseStudy1 {

    private ArrayList<String> attendanceList;

    public CaseStudy1() {
        attendanceList = new ArrayList<>();
    }

    // 1. Add student name
    public void addStudent(String name) {
        if (name != null && !name.trim().isEmpty()) {
            attendanceList.add(name.trim());
            System.out.println("Added: \"" + name.trim() + "\" to attendance list.");
        } else {
            System.out.println("Invalid student name.");
        }
    }

    // 2. Display all students
    public void displayStudents() {
        System.out.println("\n--- Present Students List ---");
        if (attendanceList.isEmpty()) {
            System.out.println("No students present.");
            return;
        }
        for (int i = 0; i < attendanceList.size(); i++) {
            System.out.println((i + 1) + ". " + attendanceList.get(i));
        }
    }

    // 3. Search for a student (case-insensitive)
    public boolean searchStudent(String name) {
        for (int i = 0; i < attendanceList.size(); i++) {
            if (attendanceList.get(i).equalsIgnoreCase(name.trim())) {
                System.out.println("Search: \"" + name + "\" is PRESENT (Position: " + (i + 1) + ").");
                return true;
            }
        }
        System.out.println("Search: \"" + name + "\" is NOT FOUND in attendance list.");
        return false;
    }

    // 4. Remove a student
    public boolean removeStudent(String name) {
        for (int i = 0; i < attendanceList.size(); i++) {
            if (attendanceList.get(i).equalsIgnoreCase(name.trim())) {
                String removed = attendanceList.remove(i);
                System.out.println("Removed: \"" + removed + "\" from attendance list.");
                return true;
            }
        }
        System.out.println("Cannot remove: \"" + name + "\" is not in attendance list.");
        return false;
    }

    // 5. Display the number of students present
    public int getPresentCount() {
        return attendanceList.size();
    }

    public static void main(String[] args) {
        CaseStudy1 manager = new CaseStudy1();

        System.out.println("===== CASE STUDY 1: STUDENT ATTENDANCE MANAGEMENT =====");

        // 1. Add student names
        System.out.println("\n[Action] Adding students to attendance...");
        manager.addStudent("Aarav Sharma");
        manager.addStudent("Diya Patel");
        manager.addStudent("Rohan Gupta");
        manager.addStudent("Sneha Verma");
        manager.addStudent("Vikram Singh");

        // 2. Display all students
        manager.displayStudents();

        // 5. Display total count
        System.out.println("\nTotal students present: " + manager.getPresentCount());

        // 3. Search for students
        System.out.println("\n[Action] Searching for students...");
        manager.searchStudent("Rohan Gupta");
        manager.searchStudent("Pooja Mehta");

        // 4. Remove a student
        System.out.println("\n[Action] Removing students...");
        manager.removeStudent("Diya Patel");
        manager.removeStudent("Karan Johar"); // Not present

        // Display updated list and count
        manager.displayStudents();
        System.out.println("\nUpdated total students present: " + manager.getPresentCount());
    }
}
