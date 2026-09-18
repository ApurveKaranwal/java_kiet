import java.util.*;

public class CourseAnalysis {

    public static Set<Integer> commonStudents(
            Set<Integer> javaStudents,
            Set<Integer> pythonStudents) {

        Set<Integer> common = new LinkedHashSet<>(javaStudents);
        common.retainAll(pythonStudents);
        return common;
    }

    public static Set<Integer> allStudents(
            Set<Integer> javaStudents,
            Set<Integer> pythonStudents) {

        Set<Integer> all = new LinkedHashSet<>(javaStudents);
        all.addAll(pythonStudents);
        return all;
    }

    public static Set<Integer> onlyJava(
            Set<Integer> javaStudents,
            Set<Integer> pythonStudents) {

        Set<Integer> only = new LinkedHashSet<>(javaStudents);
        only.removeAll(pythonStudents);
        return only;
    }

    public static void main(String[] args) {

        Set<Integer> javaStudents =
                new HashSet<>(
                    Arrays.asList(101, 102, 103, 104));

        Set<Integer> pythonStudents =
                new HashSet<>(
                    Arrays.asList(103, 104, 105, 106));

        System.out.println("Both: "
                + commonStudents(javaStudents, pythonStudents));

        System.out.println("Either: "
                + allStudents(javaStudents, pythonStudents));

        System.out.println("Only Java: "
                + onlyJava(javaStudents, pythonStudents));
    }
}
