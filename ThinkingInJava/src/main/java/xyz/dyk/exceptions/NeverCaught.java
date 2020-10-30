package xyz.dyk.exceptions;

//  Ignoring RuntimeExceptions.
//  {ThrowsException}
/**
 * @author PhotoYamEgg
 * @date 2019/8/12 - 10:10
 */
public class NeverCaught {
    static void f(){
        throw new RuntimeException("From f()");
    }
    static void g(){
        f();
    }

    public static void main(String[] args) {
        g();
    }
}
