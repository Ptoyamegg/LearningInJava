package xyz.dyk.arrays;

import java.util.Arrays;

public class ThreeDWithNew {
    public static void main(String[] args) {
        //  3-D array with fixed length:
        int[][][] a = new int[2][3][4];
        System.out.println(Arrays.deepToString(a));
    }
}
