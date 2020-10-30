package xyz.dyk.innerclasses;

public class DotThis {
    void f(){System.out.println("DotThis.f()");}
    public class Inner{
        public DotThis outer(){
            return DotThis.this;
            // A plain "this" would be Inner's "this"
        }
    }
    public Inner inner(){return new Inner();}
    public static void main(String[] args){
        DotThis dt = new DotThis();
        Inner dti = dt.inner();
        //! DotThis.Inner dti2 = new DotThis.inner();
        dti.outer().f();
    }
}
