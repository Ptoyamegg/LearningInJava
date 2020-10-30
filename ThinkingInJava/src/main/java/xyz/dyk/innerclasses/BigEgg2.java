package xyz.dyk.innerclasses;

class Egg2{
    protected class Yolk{
        public Yolk(){System.out.println("Egg2.Yolk()");}   //1
        public void f(){System.out.println("Egg2.Yolk.f()");}
    }
    private Yolk y = new Yolk();    //1
    public Egg2(){System.out.println("New Egg2()");}    //2
    public void insertYolk(Yolk yy){ y = yy;}
    public void g(){y.f();}
}

public class BigEgg2 extends Egg2{
    public class Yolk extends Egg2.Yolk{    //3
        public Yolk(){ System.out.println("BigEgg2.Yolk()");}   //4
        public void f() { System.out.println("BigEgg2.Yolk.f()");}  //5
    }
    public BigEgg2(){
        insertYolk(new Yolk());
//        System.out.println("++++++++");
    }
    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        System.out.println("----------");
        e2.g();
    }
}
