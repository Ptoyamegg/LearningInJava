package xyz.dyk.concurrency;
//  Adding more Threads.

public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Wait for LiftOff()");
    }
}
