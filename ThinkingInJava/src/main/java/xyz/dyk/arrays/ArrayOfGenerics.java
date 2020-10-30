package xyz.dyk.arrays;
//  It is possible to create xyz.dyk.arrays of xyz.dyk.generics.

import java.util.ArrayList;
import java.util.List;

public class ArrayOfGenerics {
    @SuppressWarnings("unchedked")
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[])la;    //  "Unchecked" warning
        ls[0] = new ArrayList<String>();
        //  Compile-time checking produces an error:
        // !ls[1] = new ArrayList<Integer>();

        //  The problem: List<String> is a subtype of Object
        Object[] objects = ls;  //  So assignment is OK
        //  Compile-time and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        //  However, if your needs are straightforward it is possible to create an array of xyz.dyk.generics, albeit with an "unchecked" warning:
        List<BerylliumSphere>[] spheres =
                (List<BerylliumSphere>[])new List[10];
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new ArrayList<BerylliumSphere>();
    }
}
