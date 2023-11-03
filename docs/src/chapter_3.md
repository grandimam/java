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
