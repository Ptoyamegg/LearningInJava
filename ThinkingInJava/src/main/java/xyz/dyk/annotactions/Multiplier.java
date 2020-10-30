package xyz.dyk.annotactions;
//  APT-based annotation processing.

@ExtractInterface("IMultiplier")
public class Multiplier {
    private int add(int x, int y) { return x + y; }
    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++)
            total = add(total, y);
        return total;
    }

    public static void main(String[] args) {
        Multiplier m = new Multiplier();
        System.out.println("11*16 = " + m.multiply(11, 16));
    }
}
