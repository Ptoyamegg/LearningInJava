package xyz.dyk.generics;
//  {CompileTimeError}  (Won't compile)

public class NonCovariantGenerics {
    //  Compile Error: incompatible types:
//    List<Fruit> fruits = new ArrayList<Apple>();
}
