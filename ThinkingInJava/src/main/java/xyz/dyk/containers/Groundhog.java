package xyz.dyk.containers;
//  Looks plausible, but doesn't work as a HashMap key.

public class Groundhog {
    protected int number;
    public Groundhog(int number) { this.number = number;}
    public String toString() { return "Groundhog #" + number;}
}
