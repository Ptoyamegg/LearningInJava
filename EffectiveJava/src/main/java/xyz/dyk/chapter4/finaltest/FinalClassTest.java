package xyz.dyk.chapter4.finaltest;

public class FinalClassTest {
    private int i;
    private FinalClassTest() {
    }

    public FinalClassTest(int i) {
        this.i = i;
    }
}
class ExtendClass extends FinalClassTest{
    public ExtendClass() {
        super(1);
    }
}