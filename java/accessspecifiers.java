
class accessspecifiers {

    private String name;
    int age;
    protected String college;
    public String city;

    void display() {
        System.out.println(name);
        System.out.println(age);
        System.out.println(college);
        System.out.println(city);
    }

    public static void main(String[] args) {

        accessspecifiers s = new accessspecifiers();

        s.name = "Abhishek";
        s.age = 20;
        s.college = "ABC College";
        s.city = "Delhi";

        s.display();
    }
}

