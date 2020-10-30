package xyz.dyk.holding;

import java.util.Arrays;

/**
 * @author PhotoYamEgg
 * @date 2019/8/11 - 14:07
 */
public class ArrayIsNotIterable {
    static <T> void test(Iterable<T> ib){
        for (T t : ib)
            System.out.print(t + " ");
    }

    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 3));
        String[] strings = { "A", "B", "C"};
        //  An array works in foreach , but it's not Iterable:
        //! test(xyz.dyk.strings);
        //  You must explicitly convert it to an Iterable:
        test(Arrays.asList(strings));
    }
}
