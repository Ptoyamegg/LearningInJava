package xyz.dyk.generics;

public class Holder<T> {
    private T value;
    public Holder() {}
    public Holder(T item) { value = item;}
    public void set(T item) { value = item;}
    public T get() { return value;}
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> appleHolder = new Holder<>(new Apple());

        System.out.println("1: " + appleHolder);

        Apple apple = appleHolder.get();

        System.out.println("2: " + apple);

        appleHolder.set(apple);

        System.out.println("3: " + appleHolder);

        //  Holder<Fruit> fruitHolder = appleHolder;    //  Cannot upcast
        Holder<? extends Fruit> fruitHolder = appleHolder;  //  OK

        System.out.println("4: " + appleHolder);
        System.out.println("5: " + fruitHolder);

        Fruit fruit = fruitHolder.get();
        System.out.println("6: " + fruit);

        apple = (Apple)fruitHolder.get();   //  Returns 'Object'
        System.out.println("7: " + apple);

        try {
            Orange orange = (Orange)fruitHolder.get();  //  No waring (ClassCastException)
        }catch (Exception e) {
            System.out.println(e);
        }
        //fruitHolder.set(new Apple());
        //fruitHolder.set(new Fruit());

        System.out.println("8: " + appleHolder);
        System.out.println("9: " + fruitHolder);

        System.out.println(fruitHolder.equals(appleHolder));    //  NO!!!
    }
}
