package xyz.dyk.generics;

class Manipulator<T> {
    private T obj;
    public Manipulator(T x) { obj = x;}
    public void manipulate() {
//        obj.f();
        System.out.println("Error: cannot find symbol: method f()");
    }
}

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        Manipulator2<HasF> manipulator2 = new Manipulator2<>(hf);
        Manipulator2<HasF> manipulator3 = new Manipulator2<>(hf);
        manipulator.manipulate();
        manipulator2.manipulate();
        manipulator3.manipulate();
    }
}
