package xyz.dyk.chapter2.singleton;

/**
 * Singleton with static factory
 */
public class Elvis2 {
    private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
    }

    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
    }

    //  readResolve method to preserve singleton property
    private Object readResolve() {
        //  Return thi one true Elvis and let the garbage collector
        //  take care of the Elvis impersonator.
        return INSTANCE;
    }
}
