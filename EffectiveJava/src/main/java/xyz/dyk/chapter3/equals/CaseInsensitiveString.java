package xyz.dyk.chapter3.equals;

import java.util.Objects;

/**
 * Broken - violate symmetry!
 */
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }
    //  Broken - violate symmetry!
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString) {
            return s.equals(((CaseInsensitiveString) o).s);
        }
        if (o instanceof String) {      //  One-way interoperablity!
            return s.equalsIgnoreCase((String) o);
        }
        return false;
    }
}
