package xyz.dyk.generics;

public class GenericHolder<T> {
    private T obj;
    public void set(T t) { obj = t;}
    public T get() { return obj;}

    public static void main(String[] args) {
        GenericHolder<String> holder = new GenericHolder<>();
        holder.set("Item");
        String s = holder.get();
    }
}
