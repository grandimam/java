Lambda expressions are functional interfaces. An interface with single abstract method is called functional interface (java.lang.Runnable). They provide below functionalities.

- Use functions as method arguments
- A function that can be created without belonging to any class.
- A lambda expression can be passed around as if it was an object and executed on demand.

### Stream

Stream API is used to process collections of objects. Stream package is for processing sequence of elements. Stream can 
be created from collections or array using stream() and of() methods. 

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

Operations are divided into intermediate operations and terminal operations. Former allows chaining.

```java
long count = list.stream().distinct().count();
```

**Filtering**

Allows for filtering elements using a Predicate.

**Mapping**

Collect a stream by applying special function to them and collect these new elements. 

**Matching**

Allows for validating elements based on some predicate. 

```java
boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
```

**Reducing**

Allows for reducing a sequence of elements to some value according to a specific function. 

**Collecting**

Allows for collecting the stream to a Collection or Map.

```java
List<String> resultList 
  = list.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
```

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

## Functional Interfaces

An interface with a single abstract method is a functional interface. Its implementation may be treated as lambda expressions. 

