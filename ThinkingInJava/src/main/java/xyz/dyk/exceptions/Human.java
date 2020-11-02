package xyz.dyk.exceptions;
//  Catching exception hierarchies.

/**
 * @author PhotoYamEgg
 * @date 2019/8/14 - 21:38
 */
class Annoyance extends Exception{}

class Sneeze extends Annoyance{}

class OtherSneeze extends Annoyance{}

public class Human {
    public static void main(String[] args) {
        //  Catch the exact type:
        try {
            throw new Sneeze();
        }catch (Sneeze e){
            System.out.println("Caught Sneeze");
        }catch (Annoyance a){
            System.out.println("Caught Annoyance");
        }
        //  Catch the base type:
        try {
            throw new Sneeze();
        }catch (Annoyance a){
            System.out.println("Caught Annoyance");
        }
    }
}
