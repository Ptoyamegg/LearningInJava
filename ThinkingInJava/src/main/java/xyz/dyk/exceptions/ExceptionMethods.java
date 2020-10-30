package xyz.dyk.exceptions;
//  Demonstrating the Exception Methods.

/**
 * @author PhotoYamEgg
 * @date 2019/8/11 - 21:18
 */
public class ExceptionMethods {
    public static void main(String[] args) {
        try {
            throw new Exception("MyException");
        }catch (Exception e){
            System.out.println("Caught Exception ");
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("getLocalizedMessage(): " + e.getLocalizedMessage());
            System.out.println("toString(): " + e);
            System.out.println("printStackTrace(): ");
            e.printStackTrace(System.out);
        }
    }
}
