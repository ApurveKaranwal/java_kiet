import java.util.*;

public class CommonStudents {

    public static Set<Integer> findCommonStudents(
            Set<Integer> event1,
            Set<Integer> event2) {

        Set<Integer> common = new LinkedHashSet<>(event1);
        common.retainAll(event2);
        return common;
    }

    public static void main(String[] args) {

        Set<Integer> event1 =
                new HashSet<>(Arrays.asList(101, 102, 103, 104));

        Set<Integer> event2 =
                new HashSet<>(Arrays.asList(103, 104, 105, 106));

        Set<Integer> common =
                findCommonStudents(event1, event2);

        System.out.println("Common Students: " + common);
    }
}
