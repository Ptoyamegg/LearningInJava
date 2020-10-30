package xyz.dyk.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 15:27
 */
public class InfiniteRecursion {
    public String toString() {
        //  return " InfiniteRecursion address: " + this + "\n";
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v =
                new ArrayList<InfiniteRecursion>();
        for (int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
}
