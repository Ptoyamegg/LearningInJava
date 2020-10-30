package xyz.dyk.innerclasses;

public class DotNew {
    public class Inner{}
    public static void main(String[] args){
        DotNew dn = new DotNew();
        Inner dni = dn.new Inner();
        //! DotNew.Inner dni2 = DotNew.new Inner();
    }
}
