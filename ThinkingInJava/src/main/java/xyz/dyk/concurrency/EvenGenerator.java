package xyz.dyk.concurrency;
//  When threads collide.

public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    public int next() {     //  Danger point here!
        ++currentEvenValue;
        Thread.yield();     //  Cause failure faster
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
