package xyz.dyk.strings;
//  Allows you to easily try out regular expressions.
//  {Args:  abcabcabcdefabc "abc+" "(abc)+" "(abc){2,}" }

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 17:53
 */
public class TestRegularExpression {
    public static void main(String[] ar) {
        String[] args = {"abcabcabcdefabc","abc+","(abc)+","(abc){2,}"};

        if (args.length < 2){
            System.out.println("Usage:\njava TestRegularException " + "characterSequence regularException+");
            System.exit(0);
        }
        System.out.println("Input: \"" + args[0] + "\"");
        for (String arg :
                args) {
            System.out.println("Regular expression: \"" + arg +"\"");
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(args[0]);
            while (m.find()){
                System.out.println("Match \"" + m.group() + "\" at positions " + m.start() + "-" + (m.end() - 1));
            }
        }
    }
}
