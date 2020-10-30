package xyz.dyk.typeinfo;

interface Interface{
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {
    public void doSomething() {
        System.out.println("doSomething");
    }
    public void somethingElse(String arg) {
        System.out.println("somethingElse" + arg);
    }
}

class SimpleProxy implements Interface {
    private Interface proied;
    public SimpleProxy(Interface proied) { this.proied = proied;}
    public void doSomething() {
        System.out.println("SimpleProxy doSomething()");
        proied.doSomething();
    }
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse" + arg);
        proied.somethingElse(arg);
    }
}

public class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
