package xyz.dyk.chapter4.extend;

public class Super {
    //  Broken - constructor invokes an overridable method

    public Super() {
        overrideMe();
    }
    public void overrideMe() {
        System.out.println("super");
    }
}
