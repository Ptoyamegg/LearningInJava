package xyz.dyk.innerclasses;

public class Outer {
    static class Inner {
    }
    public Inner getInner(){
        return new Inner();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Inner inner = outer.getInner();
    }
}
