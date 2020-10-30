package xyz.dyk.arrays;
//  Sorting an array of Strings.

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;

public class StringSorting {
    public static void main(String[] args) {
        String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
        System.out.println("Before sort: \n" + Arrays.toString(sa));
        Arrays.sort(sa);
        System.out.println("After sort: \n" + Arrays.toString(sa));
        Arrays.sort(sa, Collections.reverseOrder());
        System.out.println("Reverse sort: \n" + Arrays.toString(sa));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sort: \n" + Arrays.toString(sa));
    }
}
