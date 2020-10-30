package xyz.dyk.innerclasses;

class Egg{
    private Yolk y;
    protected class Yolk{
        public Yolk(){System.out.println("Egg.Ylok()");}
    }
    public Egg(){
        System.out.println("New Egg()");
        y = new Yolk();
    }
}

public class BigEgg extends Egg{
    public class Yolk{
        public Yolk(){System.out.println("BigEgg.Yolk()");}
    }
//    public BigEgg(){System.out.println("New BigEgg()");}
    public static void main(String[] args) {
        new BigEgg();
    }
}
