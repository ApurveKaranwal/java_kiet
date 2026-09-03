class Student{
    String name;
    int age;
    void display(){
    System.out.println("name"+name);
    System.out.println("age"+age);
    }
    public static void main(String[] args){
        Student s1=new Student();
        s1.name ="Abhishek";
        s1.age=20;
        s1.display();
    }
}