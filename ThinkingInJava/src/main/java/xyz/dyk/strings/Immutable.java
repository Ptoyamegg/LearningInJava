package xyz.dyk.strings;

/**
 * @author PhotoYamEgg
 * @date 2019/8/14 - 23:02
 */
public class Immutable {
    public static String upcase(String s){
        return s.toUpperCase();
    }
    public static void main(String[] args){
        String q = "howdy";
        System.out.println(q);  //  howdy
        String qq = upcase(q);
        System.out.println(qq); //  HOWDY
        System.out.println(q);  //  howdy
    }
}
