package xyz.dyk.strings;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 17:25
 */
public class Replacing {
    static String s = Splitting.knights;

    public static void main(String[] args) {
        System.out.println(s.replaceFirst("f\\w+", "located"));
        System.out.println(s.replaceAll("shrubbery|tree|herring", "banana"));
    }
}
