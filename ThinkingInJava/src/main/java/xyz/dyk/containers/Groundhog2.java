package xyz.dyk.containers;
//  A class that's used as a key in HashMap must override hashCode() and equals().

public class Groundhog2 extends Groundhog{
    public Groundhog2(int n) { super(n);}
    public int hashCode() {return number;}
    public boolean equals(Object o) {
        return o instanceof Groundhog2 && (number == ((Groundhog2) o).number);
    }
}
