Exceptions are abnormal behaviors during the execution of the program. Exceptions are just objects with all of them 
extending from Throwable. Errors are unrecoverable conditions such as OOM, Memory leaks, etc. 

```
              ---> Throwable <--- 
              |    (checked)     |
              |                  |
              |                  |
      ---> Exception           Error
      |    (checked)        (unchecked)
      |
RuntimeException
  (unchecked)
```

There are three main categories of exceptions - 

* Checked exceptions

* Unchecked exceptions / Runtime Exceptions

* Errors

If a client can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do 
anything to recover from the exception, make it an unchecked exception. For example, before we open a file, we can first
validate the input file name. If the user input file name is invalid, we can throw a custom checked exception: 

```java
if (!isCorrectFileName(fileName)) {
    throw new IncorrectFileNameException("Incorrect filename : " + fileName );
}
```

In this way, we can recover the system by accepting another user input file name. However, if the input file name is a 
null pointer or it is an empty string, it means that we have some errors in the code. In this case, we should throw an 
unchecked exception:

```java
if (fileName == null || fileName.isEmpty())  {
    throw new NullOrEmptyException("The filename is null or empty.");
}
```

**Checked**

Checked exceptions are exceptions that are outside the control of our program, and the compiler requires us to handle by
either throwing the exception up the call stack or catching it. We can use the throws keyword to declare a checked exception. 

Eg. IOException, ServletException. 

**Unchecked**

Unchecked exceptions are exceptions that represents some error in the program logic. The compiler does not handle these 
exceptions, and we do not have to declare the exception using the throws keyword. Exceptions that extends RuntimeException
is unchecked.

Eg. NullPointerException, IllegalArgumentException.

**Errors**

Errors are irrecoverable conditions like library incompatability, memory leaks, recursion, etc. They are also unchecked 
exception even though it is not runtime. 

Eg. StackOverflowError, OutOfMemoryError.

**Throw vs Throws**

Throw is used to throw the exception up the call stack. Throws is a keyword used next to the method to highlight that 
method throws an exception. 

## Handling Exceptions

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

## Throwing Exceptions

If we do not want to handle the exceptions or want to generate our own exceptions for others to handle we can use the throw keyword. 

```java
public class TimeoutException extends Exception {
    public TimeoutException(String message) {
        super(message);
    }
}
```

**Checked Exception**

Throwing a checked exception is as simple as using the throw keyword

```java
public List<Player> loadAllPlayers(String playersFile) throws TimeoutException {
    while ( !tooLong ) {
        // ... potentially long operation
    }
    throw new TimeoutException("This operation took too long");
}
```

**Unchecked Exception**

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

## Exception Handling and Overriding

- If SuperClass does not declare an exception, then the SubClass can only declare unchecked exceptions, but not the checked exceptions.
- If SuperClass declares an exception, then the SubClass can only declare the same or child exceptions of the exception declared by the SuperClass and any new Runtime Exceptions, just not any new checked exceptions at the same level or higher.
- If SuperClass declares an exception, then the SubClass can declare without exception.
