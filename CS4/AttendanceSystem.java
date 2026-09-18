import java.util.*;

public class AttendanceSystem {

    public static void markAttendance(Set<Integer> attendance,
                                       int studentId) {
        attendance.add(studentId);
    }

    public static boolean isPresent(Set<Integer> attendance,
                                    int studentId) {
        return attendance.contains(studentId);
    }

    public static int getAttendanceCount(Set<Integer> attendance) {
        return attendance.size();
    }

    public static void displayAttendance(Set<Integer> attendance) {
        int i = 0;
        for (int id : attendance) {
            System.out.print(id);
            if (++i < attendance.size()) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Set<Integer> attendance = new LinkedHashSet<>();

        markAttendance(attendance, 105);
        markAttendance(attendance, 102);
        markAttendance(attendance, 108);
        markAttendance(attendance, 105);
        markAttendance(attendance, 101);

        displayAttendance(attendance);

        System.out.println("Student 108 present: "
                + isPresent(attendance, 108));

        System.out.println("Total Present: "
                + getAttendanceCount(attendance));
    }
}
