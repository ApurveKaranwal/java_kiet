class Exceptiondemo {
    public static void main(String[] args){
        try{
            int result=10/0;
        }
        catch(ArithmeticException e){
            System.out.println("Cannot divide by zero");
            System.out.println(e); // where e is the variable which tells that whats error
        }
    }
}
