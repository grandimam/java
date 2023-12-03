# Object Oriented Programming

The main oops concepts are as follows: class / object, abstraction, inheritance, and polymorphism. 

## Class / Object

A class is a blueprint from which individual objects are created. It helps define all the internal objects that hold the object state. An object is an instance of a class. It contains state and behavior, using fields and methods. Objects are stored in heap memory which is a large pool of unused memory allocated for the application. Once the objects are created, it needs to be destroyed. GC is a program for automatic memory management in the Java platform. To destroy objects -

- Objects should no longer have any reference pointing to it
- And, all reference point to the object should be out of scope
 

Class is a blueprint of certain type containing state and behavior. Objects of a class is called instances. Every class has an empty constructor by default. An empty constructor only initializes the fields with default values.

An object is a living instance of a class and they maintain the state and behavior during runtime. Class constructor doesn't initialize the field it merely declares

```java
class BankAccount {
    String name;
    LocalDateTime opened;
    double balance;

    @Override
    public String toString() {
        return String.format("%s, %s, %f", 
          this.name, this.opened.toString(), this.balance);
    }
}
```

```java
BankAccount account = new BankAccount();
account.toString();
```

```java
java.lang.NullPointerException
    at com.baeldung.constructors.BankAccount.toString(BankAccount.java:12)
    at com.baeldung.constructors.ConstructorUnitTest
      .givenNoExplicitContructor_whenUsed_thenFails(ConstructorUnitTest.java:23)
```

### Final

* Final variable cannot be reassigned once initialized. It acts like a constant
* Final methods cannot be overriden. It can be overloaded.
* Final classes cannot be subclassed.

They can only be initialized within the constructor of the class and are immutable. If we create multiple constructor within the class, then they need to be initialized under each constructor.

### This and Super

The this keyword provides a reference to the current object. The super keyword refers to the parent class object. These keywords allow us to refer to a variable or method that has the same name in the current context and the parent object. Super is used on to access the instance variables and methods of parent class. This is used to refer the current object. It is typically used in constructor to differentiate the local variable with the parameters. 

### Record Class

A record class is a immutable class with only getters, and that cannot be extended. It reduces boiler plate code to create an immutable class using final keyword and restricting constructor access. Record class can however extend or implement other classes.

```java
record Person(int age, String name) {

}

record Person(int age) implements Student {
    @Override
    public String getSchoolName() {
        return super.name
    }
}
```

It is possible to add a static method to a record class. It cannot use any instance variables but can only use variables passed as arguments. record can also have another constructor. It is not possible to add another property or a setter to record, while all additional getters have to use only getters provided already by record.

### Sealed 

Sealed is used to allow certain classes to extend capabilities of the parent class. The difference between a sealed class or interface and a final one is that a sealed class or interface always has a permits keyword, followed by the list of the existing direct subtypes that are allowed to extend the sealed class or interface.

The subtypes listed after the permits keyword must exist at compilation time in the same module as the sealed class or in the same package if in the default (unnamed) module.

### Upcasting vs Downcasting

Casting refers to taking an object of one type and turning it into another type. Upcasting is treating an instance of a subclass as if it were an instance of one of its superclass types. Downcasting is treating an instance of a superclass as if it were an instance of one of its subclass types.

```
class Animal {

}

class Dog extends Animal {

}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();

        // Upcasting Dog to Animal. This is implicit and safe.
        Animal animal = dog; 

        // Now 'animal' can be used to call methods that are defined in 'Animal' class.
    }
}

```

```
class Animal {

}

class Dog extends Animal {

}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();

        // Downcasting Animal to Dog. This must be explicit and can be unsafe.
        Dog dog = (Dog) animal; // This will throw a ClassCastException at runtime.

        // If we are sure that 'animal' is indeed a Dog, then downcasting is fine.
        Animal animalDog = new Dog();
        Dog realDog = (Dog) animalDog; // This is correct and will not throw an exception.
    }
}
```

### Converting between primitive and reference types

The conversion of a primitive type value to an object of the corresponding wrapper class is called boxing. Also, the conversion from an object of a wrapper class to the corresponding primitive type value is called unboxing.

* The boxing of a primitive type can be done either automatically (called autoboxing) or explicitly using the valueOf() method available in each wrapper type. 

* The conversion from a wrapper type to the corresponding primitive type is not called auto-unboxing; it is called implicit unboxing instead. In contrast to autoboxing, it is possible to use implicit unboxing even between wrapping and primitive types that do not match.

## Encapsulation

Encapsulatio is wrapping data and code into a single unit (typically a class). In encapsulation, the variables of are hidden from other classes and can be accessed only through the methods of their current class. Therefore, it is also known as data hiding. It is implemented using access modifiers.

