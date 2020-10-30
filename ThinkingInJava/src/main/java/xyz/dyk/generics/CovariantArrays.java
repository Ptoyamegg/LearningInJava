package xyz.dyk.generics;

class Fruit {}

class Apple extends Fruit {}

class Jonathan extends Apple {}

class Orange extends Fruit {}

public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();    //  OK
        fruits[1] = new Jonathan(); //  OK
        //  Runtime type is Apple[], not Fruit[] or Orange[]:
        try {
            //  Compiler allows you to add Fruit:
            fruits[0] = new Fruit();    //  ArrayStoreException
        }catch (Exception e) {
            System.out.println(e);
        }
        try {
            //  Compiler allows you to add Orange:
            fruits[0] = new Orange();    //  ArrayStoreException
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
