package xyz.dyk.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ByteSet {
    Byte[] possibles = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    Set<Byte> myset = new HashSet<Byte>(Arrays.asList(possibles));
    //  But you can't do this:
    //  Set<Byte> myset2 = new HashSet<Byte>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
}
