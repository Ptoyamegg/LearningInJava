package xyz.dyk.chapter5.generic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SafetyGenericTest {
    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }
}
