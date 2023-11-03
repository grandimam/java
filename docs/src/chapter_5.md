Using generics methods can be written with single method declaration and can be called for different types.

### Bounded Generics

Ability to restrict types of a generic method to its type and all its subclasses (upperbound) or its superclasses (lower bound). For upperbound we use extends keyword after the type followed by upperbound.

```java
public <T extends Number> List<T> fromArrayToList(T[] a) {
    ...
}
```

### Multiple Bounds

We can allow multiple bounds

```java
<T extends Number & Comparable>
```

### Wildcards

Wildcards are represented using ? and are used to mark unknown tyes. 

### Type Erasure

Type erasure is the process of removing all type parameters and replacing with their bounds or Object type in case if the types are unbound. This way, the bytecode after compilation contains only normal classes, interfaces and methods, ensuring that no new types are produced

```java
public <T> List<T> genericMethod(List<T> list) {
    return list.stream().collect(Collectors.toList());
}
```

With type erasure, the unbounded type *T* is replaced with *Object*:

```java
// for illustration
public List<Object> withErasure(List<Object> list) {
    return list.stream().collect(Collectors.toList());
}

// which in practice results in
public List withErasure(List list) {
    return list.stream().collect(Collectors.toList());
}
```

If the type is bounded, the type will be replaced by the bound at compile time:

```java
public <T extends Building> void genericMethod(T t) {
    ...
}

// and would change after compilation:

public void genericMethod(Building t) {
    ...
}

```

Generics are a compile-time feature, meaning the type parameter is erased and all generic types are implemented as type *Object*.

```java
List<Integer> list = new ArrayList<>();
list.add(17);
```

Therefore, type parameters must be convertible to *Object*. **Since primitive types don't extend *Object*, we can't use them as type parameters.** However, Java provides boxed types for primitives, along with autoboxing and unboxing to unwrap them:


