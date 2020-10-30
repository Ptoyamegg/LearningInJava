package xyz.dyk.holding;

import java.util.Map;

/**
 * @author PhotoYamEgg
 * @date 2019/8/11 - 13:57
 */
public class EnviromentVariables {
    public static void main(String[] args) {
        for (Map.Entry entry: System.getenv().entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
