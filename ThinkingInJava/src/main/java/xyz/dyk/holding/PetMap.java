package xyz.dyk.holding;

import xyz.dyk.typeinfo.pets.Cat;
import xyz.dyk.typeinfo.pets.Dog;
import xyz.dyk.typeinfo.pets.Pet;

import java.util.HashMap;
import java.util.Map;

public class PetMap {
    public static void main(String[] args) {
        Map<String,Pet> petMap = new HashMap<String, Pet>();
        petMap.put("My Cat",new Cat("Molly"));
        petMap.put("My Dog",new Dog("Molly"));
        petMap.put("My Hamster",new Cat("Molly"));
        System.out.println(petMap);
        Pet dog  = petMap.get("My Dog");
        System.out.println(dog);
        System.out.println(petMap.containsKey("My Dog"));
        System.out.println(petMap.containsValue(dog));
    }
}
