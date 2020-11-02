package xyz.dyk.methods;

import java.lang.reflect.Method;
import java.time.LocalDate;

public class MethodTableTest {
    public static void main(String[] args) throws Exception {
        Method square = MethodTableTest.class.getDeclaredMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
//        LocalDate
//        String
    }

    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) {
        //  print out the method as table header
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
