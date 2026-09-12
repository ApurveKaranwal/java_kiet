class pdemo {
    int a, b;
    int res;

    void sum() {
        res = a + b;
        System.out.println(res);
    }
}

public class hello extends pdemo {
    int c = 9, d = 8;

    @Override
    void sum() {
        res = c + d;
        System.out.println(res);
    }

    public static void main(String[] args) {
        hello obj = new hello();
        obj.sum();
    }
}