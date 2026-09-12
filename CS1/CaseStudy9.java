import java.util.ArrayList;
import java.util.List;

/**
 * Case Study 9: University Course Registration
 * A student can register for a maximum of 5 courses.
 * registerCourse() and displayCourses().
 * Displays message if more than 5 courses are attempted.
 */
public class CaseStudy9 {

    static class Student {
        private String name;
        private List<String> courses;
        private static final int MAX_COURSES = 5;

        public Student(String name) {
            this.name = name;
            this.courses = new ArrayList<>();
        }

        public void registerCourse(String course) {
            if (courses.size() < MAX_COURSES) {
                courses.add(course);
                System.out.println("Successfully registered for: " + course);
            } else {
                System.out.println("Registration failed! Maximum limit of " + MAX_COURSES + " courses reached.");
            }
        }

        public void displayCourses() {
            System.out.println("Student: " + name);
            System.out.println("Registered Courses (" + courses.size() + "/" + MAX_COURSES + "):");
            if (courses.isEmpty()) {
                System.out.println("  No courses registered yet.");
            } else {
                for (int i = 0; i < courses.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + courses.get(i));
                }
            }
        }
    }

    public static void main(String[] args) {
        Student s = new Student("Rahul Kumar");

        s.registerCourse("Data Structures");
        s.registerCourse("Operating Systems");
        s.registerCourse("Computer Networks");
        s.registerCourse("Database Management");
        s.registerCourse("Software Engineering");
        s.registerCourse("Artificial Intelligence"); // should fail - over limit
        s.registerCourse("Machine Learning");        // should fail - over limit

        System.out.println();
        s.displayCourses();
    }
}
