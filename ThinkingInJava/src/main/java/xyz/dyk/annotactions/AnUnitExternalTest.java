package xyz.dyk.annotactions;
//  Create non-embedded tests.

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

public class AnUnitExternalTest extends AtUnitExample1 {
    @Test boolean _methodOne() {
        return methodOne().equals("This is methodOne()");
    }
    @Test
    boolean _methodTwo() { return methodTwo() == 2; }

    public static void main(String[] args) {
        OSExecute.command(
                "java net.mindview.atunit.AtUnit AnUnitExternalTest"
        );
    }
}
