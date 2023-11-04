# Types and Variables

Java has the following primitive types - int, float, boolean, char. Everything else is a referenced type in Java. Referenced types are class that have value and methods. It is called reference types because we do not deal with the values directly, but merely a reference of that value.

A null reference just indicates that an object does not have any value. It is not an object and has not storage in the memory location. 

### Equality

For primitive types, the equality operator compares the actual values of the primitives. If two primitives have the same value, they are considered equal.

```
int a = 5;
int b = 5;

if (a == b) { // This will be true because the values are the same
    System.out.println("a and b are equal.");
}
```

For reference types (objects), the equality operator compares the references, not the object content. It checks to see if both references point to the exact same object in memory.

```
String x = new String("hello");
String y = new String("hello");

if (x == y) { // This will be false because x and y refer to different objects
    System.out.println("x and y are the same object.");
}

if (x.equals(y)) { // This will be true because x and y have the same content
    System.out.println("x and y are equal.");
}
```

The equals() method is used to compare the content of the objects. 

```
public class Person {
    String name;
    int age;
    
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override equals method to compare object's content
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // check if points to the same memory location
        if (obj == null || getClass() != obj.getClass()) return false; // if object is empty or different class
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }
}
```

### Assignment

For primitive types, all literal representation is immutable. Everytime we reassign a variable we are creating a copy of the value of that variable. Any changes to the copy has not effect on the actual variable.

For reference types, the behavior depends on how we assign a variable. A good example would be string literals:

```
String a = "abc";
String b = "abc";
System.out.println(s1 == s2); // true
```

String is a special reference type. It represents a sequence of characters. In the example above, s1 and s2 both refer to the same string literal "hello" in the string pool. This is a specific optimization for strings in Java that allows literals with the same content to be shared to save memory.

However, if we create the string object using new operator the memory for the new object is allocated outside the string pool. It is possible to move the string value created with the new operator to the string pool using the intern() method.

```
String a = new String("abc");
String b = new String("abc");
System.out.println(s1 == s2); // false
```

```
String o1 = new String("abc");
System.out.println("abc" == o1);          //prints: false
System.out.println("abc" == o1.intern()); //prints: true
```

For other reference types, when you assign one object reference to another, you are copying the reference, not the object itself. Both references will point to the same original object.

```
SomeClass obj1 = new SomeClass(); // obj1 refers to a new instance of SomeClass
SomeClass obj2 = obj1; // obj2 now refers to the same instance as obj1
```

After the above code executes, obj1 and obj2 refer to the same object. There is only one instance of SomeClass, and both obj1 and obj2 are references to it. Any changes made to the object through obj1 will be visible through obj2, because they are both referencing the same instance.

If you want to create a copy of an object, you need to explicitly do so, usually by implementing a copy constructor or a cloning method:

```
SomeClass obj3 = new SomeClass(obj1); // Assuming SomeClass has a copy constructor
```

### Passing

Java is strictly pass by value. When you pass a string literal or object to a function, it is pass by value. The function receives a copy of the reference of the same object not a different object or literal.

## Variables

Variable has an ID and a type. ID is the unique name of the variable, and the type represents the actual type (primitive or referenced) i.e the value and the behavior of the variable. A variable needs to be declared, initialized. Once initialized it can be assigned a value. 

```
int x; //declaration of variable x
x = 1; //initialization of variable x
x = 2; //assignment of variable x
```

Initialization and assignment look the same. The difference is in their sequence: the first assignment is called initialization. Without an initialization, a variable cannot be used. Declaration and initialization can be combined in a single statement.

### Var Type

Var is used as a type holder. It allows the compiler to figure out what type the value is during compile type. Var type needs to be initialized.

### Switch

Switch is used for control flow. In Java 14, we have a less verbose form. This form does not use a break statement.

```
switch(x) {
    case 1 -> System.out.println("Hello");
    default -> System.out.println("Cancelled");
}

switch(x) {
    case 1 -> {
        // this form can be used in case of multiple lines
    }
}
```

In Java 14, the swtich statement can also return a value thus becoming a switch expression. 