### Public

The public access modifier makes the class, method, or field accessible from any other class everywhere, whether within the same package or in others. It provides the widest scope among all access modifiers.

### Private

The private access modifier is the most restrictive. Variables and methods marked as private can be accessed only within the class in which they are declared. They are not visible to subclasses or any external classes.

### Protected

The protected access modifier allows the variable or method to be accessed within the same package or by subclasses in other packages. It is more accessible than default and private but more restrictive than public.

## Abstraction

Data abstraction is the ability to view only the required characteristics of the object. Data abstraction is implemented using abstract and interface classes

### Abstract

Abstract class enables data encapsulation and polymorphism. It doesn't allow multiple inheritance. Properties of abstraction using abstract keyword -

* It is defined using the abstract keyword preceding the class keyword

* Abstract class can be subclassed but cannot be instantiated. If a class has one abstract method, then the entire class should be abstracted. This also means an abstract class can have concrete or abstract methods.

* Subclass that extend the abstract class must implement all the abstract methods or be an abstract class itself

```java
public abstract class BoardGame {

    //... field declarations, constructors

    public abstract void play();

    //... concrete methods
}
```

```java
public class Checkers extends BoardGame {

    public void play() {
        //... implementation
    }
}
```

When to use abstract classes -

- Abstraction can be used to encapsulate multiple functionality into one place and can be reused across other places

- Abstraction allows for lazy implementation, where the blueprint is defined and the implementation is done more concretely someplace else

- Concrete classes have common functionality that can be used with protected access modifiers

Properties of abstraction using interface keyword -

- An interface is a blue print for a class. Interface cannot be instantiated using the new keyword but concrete class can implement these methods. Eg Map, List, Set

```java
interface Mogul {
    void name();
}
```

- Abstract class can have both implemented and unimplemented methods. Eg. AbstractList, AbstractSet

### Interfaces

A class is a blueprint for an object, and an interface is a blueprint for a class. Class is said to be concrete if it can be created using new operator. Concrete classes can implement interfaces. Not all classes are concrete, Java also has abstract classes - abstract, interface.

Interface is a blueprint for a class. It can contain constants, method signatures, default method, static method, and nested types. Method body exist only for default methods and static methods. Interface cannot be instantiated they can only be implemented by classes or extended by other interfaces. Following are the major purpose for interfaces:

1. To achieve abstraction
2. To achieve multiple inheritance
3. To achieve loose coupling

```
public interface Animal {
    // This is an abstract method
    public void eat();

    // This is a default method
    default void breathe() {
        System.out.println("Breathing...");
    }
    
    // This is a static method
    static int getLegCount() {
        return 4; // Most animals have 4 legs
    }
}
```

### Static Methods

These can be defined in interfaces just like in classes. However, they are not part of the implementing class's interface, they can only be called on the interface itself.

### Default Methods

These are methods with a default implementation. They were added to enable new functionality to be added to interfaces without breaking the existing implementation of classes that use these interfaces. They are used to enabled backward compatibility.

### Interface vs Abstract Class

Abstract classes are similar to interfaces but they allow fields and constructors, and can also define non-final methods. 

## Polymorphism

Ability for an object to take different forms at runtime. It is achieved through method overriding.

```java
public interface Shape {
    String name();
}
```

```
public class Circle implements Shape {

    @Override
    public String name() {
        return "Circle";
    }
}
```

```java
public class Square implements Shape {

    @Override
    public String name() {
        return "Square";
    }
}
```

```java
List<Shape> shapes = new ArrayList<>();
Shape circleShape = new Circle();
Shape squareShape = new Square();

shapes.add(circleShape);
shapes.add(squareShape);

for (Shape shape : shapes) {
    System.out.println(shape.name());
}
```

## Inheritance

Inheritance is a concept in which the properties of the parent class is inherited by the child class. It enables extending and reusing of code. Inheritance is applied using class and interface and can be of multiple types - single, multilevel, hierarchical, and multiple.

Class only supports single inheritance. A subclass inherting a class can inherit all public, protected instance fields and methods. Static fields and methods don't comply to the traditional rules of inheritance. Subclass cannot inherit static fields or methods but instead access them using Class prefix. A subclass cannot access a private instance field / method, but can access final instance field or method but cannot override it.

```java
public interface Car {

}

public class Skoda implements Car {

}
```

#### Single

```java
class Parent {
    // Parent class members
}

class Child extends Parent {
    // Child class members
}
```

### Inheritance Resolution

* For instance fields and methods - if both the have fields or methods with the same name then the field of method need to be explicitly prefixed with this or super for resolution else will be overriden.

