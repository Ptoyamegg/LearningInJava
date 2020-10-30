package xyz.dyk.arrays;
//  Arrays of generic types won't compile.

public class ArrayOfGenericsType<T> {
    T[] array;  //  OK
    @SuppressWarnings("unchecked")
    public ArrayOfGenericsType(int size) {
        //! array = new T[size];    //  Illegal
        array = (T[])new Object[size];  //  "unchecked" Warning
    }
    //  Illegal:
    //! public <U> U[] makeArray() { return new U[10]; }
}
