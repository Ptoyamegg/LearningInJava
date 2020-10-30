package xyz.dyk.generics;

public class Holder2 {
    private Object o;
    public Holder2(Object o) {this.o = o;}
    public void set(Object o) { this.o = o;}
    public Object get() { return o;}

    public static void main(String[] args) {
        Holder2 h2 = new Holder2(new Automobile());
        Automobile a = (Automobile)h2.get();
        h2.set("Not an Automobile");
        String s = (String)h2.get();
        h2.set(1);  //  Autoboxes to Integer
        Integer i = (Integer)h2.get();
    }
}
