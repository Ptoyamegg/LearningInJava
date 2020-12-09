/*
package xyz.dyk.chapter2;


import sun.misc.Cleaner;

*/
/**
 * An autocloseable class using a cleaner as a safety net
 *//*

public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create(new Object(), () -> {
    });

    //  Resource that requires cleaning. Must not refer to Room!
    private static class State implements Runnable {
        int numJunkPiles;   //  Number of junk piles in this room

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        //  Invoked by close method or cleaner

        @Override
        public void run() {
            System.out.println("Cleaning Room");
            numJunkPiles = 0;
        }
    }

    //  The state of this room, shared with our cleanable
    private final State state = new State(1);

    //  Our cleanable. Cleans the room when it's eligible for gc
//    private final Cleaner.c

    @Override
    public void close() throws Exception {

    }
}
*/
