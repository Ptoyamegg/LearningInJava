package xyz.dyk.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 19:03
 */
public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
        while (m.find())
            System.out.print(m.group() + " ");
        System.out.println();
        int i = 0;
        while (m.find(i)){
            System.out.print(m.group() + " ");
            i++;
        }
    }
}
