package xyz.dyk.strings;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 14:39
 */
public class WhitherStringBulider {
    public String implicit(String[] fields){
        String result = "";
        for (int i = 0; i < fields.length; i++)
            result += fields[i];
        return result;
    }
    public String explicit(String[] fields){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++)
            result.append(fields[i]);
        return result.toString();
    }
}
