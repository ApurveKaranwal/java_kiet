class Hello {
    int a, b, sum;

    void calculateSum() {
        a = 5;
        b = 10;
        sum = a + b;
        System.out.println("Sum: " + sum);
    }

    public static void main(String[] args) {
        Hello obj = new Hello();
        obj.calculateSum();
    }
}
