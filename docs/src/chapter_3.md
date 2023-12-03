Java has two units of execution - process and threads. A process represents the whole JVM. And it can have many threads running and at least one thread called the main thread—the one that starts the application.

Daemon threads run as a background process rather than interacting directly with the user. Daemon thread exit immediately after the last user thread exits or are terminated by the jvm.

## Thread

Implementing a runnable interface allows for implementing multiple classes. Whereas Thread class can be extended only once. Threads and any objects can also talk to each other using the wait(), notify(), and notifyAll() methods of the java.lang.Object base class.

Thread has following state - new, runnable, blocked, waiting, timed waiting, and terminated. When a program calls the start method, it creats a thread and then calls the run() method.

### Creating a Thread

Thread can be created by extending the thread class.

```
import java.lang.Thread;

public class Person extends Thread {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        
    }
}
```

### Implementing Runnable Interface

The second way to create a thread is to use a class that implements java.lang. Runnable. Here is an example of such a class that has almost exactly the same functionality as the MyThread.

```
import java.lang.Thread;

public class Person implements Runnable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        
    }
}
```

### Thread Safety

Multithreading is a process of running multiple threads simultaneously. When multiple threads are working on the same data that scenario is not thread-safe and we will get inconsistent results. There are four ways to achieve Thread Safety in Java. These are:

1. Using Synchronization
2. Using Volatile Keyword
3. Using Atomic Variable
4. Using Final Keyword

### Synchronization

Synchronization is a process in which a single thread accesses a shared object or data. It is used to achieve thread safety. Here are the key aspects of synchronization in Java:

1. Synchronized Methods: You can declare an entire method as synchronized, which will require a thread to obtain a lock on the object's monitor before executing the method. This prevents other threads from entering any of the object's synchronized methods until the lock is released.

```java
public class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

2. Synchronized Blocks: Instead of synchronizing an entire method, you can synchronize a block of statements within a method. This often leads to better performance, as it reduces the amount of time that the lock is held.

```java
public class Counter {
    private int count = 0;
    private final Object lock = new Object();
    
    public void increment() {
        synchronized(lock) {
            count++;
        }
    }
}
```

3. Static Synchronization: When you want to synchronize a static method, you are actually synchronizing on the class's Class object, not an instance of the class. This means that the static method is synchronized across all instances of that class.

```java
public class Utils {
    private static int helper = 0;
    
    public static synchronized void doSomething() {
        helper++;
        // Some other operations
    }
}
```

4. The volatile Keyword: This is a lighter form of synchronization that ensures that the value of a variable will be read from and written to main memory, and not cached thread-locally. It's used for variables that are accessed by multiple threads without the need for mutual exclusion.

The volatile keyword in Java is used to indicate that a variable's value will be modified by different threads. Declaring a variable as volatile means that changes made by one thread to a volatile variable are always visible to other threads immediately. Volatile variable is not atomic! 

```java
public class Flag {
    private volatile boolean flag = true;
    
    public void setFalse() {
        flag = false;
    }
    
    public boolean checkFlag() {
        return flag;
    }
}
```

5. Locks and Condition Variables: The java.util.concurrent.locks package provides a framework for lock implementation. The ReentrantLock class is an implementation of the Lock interface that allows locking and unlocking, with the same thread able to acquire the lock multiple times (reentrantly). Condition variables can be used to create conditions on which threads can wait, providing a more advanced way to organize inter-thread signaling.

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SharedResource {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    
    public void method() {
        lock.lock();
        try {
            // access the resource
            condition.await(); // wait on some condition
            condition.signal(); // signal another thread
        } finally {
            lock.unlock();
        }
    }
}
```

## Deadlock

A deadlock is a situation where two or more threads are blocked waiting for each other.

## Executor

It is an interface that represents an object to execute tasks. Depending on the implementation the task can run on a new or a current thread. 

```java
public class Invoker implements Executor {
    @Override
    public void execute(Runnable r) {
        r.run();
    }
}

public void execute() {
    Executor executor = new Invoker();
    executor.execute( () -> {
        // task to be performed
    });
}
```

### Executor Service

Provides a complete mechanism for asynchronous processing. It manages an in-memory queue and schedules tasks based on the thread availability. 

```java
public class Task implements Runnable {
    @Override
    public void run() {
        // task details
    }
}

ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(new Task());
```

Executor Service can be terminated using shutdown(), this waits until all the tasks are finished. And shutdownNow() which terminates immediately. 

### Scheduled Executor Service

Scheduled Executor Service can perform the tasks periodically. 

### Future

Used to represent the result of the asynchronous operation. 

```java
public void invoke() {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    Future<String> future = executorService.submit(() -> {
        // ...
        Thread.sleep(10000l);
        return "Hello world";
    });
}
```

### Semaphore

Used for blocking thread level access to some part of the physical or logical resource. Semaphore contains permits, when a thread tries to enter the critical section it needs to check with the semaphore if a permit is available or not. 

Permit is acquired using tryAcquire() if not acquired the thread is not permited to the section. 
