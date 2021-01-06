package xyz.dyk.innerclasses;

public class DotNew {
    private int i = 0;
    public class Inner{
//        private int i = 1;
        public int showOuter(){
            return i;
        }
    }
    public static void main(String[] args){
        DotNew dn = new DotNew();
        Inner dni = dn.new Inner();
        System.out.println(dni.showOuter());
        //! DotNew.Inner dni2 = DotNew.new Inner();
    }
}
