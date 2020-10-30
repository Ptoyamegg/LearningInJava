package xyz.dyk.generics;

import java.util.Arrays;
import java.util.List;

public class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> fruits = Arrays.asList(new Apple());
        Apple a = (Apple)fruits.get(0); //  No warning
        fruits.contains(new Apple());   //  Argument is 'Object'
        fruits.indexOf(new Apple());    //  Argument is 'Object'
    }
}
