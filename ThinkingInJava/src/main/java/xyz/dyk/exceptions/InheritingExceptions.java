package xyz.dyk.exceptions;

/**
 * @author PhotoYamEgg
 * @date 2019/8/11 - 20:39
 */

class SimpleException extends Exception{}

public class InheritingExceptions {
    public void f() throws SimpleException{
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e){
            System.out.println("Caught it!");
        }
    }
}
