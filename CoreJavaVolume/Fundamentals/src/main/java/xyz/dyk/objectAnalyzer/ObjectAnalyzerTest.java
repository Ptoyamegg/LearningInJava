package xyz.dyk.objectAnalyzer;

import java.util.ArrayList;

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
