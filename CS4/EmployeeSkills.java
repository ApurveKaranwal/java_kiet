import java.util.*;

public class EmployeeSkills {

    public static int countSkill(List<String> skills,
                                 String skill) {

        return Collections.frequency(skills, skill);
    }

    public static void main(String[] args) {

        List<String> skills = new ArrayList<>();

        skills.add("Java");
        skills.add("Python");
        skills.add("Java");
        skills.add("SQL");
        skills.add("Java");

        System.out.println("Java occurrences: "
                + countSkill(skills, "Java"));

        System.out.println("Python occurrences: "
                + countSkill(skills, "Python"));
    }
}
