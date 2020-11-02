package xyz.dyk.objectAnalyzer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            squares.add(i*i);
//        squares.get(-1);
//        squares.listIterator().set(10);
//        List<Integer> integers = squares.subList(1, 2);
//        integers.addAll(null);
//        squares.removeIf(Predicate.isEqual(1));
//        Object newArray = Array.newInstance(ObjectAnalyzerTest.class,1);
//        Arrays.copyOf()
//        int length = Array.getLength();
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
