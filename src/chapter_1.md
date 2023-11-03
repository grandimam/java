Java platforms has tools and libraries necessary for developing Java based application. 

JVM and JRE are core components of JDK. JVM is the run-time engine to run the Java application. JVM calls the main method. 
Java applications are called WORA (write once read anywhere). A programmer can develop Java code on one system and expect
to run on any other environment. When we compile a .java file, a .class file gets created by the Java compiler. The *.class* 
files contains byte-code with the same class names present in *.java*. 

These class files go through various stages -

![](https://media.geeksforgeeks.org/wp-content/uploads/CommonArticleDesign20-min-1.png)

### **JIT (Just In Time Compilation)**

Just-in-time (JIT) compilation is a way of executing computer code that involves compilation during execution of a program (runtime).

Converting bytecode to native machine language has a huge impact. These Bytecode have to be interpreted or compiled to proper machine instructions depending on the instruction set architecture. 

Moreover, these can be directly executed if the instruction architecture is bytecode based. Interpreting the bytecode affects the speed of execution. In order to improve performance, JIT compilers interact with the Java Virtual Machine (JVM) at run time and compile suitable bytecode sequences into native machine code.

# Data Type

Java is strictly pass by value. 

In pass by value, the caller and callee method operate on two difference variables which are copies of each other. Any 
changes on the one won't modify the other. While calling the method, the parameters are passed will be clone of the 
original parameters. Any modification done won't have any effect on the original parameters.

In pass by reference, the caller and callee method will operate on the same object. Any changes on the parameter will 
result in changes of the original value. Primitives variables store the actual value, non-primitive variables store reference

There are two broad data types - primitive and reference. Primitive data types are the basic data types. In Java, the 
primitive data types are integer, floating-point, character, and boolean. Reference data types are object that contains 
references to value or other objects.

**Declaring the primitive data types**

```java
int a;
int b;
```

The variables will receive default values based on their declared types. For integers, the default values would be 0, and 0.0.

## Array

An array is a reference type that can store collections of specific type. Arrays are objects and object variables are 
always references in Java. So, when we declare an object variable as final, it means that the variable cannot be changed
to refer to anything else.

## String

An object whose state remains constant after it has been created entirely. Once an object has been assigned to a variable,
we can neither update the reference nor mutate the internal state.

String are created immutable, thread-safe to improve caching and performance. String uses a process called interning, 
where we store only one distinct copy of the value and reusing them to save heap (two different variables point to the 
same string object).

### String Buffer and String Builder

- String are immutable, and objects of StringBuffer and StringBuilder are mutable.
- StringBuffer and StringBuilder are similar, but StringBuilder is faster and preferred over StringBuffer for single-threaded program. If thread safety is needed, then StringBuffer is used.

### **==, comparison**

This way of string comparison only checks for referential equality of two string.

```java
String string1 = "using comparison operator";
String string2 = "using comparison operator";
String string3 = new String("using comparison operator");

assertThat(string1 == string2).isTrue();
assertThat(string1 == string3).isFalse();
```

In the example above, the first assertion is true because the two variables point to the same *String* literal. On the other hand, the second assertion is false because *string1* is created with a literal and *string3* is created using the *new* operator – therefore they reference different objects.

### **equals()**

Equals is overriden by the String class from the Object. This method compares two strings character by character ignoring their address.

```java
String string1 = "using equals method";
String string2 = "using equals method";

String string3 = "using EQUALS method";
String string4 = new String("using equals method");

assertThat(string1.equals(string2)).isTrue();
assertThat(string1.equals(string4)).isTrue();

assertThat(string1.equals(null)).isFalse();
assertThat(string1.equals(string3)).isFalse();
```

In this example, *string1, string2,* and *string4* variables are equal because they have the same case and value irrespective of their address.

For *string3* the method returns *false,* as it's case sensitive. Also, if any of the two strings is *null*, then the method returns *false.*

### **equalsIgnoreCase()**

Ignores casing in characters while comparing *Strings*.

### **compareTo()**

Compares two string character by character lexicographically. This method returns 0 if two *Strings* are equal or if both are *null,* a negative number if the first *String* comes before the argument, and a number greater than zero if the first *String* comes after the argument *String.*

```java
String author = "author";
String book = "book";
String duplicateBook = "book";

assertThat(author.compareTo(book))
  .isEqualTo(-1);
assertThat(book.compareTo(author))
  .isEqualTo(1);
assertThat(duplicateBook.compareTo(book))
  .isEqualTo(0);
```
# Access Modifiers

Access modifiers are used to encapsulate the state of the objects. Default access modifiers are package-private, as all 
members are only visible within the same packages. Protected access modifiers are also package-private but also members 
are visible to their subclasses as well.

## Variable Scope

**Instance and Class Variable**

Instance and class variables don't require us to initialize. As soon as we declare them they are given default value.

```java
@Test
public void whenValuesAreNotInitialized_thenUserNameAndIdReturnDefault() {
    User user = new User();

    assertThat(user.getName()).isNull();
    assertThat(user.getId() == 0);
}
```

**Local Variable**

Local variable must be initialized before use, as they don't have default value. The below code will give error during 
the compilation stage itself.

```java
public void print(){
    int i;
    System.out.println(i);
}
```

**Final**

Final variable is applied to the field that can no longer be modified.


## Non-Access Modifiers

* Final

* Static

* Volatile

* Abstract

* Transient

* Synchronized

## Static

Static keyword means that a particular member belongs to the type itself rather than the instance of that type. That is 
there is only one instance of the member shared across all instances of the class. Static initializers are created 
before the class is created. 

**Fields**

Static fields are stored in the heap. When a field is declared as static, a single copy of that field is created and 
shared among all instance of that class. Static fields can only be declared at a class level, and can be accessed 
without object creation.

```java
public class Car {
    private String name;
    private String engine;

    public static int numberOfCars;

    public Car(String name, String engine) {
        this.name = name;
        this.engine = engine;
        numberOfCars++;
    }

    // getters and setters
}
```

**Methods**

Static methods belong to the class instead of the instance. We can call them without creating the object. Static methods
are resolved in compile time and since overriding is runtime process static methods cannot be overridden. Abstract methods
cannot be static. Static methods can't use this or super. Instance methods can directly access other instance and 
non-instance methods and variables. Static methods can access all static variables, methods and can't access non-static 
variables and methods directly (they require object reference to do so)

**Class**

A class can be made **static** only if it is a nested. We cannot declare a top-level class with a static modifier. Such 
types of classes are called Nested static classes. Nested static class doesn’t need a reference of Outer class. 

In this case, a static class cannot access non-static members of the Outer class.

## Final

Final keyword is used to limit the scope for variables, classes, and methods.

**Class**

Classes market with final keyword cannot be extended. One of the major immutable classes that have final marked are 
String class. Any attempt to extend a final class will result in compilation error. Final class doesn't mean that objects
are immutable. We just can't extend it.

```java
public final class Cat {

    private int weight;

    // standard getter and setter
}
```

**Methods**

Methods marked as finals cannot be overriden.

```java
public class Dog {
    public final void sound() {
        // ...
    }
}
```

- Methods marked as final can be overloaded
- Mark a method as final if we don't want to the caller to cause suprising results
- Difference between marking all the methods final and marking the class final is that in the former we can extend the 
class and in the latter we cannot

**Fields and Variables**

Variables marked as final cannot be reassigned. Primitive variables cannot be assigned again. Reference variables cannot
be reassigned but are still mutable.

```java
final Cat cat = new Cat();
```

```
cat.setWeight(5);

assertEquals(5, cat.getWeight());
```

Final fields can be constant or write once fields. And class constant fields should be capitalized. In case the fields 
are not meant to be serialized then the fields must be specified as final.

```java
static final int MAX_WIDTH = 999;
```

All final fields must be initialized before the constructor completes. **You can assign a value to a final variable only one time.**

- For static final fields, these need to be initialized upon declaration or inside static initializers (as static 
initializers are created before constructors).
- For instance fields, they can be initialized - upon declaration, inside initializer block, inside the constructor.

Constructor can be invoked only **one** time per object creation by using the `new` keyword. You cannot invoke constructor multiple times, because constructor are not designed to do so.

```
class Test {
    private final List foo;

    public Test() {
        foo = new ArrayList();
        foo.add("foo"); // Modification-1
    }

    public void setFoo(List foo) {
       //this.foo = foo; Results in compile time error.
    }
}
```

`foo` is an **instance** variable. When we create `Test` class object then the instance variable `foo`, will be copied 
inside the object of `Test` class. If we assign `foo` inside the constructor, then the compiler knows that the constructor
will be invoked only once, so there is no problem assigning it inside the constructor.

If we assign `foo` inside a method, the compiler knows that a method can be called multiple times, which means the value
will have to be changed multiple times, which is not allowed for a `final` variable. So the compiler decides constructor
is good choice!

**Argument**

Final argument cannot be changed inside the method

```java
public void methodWithFinalArguments(final int x) {
    x=1;
}
```

The final local variable x cannot be assigned. It must be blank and not using a compound assignment.

**Static - Final**

These fields need to be initialized upon declaration or inside static initializers.

```java
private static final List foo = new ArrayList();
```

`foo` is now a **static** variable. When we create an instance of `Test` class, `foo` will not be copied to the object 
because `foo` is static. Now `foo` is not an independent property of each object. This is a property of `Test` class. 
But `foo` can be seen by multiple objects and if every object which is created by using the `new` keyword which will 
ultimately invoke the `Test` constructor which changes the value at the time of multiple object creation (Remember 
`static foo` is not copied in every object, but is shared between multiple objects)

## Synchronized

Race condition occurs when two or more thread attempt to update shared data. We can avoid this by synchronized thread access to the shared data, allowing only one thread to execute at any given time. 

Synchronized keyword can be used across - 

* instance methods

* static methods

* code blocks

Synchronized internally uses a monitor lock to provide synchronization. The monitors are bound to an object, and all synchronized blocks of the same object can have only one thread executing them at the same time. 

**Synchronized instance methods**

```java
public synchronized void synchronisedCalculate() {
    setSum(getSum() + 1);
}
```

Instance methods are *synchronized* over the instance of the class owning the method, which means only one thread per instance of the class can execute this method.

**Synchronized static methods**

```java
public static synchronized void syncStaticCalculate() {
     staticSum = staticSum + 1;
 }
```

These methods are synchronized on the class objects. Since only one *Class* object exists per JVM per class, only one thread can execute inside a *static* *synchronized* method per class, irrespective of the number of instances it has.

**Synchronized blocks within the methods**

```java
public void performSynchronisedTask() {
    synchronized (this) {
        setCount(getCount()+1);
    }
}
```

This is the monitor object. The code inside the block gets synchronized on the monitor object. Simply put, only one thread per monitor object can execute inside that block of code. If the method was *static*, we would pass the class name in place of the object reference, and the class would be a monitor for synchronization of the block:
