package xyz.dyk.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovariance {
    public static void main(String[] args) {
        //  Wildcards allow covariance:
        List<? extends Fruit> fruits = new ArrayList<Apple>();
        //  Compile Error: can't add any type of object:
        //fruits.add(new Apple());
        //fruits.add(new Fruit());
        //fruits.add(new Object());
        fruits.add(null);   //  Legal but uninteresting
        //  We know that it returns at least Fruit:
        Fruit f = fruits.get(0);
    }
}
