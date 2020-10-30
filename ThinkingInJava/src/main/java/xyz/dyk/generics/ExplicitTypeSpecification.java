package xyz.dyk.generics;

import net.mindview.util.New;
import xyz.dyk.typeinfo.pets.Person;
import xyz.dyk.typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

public class ExplicitTypeSpecification {
    static void f(Map<Person, List<? extends Pet>> petPeople) {}

    public static void main(String[] args) {
        f(New.<Person, List<? extends Pet>>map());
    }
}
