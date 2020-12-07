package xyz.dyk.innerClass;

public class Outer {
    public void inner() {
        final int num = 5;
        class InnerClass{
            private int num = 4;
            public void testNum() {
                System.out.println(num);
            }
        }
        InnerClass innerClass = new InnerClass();

    }

}
