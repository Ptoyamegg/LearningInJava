package xyz.dyk.exceptions;

/**
 * @author PhotoYamEgg
 * @date 2019/8/12 - 10:24
 */
public class OnOffSwitch {
    public static Switch sw = new Switch();
    public static void f()throws OnOffException1,OnOffException2{}

    public static void main(String[] args) {
        try {
            sw.on();
            //  Code that can throw xyz.dyk.exceptions...
            f();
            sw.off();
        }catch (OnOffException1 e){
            System.out.println("OnOffException1");
            sw.off();
        }catch (OnOffException2 e){
            System.out.println("OnOffException2");
            sw.off();
        }
    }
}
