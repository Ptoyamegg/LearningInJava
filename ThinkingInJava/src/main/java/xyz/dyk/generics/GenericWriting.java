package xyz.dyk.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericWriting {
    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }
    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruits = new ArrayList<Fruit>();
    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruits, new Apple());    //  Error?
//        writeExact(apples, new Fruit());    //  Error!
//        writeExact(fruits, new Fruit());
        //  Incompatible type: found Fruit, required Apple
    }
    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }
    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruits, new Apple());
    }

    public static void main(String[] args) {
        f1();f2();
    }
}
// have some question ! 2019年12月6日 22:32:37