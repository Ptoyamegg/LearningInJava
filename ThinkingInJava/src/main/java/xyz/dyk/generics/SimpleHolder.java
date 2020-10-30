package xyz.dyk.generics;

public class SimpleHolder {
    private Object obj;
    private void set(Object obj) { this.obj = obj;}
    public Object get() { return obj;}

    public static void main(String[] args) {
        SimpleHolder holder = new SimpleHolder();
        holder.set("Item");
        String s = (String)holder.get();
    }
}
