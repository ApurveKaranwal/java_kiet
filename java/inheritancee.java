class inheritance{
    String name="Chodu";
    int roll;
    void display(){
        System.out.println(name);

    }

}
class student extends inheritance{
    int id;
    String branch;

    public static void main(String[] args){
        student s=new student();
        s.display();
        s.roll=69;
        s.name="Abhishek Malhotra";
        s.id=100;
        s.branch="Chudi hui IT-A";
        System.out.println(s.name);
        System.out.println(s.roll);
        System.out.println(s.branch);
        System.out.println(s.id);
        
    }
}