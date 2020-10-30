package xyz.dyk.generics;

public class CountedObject {
    private static long counter = 0;
    private final long id = counter++;
    private long id() {return id;}
    public String toString() { return "CountedObject " + id;}
}
