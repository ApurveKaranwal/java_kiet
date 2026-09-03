class staticc{
    String name; static String college="ABC college";
    public static void main(String[] args){
        staticc s1=new staticc();
        staticc s2=new staticc();
        s1.name="Abhishek";
        s2.name="Abhi";
        System.out.println(s1.name);
        System.out.println(staticc.college);
        System.out.println(s2.name);
        System.out.println(staticc.college);
    }
}