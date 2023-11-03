# Object

Objects consisting of state and behavior. They store state in fields and exposes behavior using methods. They need to be
declared and initialized to be created. Objects are stored in heap memory. Heap is a large pool of  unused memory allocated 
for the application.  

Object created needs to be destroyed. GC is a program for automatic memory management in the Java platform. To destroy 
objects -
- They should no longer have any reference pointing to it
- All reference point to the object should be out of scope

**Initialization**

Java has empty initializers with no associated name or data type. These initializers are static and instance. Initialization are done in order -

- Static variables and static initializers

- Instance variables and instance initializers

- Constructors

```java
{
    id = 0;
}
```

Static intializers are used to intialize static fields.

```java
private static String forum;
static {
    forum = "Java";
}
```

**Declaration**

Declaration is the process of defining the variable. Initialization is about assigning a value to a variable

Primitive types hold value in-memory where the variable is allocated. Reference types hold reference to objects (instances of the class) instead of the object they are referring to.

```java
@Test
public void whenIntializedWithNew_thenInstanceIsNotNull() {
    User user = new User();

    assertThat(user).isNotNull();
}
```

Object creation is bit more complex. Object initilization occurs using the new keyword, this invokes the constructor and initializes the object in memory.

* New keyword is responsible for allocating memory for the new object through a constructor

* Constructor represents the main property of the object and is used to initialize instance variables. Class can have many constructors as long as the parameter (overloading)

**Other methods for creating objects**

Objects can be created using reflection, cloning, and serialization. 

* Reflection is a mechanism to inspect classes, fields, and methods at run-time

* Cloning is a way to create exact copy of the object using the clone() and clonable interface.


# Class

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

**Concrete**

- Concrete classes are class that we can create instance of using the new keyword. Examples of concrete classes are HashMap, ArrayList, etc.Not all classes are concrete, Java also has abstract classes - abstract, interface.

- Class is a blueprint for an object and Interface is a blueprint for a class. Concrete classes can implement interfaces.

- Primitive types hold the value in-memory where the variable is allocated. References don't hold the value of the object they refer to.

**Final Variables**

They can only be initialized within the constructor of the class and are immutable. If we create multiple constructor within the class, then they need to be initialized under each constructor.

**This and Super**


This is used to refer the current object. It is typically used in constructor to differentiate the local variable with the parameters. 

Super is used to access the parent class. It can be used to call the parent constructor. It can be used to access the parent constructor's variables.

### Abstract

Java provides a mechanism to achieve abstraction, polumorphism, inheritance has can be achieved using abstract and interface keyword. Abstract class enables data encapsulation and polymorphism. It doesn't allow multiple inheritance.

Properties of abstraction using abstract keyword -

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

Interfaces is used to implement polymorphism and multiple inheritance. They help add additional functionality to 
unrelated classes. For instance Comparable, Comparator, and Cloneable are interfaces that can be implemented by unrelated 
classes.

**Default Methods**

Conceptually the main purpose of default methods in interfaces is backward compatibility.

**Multiple Inheritance**

Java primarily supports single inheritance. Using interfaces we can implement multiple interfaces.

```java
public interface Transform {
    void transform();
}

public interface Fly {
    void fly();
}

public class Car implements Fly, Transform {

    @Override
    public void fly() {
        System.out.println("I can Fly!!");
    }

    @Override
    public void transform() {
        System.out.println("I can Transform!!");
    }
}
```

In order to enable multiple inheritance through interfaces we have to remember a few rules.

- Interface extending another interface

- Abstract class implementing interfaces

# Object-Oriented Concepts

Data abstraction is the ability to view only the required characteristics of the object. Encapsulation is the ability to hide behavior under single unit typically a class. It is a protective shield that prevents the data from being accessed by the code outside this shield. Encapsulation is implemented using access modifiers, and abstraction is implemented using abstract and interface classes.

### Polymorphism

Ability for an object to take different forms during runtime. It is achieved through overriding behavior of a specific object.

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

### Inheritance

Inheritance enables us to reuse existing code or extending an existing type. A call can inherit another class and multiple inheritance. 

When a class inherits another class or interface, it not only inherits the members but also their type.

```java
public interface Car {

}

public class Skoda implements Car {

}


public class Employee {
    private String name;
    private Car car;

    public Employee(Car car) {
        this.car = car
    }
}

// Armored Car is a subclass of Car
Employee e1 = new Employee(new ArmoredCar());
```

If both the superclass and subclass define a variable or method with the same name, then it needs to be prefixed with this or super.

If both the superclass and subclass define a static variable or method with the same name, then they need to be explicity called using the class name and not super().

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

Or we can call them explicity. 

```java
public class Car {
    public static String msg() {
        return "Car";
    }
}

public class ArmoredCar extends Car {
    public static String msg() {
        return "ArmoredCar";
    }
}

Car first = new ArmoredCar();
ArmoredCar second = new ArmoredCar();
```

For the above code, *first.msg()* will output “Car*“* and *second.msg()* will output “ArmoredCar”. The static message that is called depends on the type of the variable used to refer to *ArmoredCar* instance.

**Class Inheritance**

* Inheritance using a class. Classes only support single inheritance. A subclass inherits all the non-static protected and public methods from the superclass. 

* Default access modifiers can inherit only within the same package. Parent members can be accessed directly by the subclass.

**Interface Inheritance**

Class can inherit multiple interfaces. 

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
public class ArmoredCar extends Car implements Floatable, Flyable{
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

**Interface extending multiple interfaces**

Interface can extend multiple interfaces by using extends keyword.

```java
public interface Floatable {
    void floatOnWater();
}

interface interface Flyable {
    void fly();
}

public interface SpaceTraveller extends Floatable, Flyable {
    void remoteControl();
}
```

### Overloading and Overriding

**Overloading**

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

**Overriding**

Overriding is the ability extend implementation in subclasses for methods defined in the parent class. Overriding is more a consequence of inheritance where overloading need not have inheritance. 

Since overriding can only be implemented using inheritance where there is a hierarchy of base and subtypes. The compiler cannot determine at compile time what method to call. This happens only at runtime and this called as dynamic binding. 

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