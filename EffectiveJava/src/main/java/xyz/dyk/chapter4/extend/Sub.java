package xyz.dyk.chapter4.extend;

import java.time.Instant;
import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public final class Sub extends Super {
    private final Instant instant;

    public Sub() {
        instant = Instant.now();
    }

    //  Overriding method invoked by superclass constructor
    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }

    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        //  The diamond operator is only legal here in Java 9 and later
        //  If you're using an earlier release, specify <Integer>
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            @Override
            public int size() {
                return a.length;
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element;
                return oldVal;
            }
        };
    }
}