package xyz.dyk.generics;

import net.mindview.util.New;
import xyz.dyk.typeinfo.pets.Person;
import xyz.dyk.typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

public class LimitsOfInterference {
    static void f(Map<Person, List<? extends Pet>> petPeople) {}

    public static void main(String[] args) {
        f(New.map());// 可以识别。。。
    }
}
