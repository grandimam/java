# Functional Programming

Functional interface is an interface with only one abstract method (including the parent interface as well). It may howeevr include static and default methods. 

```
@FunctionalInterface
interface A {
    void method1();
    default void method2(){}
    static void method3(){}
}
@FunctionalInterface
interface B extends A {
    default void method4(){}
}
@FunctionalInterface
interface C extends B {
    void method1(); // overrides the abstract method of interface A
}
//@FunctionalInterface
interface D extends C {
    void method5();  // not a functional interface
}
```

Following are the standard functional interfaces in Java - Consumer, Predicate, Supplier, Function. If a Lambda expression uses a local variable created outside it, this local variable has to be final or effectively final (not reassigned in the same context). The this keyword in a Lambda expression refers to the enclosing context, not the Lambda expression itself

### Lambda Expression

A Lambda expression is a function, implemented as an anonymous method.

### Anonymous Classes

Anonymous classes are classes that don't have a name. 