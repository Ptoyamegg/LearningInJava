package xyz.dyk.pair.pair3;

import xyz.dyk.pair.Pair;

public class PairTest3 {
}

class PairAly {
    public static boolean hasNull(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p) {
        PairAly.swapHelper(p);
    }

    private static <T> void swapHelper(Pair<T> p) {
        T first = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(first);
    }
}
