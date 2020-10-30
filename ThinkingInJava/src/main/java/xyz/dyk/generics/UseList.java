package xyz.dyk.generics;

import java.util.List;

//  {CompileTimeError} (Won't compile)
public class UseList<W, T> {
    //  void f(List<W> v) {}
    void f(List<T> v) {}    //  因为擦除的原因，这两个参数显示相同的签名
}
