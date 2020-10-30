package xyz.dyk;

class StaticSuper{
    public static String staticGet(){
        return "Base staticGet()";
    }
    public String dynamiGet(){
        return "Base dunamicGet()";
    }
}

class StaticSub extends StaticSuper{
    public static String staticGet(){
        return "xyz.dyk.Derived staticGet()";
    }
    public String dynamiGet(){
        return "xyz.dyk.Derived dunamicGet()";
    }
}

public class StaticPolymorphism {
    public static void main(String[] args){
        StaticSuper sup = new StaticSub();
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamiGet());
       // System.out.println(xyz.dyk.StaticSub.staticGet());
    }
}
