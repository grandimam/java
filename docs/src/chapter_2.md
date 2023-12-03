Collection framework holds all the collection classes and interface in it. The collection interface is the base interface that all other collection.

### Iterable and Iterator

Collection interface extends the iterable interface. Any class that implements the collection interface directly or indirectly inherits the iterable interface as well. Iterable has three methods - 

* Iterator<T> iterator(): returns the iterator object

```
Iterable<String> lst = List.of("a", "b")

for (String e: lst) {
    System.out.println(e)
}
```

* default void forEach (Consumer<? super T> function):

### Comparable vs Comparator

Comparable and Comparator are interfaces to sort collection objects. Comparable is used to compare object by itself whereas Comparator is a external to the element we are comparing. Collections has a sort() method that takes a comparator. 

Comparable is meant for objects with natural ordering which means the object itself must know how it is to be ordered. A basic differentiating feature is that using comparable we can use only one comparison. Whereas, we can write more than one custom comparators as you want for a given type, all using different interpretations of what sorting mean

Allows for comparing custom object types that aren't directly comparable. 

```java
public class Player implements Comparable<Player> {

    // same as before

    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(getRanking(), otherPlayer.getRanking());
    }

}
```

```java
public class DiscoveredAssetComparator implements Comparator<DiscoveredAssetRequest> {
    @Override
    public int compare(DiscoveredAssetRequest o1, DiscoveredAssetRequest o2) {
        return 0;
    }
}
```

Sorting order is decided by the return value of compareTo() method. The method returns a number indicating whether the object being compared is less, equal, or greater than the object being passed. 

Comparable interface is a good choice to use for defining default ordering of that object. Comparator requires us to create separate independent comparator class for comparison logic.

Java provides two interfaces to sort objects using data members of the class:

* Comparable

* Comparator

A comparable object is capable of comparing itself with another object. The class itself must implements the **java.lang.Comparable** interface to compare its instances.

Unlike Comparable, Comparator is external to the element type we are comparing. It’s a separate class. We create multiple separate classes (that implement Comparator) to compare by different members.

Comparable is meant for objects with natural ordering which means the object itself must know how it is to be ordered. For example Roll Numbers of students. Whereas, Comparator interface sorting is done through a separate class.

Logically, Comparable interface compares “this” reference with the object specified and Comparator in Java compares two different class objects provided.

If any class implements Comparable interface in Java then collection of that object either List or Array can be sorted automatically by using Collections.sort() or Arrays.sort() method and objects will be sorted based on there natural order defined by CompareTo method.

A basic differentiating feature is that using comparable we can use only one comparison. Whereas, we can write more than one custom comparators as you want for a given type, all using different interpretations of what sorting means. Like in the comparable example we could just sort by only one attribute, i.e., year but in the comparator, we were able to use different attributes like rating, name, and year as well.


## Hashset

Stores unique elements and permits null and doesn't maintain insertion order and not thread-safe. Internally a hashmap gets initialized when hashset is created. 

When an object is inserted into a HashSet, we first check the object's hashcode value to determined if its already available in the set. Each hashcode value corresponds to a certain bucket location which contain various elements. 

Objects within the same buckets will be compared using equals() method. 

### Hashmap

For maps to work properly we need to provide an implementation of equals() and hashcode(). These methods need to be overriden only for classes that want to be used a map keys not for classes that will be used as values. 

Hashmap calculates the position of based on the hashcode. The keys are stored in buckets and the number of buckers is called capacity. The hashcode method is used to determin the bucket of the key where the value needs to be inserted. 

An important aspect of the key is that it should be immutable. 

```java
public class MutableKey {
    private String name;

    // standard constructor, getter and setter

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutableKey that = (MutableKey) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
```

```java
MutableKey key = new MutableKey("initial");

Map<MutableKey, String> items = new HashMap<>();
items.put(key, "success");

key.setName("changed");

assertNull(items.get(key));
```

We're no longer able to get the corresponding value once the key has changed, instead, *null* is returned. This is because *HashMap* is searching in the wrong bucket

**Collisions**

Similar keys need to have the same hashcode thus belonging to the same bucket. The complexity to find the key inside the bucket is O(N) as List is used as bucket.

**Initializer**

Using static initializer

```java
public static Map<String, String> articleMapOne;
static {
    articleMapOne = new HashMap<>();
    articleMapOne.put("ar01", "Intro to Map");
    articleMapOne.put("ar02", "Some article");
}
```

**HashSet vs LinkedHashSet vs TreeSet**

* HashSet does not maintain any order, while LinkedHashSet maintains insertion order.
* HashSet is typically faster and has less memory overhead than LinkedHashSet.
* TreeSet guarantees natural ordering of things, and also has a comprator

### LinkedList

It is a doubly linked list implementation of the List and Deque interfaces. LinkedList permits null values.

* It is not synchronized

* If a list is modified while iteration will result in ConcurrentModificationException