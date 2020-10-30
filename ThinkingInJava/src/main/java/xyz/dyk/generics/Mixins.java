package xyz.dyk.generics;

import java.util.Date;

interface TimeStamped { long getStamp(); }

class TimeStampedImp implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImp() {
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}

interface SerialNumbered { long getSerialNumber();}

class SerialNumberedImp implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber() { return serialNumber;}
}

interface Basic {
    void set(String val);
    String get();
}

class BasicImp implements Basic {
    private String value;
    public void set(String val) { value = val;}
    public String get() { return value;}
}

//  混型：混合多个类，以产生一个可以表示混型中的所有类型的类。
class Mixin extends BasicImp
        implements TimeStamped,SerialNumbered{
    private TimeStampedImp timeStampedImp = new TimeStampedImp();
    private SerialNumberedImp serialNumberedImp = new SerialNumberedImp();
    public long getStamp() { return timeStampedImp.getStamp();}
    public long getSerialNumber() { return serialNumberedImp.getSerialNumber();}
}

public class Mixins{
    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " + mixin1.getStamp() + " " + mixin1.getSerialNumber());
        System.out.println(mixin2.get() + " " + mixin2.getStamp() + " " + mixin2.getSerialNumber());
    }
}
