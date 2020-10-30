package xyz.dyk.holding;
// Simple container example(produces compiler warnings).
// {ThrowsException}

import java.util.ArrayList;

class Apple{
    private static long counter;
    private final long id = counter++;
    public long id(){return id;}
}

class Orange{}

public class ApplesAndOrangesWithoutGenerics {
    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for (int i = 0; i < 3; i++)
            apples.add(new Apple());
        // Compile-time error:
        //apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++)
            System.out.println(apples.get(i).id());
        // Using foreach:
        for (Apple a: apples)
            System.out.println(a.id());
    }
    /*
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++)
            apples.add(new Apple());
        // Not prevented from adding an Orange to apples:
        //apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++){
            long id = ((Apple)apples.get(i)).id();
            System.out.println(id);
        }
        // Orange is detected only at run time
    }*/
}
