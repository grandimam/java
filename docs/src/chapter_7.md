Lambda expressions are instances of functional interface. A functional interface is an interface with only one abstract method (java.lang.Runnable). They provide the below functionalities.

- Use functions as method arguments
- A function that can be created without belonging to any class.
- A lambda expression can be passed around as if it was an object and executed on demand.

### Stream

Stream API is used to process collections of objects. It can be created from collections or array using stream() and of() methods. 

```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
stream = Stream.of("a", "b", "c");
```

### Multithreading

Stream allows for easy multithreading using parallelStream()

```java
list.parallelStream().forEach(element -> doWork(element));
```

### Operations

Operations are divided into intermediate operations and terminal operations. Intermediate Operations are the types of operations in which multiple methods are chained in a row.

#### Important Intermediate Operations

1. map - Used to apply a function to each element in the array
2. filter - Used to select methods based on a predicate
3. sorted - Used to sort an element
4. matching - Allows for validating elements based on some predicate. 

```java
boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
```

#### Terminal Operations

1. collect - return the result of the intermediate operations performed on the stream
2. forEach - forEach method is used to iterate through every element of the stream
3. reduce - reduce method is used to reduce the elements of a stream to a single value

```
arr.stream().reduce(0, (a, b) -> a + b);
arr.stream().reduce((a, b) -> a + b);
```

### Method Reference

A method reference is the shorthand syntax for a lambda expression that contains just one method call. There are four type method references that are as follows:

1. Static Method Reference.
2. Instance Method Reference of a particular object.
3. Instance Method Reference of an arbitrary object of a particular type.
4. Constructor Reference.

### Stream Reference

Stream can be reference as long as intermediate operations are called once a terminal operation is called stream cannot be reused. 

```java
Stream<String> stream = 
  Stream.of("a", "b", "c").filter(element -> element.contains("b"));
Optional<String> anyElement = stream.findAny();
Optional<String> firstElement = stream.findFirst(); // IllegalStateException
```

Intermediate operations return a new modified stream.  stream by itself is worthless; the user is interested in the result of the terminal operation, which can be a value of some type or an action applied to every element of the stream. We can only use one terminal operation per stream.

Stream intermediate operations are lazy invocations and they will be invoked only if it is necessary for the terminal operation execution. 

```java
List<String> list = Arrays.asList(“abc1”, “abc2”, “abc3”);
counter = 0;
Stream<String> stream = list.stream().filter(element -> {
    wasCalled();
    return element.contains("2");
});
```

### Optional

Optional is a container object which may or may not contain a value. If a value is present, isPresent() will return true and get() will return the value. Additional methods that depend on the presence or absence of a contained value are provided, such as orElse() which returns a default value if the value is not present, and ifPresent() which executes a block of code if the value is present. 