package xyz.dyk.pair.pair2;

import xyz.dyk.pair.Pair;

import java.time.LocalDate;

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),    //  G. Hopper
                LocalDate.of(1815, 12, 10),   //  A. Lovelace
                LocalDate.of(1903, 12, 2),    //  J. von Neumann
                LocalDate.of(1910, 6, 22),    //  K. Zuse
        };
        Pair<LocalDate> mm = ArrayAly.minmax(birthdays);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}


class ArrayAly {
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (T t : a) {
            if (min.compareTo(t) > 0) min = a[0];
            if (max.compareTo(t) < 0) max = a[0];
        }
        return new Pair<>(min, max);
    }
}