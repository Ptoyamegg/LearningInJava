package xyz.dyk.strings;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 17:44
 */
public class Rudolph {
    public static void main(String[] args) {
        for (String pattern : new String[]{
                        "Rudolph", "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*" }) {
            System.out.println("Rudolph".matches(pattern));
        }
    }
}
