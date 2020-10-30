package xyz.dyk.exceptions;
//  Overridden methods may throw only the xyz.dyk.exceptions
//  specified in their base-class versions. or xyz.dyk.exceptions
//  derived from the base-class xyz.dyk.exceptions.

/**
 * @author PhotoYamEgg
 * @date 2019/8/12 - 11:04
 */
class BaseballException extends Exception{}

class Foul extends BaseballException{}

class Strike extends BaseballException{}

abstract class Inning{
    public Inning()throws BaseballException{}
    public void event() throws BaseballException{
        //  Doesn't actually have to throw anything
    }
    public abstract void atBat()throws Strike,Foul;
    public void walk(){}    //  Throws no checked xyz.dyk.exceptions
}

class StormException extends Exception{}

class RainedOut extends StormException{}

class PopFoul extends Foul{}

interface Strom{
    public void event()throws RainedOut;
    public void rainHard()throws RainedOut;
}

public class StormyInning extends Inning implements Strom{
    //  Ok to add new xyz.dyk.exceptions for constructors, but you
    //  must deal with the base constructor xyz.dyk.exceptions:

    public StormyInning()
        throws RainedOut, BaseballException {}
    public StormyInning(String s)
        throws Foul,BaseballException{}
    //  Regular methods must conform to base class:
    //! void walk() throws PopFoul{}    //Compile error
    //  Interface CANNOT add xyz.dyk.exceptions to existing
    //  methods from the base class:
//!   public void event()throws RainedOut{}
    //  If the method doesn't already exist in the
    //  base class. the exception is OK:
    public void rainHard() throws RainedOut{}
    //  You can choose to not throw any exception,
    //  even if the base version does:
    public void event(){}
    //  Overridden methods can throw inherited xyz.dyk.exceptions:
    public void atBat() throws PopFoul{}

    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        }catch (PopFoul e){
            System.out.println("Pop foul");
        }catch (RainedOut e){
            System.out.println("Rained out");
        }catch (BaseballException e){
            System.out.println("Generic baseball exception");
        }
        //  Strike not thrown in derived version.
        try {
            //  What happens if you upcast?
            Inning i = new StormyInning();
            i.atBat();
            //  base-class version of the method:
        }catch (Strike e){
            System.out.println("Strike");
        }catch (Foul e){
            System.out.println("Foul");
        }catch (RainedOut e){
            System.out.println("Rained out");
        }catch (BaseballException e){
            System.out.println("Generic baseball exception");
        }
    }
}
