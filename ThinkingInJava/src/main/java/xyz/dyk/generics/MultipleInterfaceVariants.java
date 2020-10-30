package xyz.dyk.generics;
//  {CompileTimeError} (Won't compile)
interface Payable<T> {}

class Employee2 implements Payable<Employee2> {}

class Hourly extends Employee implements Payable<Hourly> {}

public class MultipleInterfaceVariants {
}
