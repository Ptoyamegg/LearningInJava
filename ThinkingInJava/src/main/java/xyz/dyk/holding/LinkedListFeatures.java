package xyz.dyk.holding;

import xyz.dyk.typeinfo.pets.Hamster;
import xyz.dyk.typeinfo.pets.Pet;
import xyz.dyk.typeinfo.pets.Pets;
import xyz.dyk.typeinfo.pets.Rat;

import java.util.LinkedList;

public class LinkedListFeatures {
    public static void main(String[] args) {
        LinkedList<Pet> pets =
                new LinkedList<Pet>(Pets.arrayList(5));
        System.out.println(pets);
        //  Idnetical:
        System.out.println("pets.getFirst(): " + pets.getFirst());
        System.out.println("pets.element(): " + pets.element());
        //  Only differs in empty-list behavior:
        System.out.println("pets.peek(): " + pets.peek());  //Return first element.If empty, return null
        //  Identical: remove and return the first element:
        System.out.println("pets.remove(): " + pets.remove());
        System.out.println("pets.removeFirst(): " + pets.removeFirst());
        //  Only differs in empty-list behavior:
        System.out.println("pets.poll(): " + pets.poll());//Return last element.If empty, return null
        System.out.println(pets);
        pets.addFirst(new Rat());
        System.out.println("After addFirst(): " + pets);
        pets.offer(Pets.randomPet());
        System.out.println("After offer(): " + pets);
        pets.add(Pets.randomPet());
        System.out.println("After add(): " + pets);
        pets.addLast(new Hamster());
        System.out.println("After addLast(): " + pets);
        System.out.println("pets.removeLast(): " + pets.removeLast());
    }
}

