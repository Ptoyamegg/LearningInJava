package xyz.dyk.holding;
//  The "Adapter Method" idiom allows you to use foreach
//  with additional kinds of Iterables.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author PhotoYamEgg
 * @date 2019/8/11 - 14:15
 */
class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<T> c){ super(c);}
    public Iterable<T> reversed() {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;
                    public boolean hasNext() {return current > -1;}
                    public T next() {return get(current--);}
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

public class AdapterMethodIdiom {
    public static void main(String[] args) {
        ReversibleArrayList<String> ral =
                new ReversibleArrayList<>(
                        Arrays.asList("To be or not to be".split(" ")));
        //  Grabs the ordinary iterator via iterator():
        for (String s : ral)
            System.out.print(s + " ");
        System.out.println();
        for (String s : ral.reversed())
            System.out.print(s + " ");
    }
}
