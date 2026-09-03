class methods{
    void greet(String name ){
        System.out.println("hello" + name);
    }
    void add(int a, int b){
        int sum=a+b;
        System.out.println("Sum" +sum);

    }
    public static void main(String[] args){
        methods s1=new methods();
        s1.greet("Abhishek");
        s1.add(10, 20);

    }
}