package xyz.dyk.chapter5.generic;

import java.util.concurrent.ThreadLocalRandom;

public class GenericTest {
    @SafeVarargs
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, b);
            case 1:
                return toArray(b, c);
            case 2:
                return toArray(c, a);
        }
        throw new AssertionError();
    }

    public static void main(String[] args) {
        Object[] objects = toArray("Good", 1321, new Object());
        String[] attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(attributes);
    }
}
