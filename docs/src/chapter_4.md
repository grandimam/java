# Exceptions

Exception are abnormal behaviors that happens during the execution of the program. Errors are unrecoverable conditions such as OOM, etc. They are both subclasses of Throwable, and can be categorized into two main types: checked and unchecked exceptions.

### Checked Exceptions

Exceptions that are checked at compile-time are checked exceptions. Compiler requires that you handle these exceptions with a try-catch block or by declaring them with the throws keyword in the method signature. They exceptions are subclasses of Exception, but they do not extend RuntimeException.

* IOException
* FileNotFoundException
* ClassNotFoundException
* SQLException

Throwing a checked exception is as simple as using the throw keyword

```java
public List<Player> loadAllPlayers(String playersFile) throws TimeoutException {
    while ( !tooLong ) {
        // ... potentially long operation
    }
    throw new IOException("This operation took too long");
}
```

### Unchecked Exceptions

Exceptions that are checked at run-time are unchecked exceptions. They are subclasses of RuntimeException, and include errors which are exceptions that the application should not try to handle. Unchecked exceptions are not checked at compile-time, the compiler does not require these exceptions to be caught or declared in the method signature. 

Examples of unchecked exceptions include:

* NullPointerException
* ArithmeticException
* IllegalArgumentException
* IndexOutOfBoundsException

We do not have to mark the method for unchecked exception. 

```java
public List<Player> loadAllPlayers(String playersFile) {
    if(!isFilenameValid(playersFile)) {
        throw new IllegalArgumentException("Filename isn't valid!");
    }
}
```

If the only possible exception that a given block of code could raise are unchecked exceptions then we can catch and rethrow Throwable or Exception without adding the method signature. 

```java
public List<Player> loadAllPlayers(String playersFile) {
    try {
        throw new NullPointerException();
    } catch (Throwable t) {
        throw t;
    }
}
```

### Errors

An Error represents a serious problem that a reasonable application should not try to catch, usually reflecting an error in the runtime environment. Examples include OutOfMemoryError, StackOverflowError, and VirtualMachineError.

It is possible to catch an Error if you have a good reason to do so, but it's typically a bad idea because it can lead to unpredictable states and make debugging much more difficult.

### Throw vs Throws

Throw is used to throw the exception up the call stack. Throws is a keyword used next to the method to highlight that method throws an exception. 

### Ways to handle exceptions

**Throws**

Simple way is to throw it. 

```java
public int getPlayerScore(String playerFile) throws FileNotFoundException {

    Scanner contents = new Scanner(new File(playerFile));
    return Integer.parseInt(contents.nextLine());
}
```

FileNotFoundException is a checked exception but anyone using our method must handle it. Also, *parseInt* can throw a *NumberFormatException*, but because it is unchecked, we aren't required to handle it.

**Try-catch**

To handle the exception ourselves we can just use the try-catch block. 

```
public int getPlayerScore(String playerFile) {
    try {
        Scanner contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException noFile) {
        throw new IllegalArgumentException("File not found");
    }
}
```

**Finally**

Finally keyword is used to execute the code regardless whether an exception occurs and not. Even if a *FileNotFoundException* is thrown up the call stack, Java will call the contents of *finally* before doing that. We can also both handle the exception *and* make sure that our resources get closed:

```java
public int getPlayerScore(String playerFile)
  throws FileNotFoundException {
    Scanner contents = null;
    try {
        contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } finally {
        if (contents != null) {
            contents.close();
        }
    }
}

// exception is both handled and finally is executed
public int getPlayerScore(String playerFile) {
    Scanner contents;
    try {
        contents = new Scanner(new File(playerFile));
        return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException noFile ) {
        logger.warn("File not found, resetting score.");
        return 0; 
    } finally {
        try {
            if (contents != null) {
                contents.close();
            }
        } catch (IOException io) {
            logger.error("Couldn't close the reader!", io);
        }
    }
}
```

**try-with-resources**

It allows us to declare resources to be used in a try-block with assurance that the resources will be closed after the execution of the block. Then, we can add the finally block to do other type of clean up. 

```java
public int getPlayerScore(String playerFile) {
    try (Scanner contents = new Scanner(new File(playerFile))) {
      return Integer.parseInt(contents.nextLine());
    } catch (FileNotFoundException e ) {
      logger.warn("File not found, resetting score.");
      return 0;
    }
}
```

Using multiple resources in:

```java
try (Scanner scanner = new Scanner(new File("testRead.txt"));
    PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
    while (scanner.hasNext()) {
    writer.print(scanner.nextLine());
    }
}
```

Using custom autocloseable resource:

```java
public class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("Closed MyResource");
    }
}
```

We can also use final variables inside the try-with-resource block.

## Exception Handling and Overriding

- If Superclass does not declare an exception, then the subclass can only declare unchecked exceptions
- If Superclass declares an exception, then the subclass can only declare the same or child exceptions of the exception any new Runtime Exceptions, just not any new checked exceptions at the same level or higher
- If SuperClass declares an exception, then the SubClass can declare without exception.