* For static variables and methods - if both have static fields or methods with the same name, then they need to be explicity called using the class name (not super).

```java
public class Car {
    public static String msg() {
        return "Car";
    }
}

public class ArmoredCar extends Car {
    public static String msg() {
        return super.msg(); // this won't compile.
    }
}

public class ArmoredCar extends Car {
    public static String msg() {
        return Car.msg(); // this will compile.
    }
}
```

#### Multilevel (Chain)

This occurs when a class is derived from a class which is also derived from another class, i.e., there is a chain of inheritance.

```java
class Parent {
    // Parent class members
}

class Child extends Parent {
    // Child class members
}

class Grandchild extends Child {
    // Grandchild class members
}
```

#### Hierarchial

In hierarchical inheritance, multiple classes inherit from a single superclass.

```java
class Parent {
    // Parent class members
}

class Child1 extends Parent {
    // Child1 class members
}

class Child2 extends Parent {
    // Child2 class members
}
```

#### Multiple

Multiple inheritance is supported only using interfaces. A class can inherit multiple interfaces. An interface can inherit multiple interfaces (using extends keyword).

```java
public interface Floatable {
    void floatOnWater();
}
```

```java
public interface Flyable {
    void fly();
}
```

```java
public class ArmoredCar extends Car implements Floatable, Flyable {
    public void floatOnWater() {
        System.out.println("I can float!");
    }

    public void fly() {
        System.out.println("I can fly!");
    }
}
```

In Java 8, with default methods if a class implements multiple interfaces with same method signature, the child class would inherit separate implementation. 

```java
public interface Floatable {
    default void repair() {
        System.out.println("Repairing Floatable object");    
    }
}
```

```java
public interface Flyable {
    default void repair() {
        System.out.println("Repairing Flyable object");    
    }
}
```

```java
public class ArmoredCar extends Car implements Floatable, Flyable {
    // this won't compile
}
```

In case on wants to implement both interfaces, we will have to override the methods. If the interfaces in the preceding examples define variables with the same name, say *duration*, we can't access them without preceding the variable name with the interface name:

```java
public interface Floatable {
    int duration = 10;
}

public interface Flyable {
    int duration = 20;
}

public class ArmoredCar extends Car implements Floatable, Flyable {

    public void aMethod() {
        System.out.println(duration); // won't compile
        System.out.println(Floatable.duration); // outputs 10
        System.out.println(Flyable.duration); // outputs 20
    }
}
```

### Rules of Inheritance on Interfaces

A subclass inheriting an interface can access all method signatures, constants, default methods, and static fields and methods. A subclass can override default methods of the parent interface.

## Overloading and Overriding

### Overloading

Overloading is the ability to create multiple methods with the same name. This is achieved through a process called static binding which is the ability to associate method calls to the correct method (in the method body). In case of overloading binding is done in compile time hence it is also called as static binding. The compiler simply checks the method signature. 

Java allows method overloading in the following ways - 

* Different number of arguments

```java
public class Multiplier {

    public int multiply(int a, int b) {
        return a * b;
    }

    public int multiply(int a, int b, int c) {
        return a * b * c;
    }
}
```

* Different argument types

```java
public class Multiplier {

    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }
}
```

* Two methods that differ only in their return types is not possible

```java
public int multiply(int a, int b) { 
    return a * b; 
}

public double multiply(int a, int b) { 
    return a * b; 
}
```

### Overriding

Overriding is the ability to extend implementation in subclasses for methods defined in the parent class. It is a consequence of inheritance where overloading need not have inheritance. Since overriding can only be implemented using inheritance where there is a hierarchy of base and subtypes. The compiler cannot determine at compile time what method to call. This happens only at runtime and this called as dynamic binding. 

```java
public class Vehicle {

    public String accelerate(long mph) {
        return "The vehicle accelerates at : " + mph + " MPH.";
    }

    public String stop() {
        return "The vehicle has stopped.";
    }

    public String run() {
        return "The vehicle is running.";
    }
}
```

```java
public class Car extends Vehicle {

    @Override
    public String accelerate(long mph) {
        return "The car accelerates at : " + mph + " MPH.";
    }
}
```

It's valid to make an overridden method to accept arguments of different types and return a different type as well, but with full adherence to these rules:

- If a method in the base class takes argument(s) of a given type, the overridden method should take the same type or a supertype (a.k.a. *contravariant* method arguments)
- If a method in the base class returns *void*, the overridden method should return *void*
- If a method in the base class returns a primitive, the overridden method should return the same primitive
- If a method in the base class returns a certain type, the overridden method should return the same type or a subtype (a.k.a. *covariant* return type)
- If a method in the base class throws an exception, the overridden method must throw the same exception or a subtype of the base class exception