package xyz.dyk.generics;

import net.mindview.util.New;
import xyz.dyk.typeinfo.pets.Person;
import xyz.dyk.typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

public class SimplePets {
    public static void main(String[] args) {
        Map<Person, List<? extends Pet>> petPeople = New.map();
        //  Rest of the code is the same...
    }
}
