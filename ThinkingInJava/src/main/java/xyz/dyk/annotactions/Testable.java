package xyz.dyk.annotactions;

//import net.mindview.atunit.*;


import net.mindview.atunit.Test;

public class Testable {
    public void execute() {
        System.out.println("Executing..");
    }
    @Test
    void testExecute() { execute(); }
}
