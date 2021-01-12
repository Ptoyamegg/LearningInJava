package xyz.dyk.chapter5.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dangerous
 */
public class ListTest {
    public static void main(String[] args) {
        Object[] objects = new Long[10];
        objects[0] = "I don't fit in";
        System.out.println(objects);
        List<Long> list = new ArrayList<>();
//        list.add("I don't fit in");
        dangerous(Arrays.asList("Good1", "hello1"), Arrays.asList("132", "13123"));
        Arrays.asList("Hello world", 1211, new Object());

        oldDangerous();
    }

    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = Arrays.asList(42);
        Object[] objects = stringLists;
        objects[0] = intList;
        String s = stringLists[0].get(0);
    }

    static void oldDangerous() {
//        List<String>[] stringLists = new List<Integer>[1];
        List<String>[] stringLists = new List[1];
        List<Integer> intList = Arrays.asList(42);
        Object[] objects = stringLists;
        objects[0] = intList;
        String s = stringLists[0].get(0);
    }
}
