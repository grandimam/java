Scanner is a simple text scanner that can parse primitive types and string. Buffered Reader on the other hand reads text from character input stream and is more efficient

### BufferedReader

It buffers the character in order to enable efficient reading of text. It can be used to read text from any kind of input source. It also minimizes IO operations by reading chunks of characters and store them in an internal buffer. While the buffer has data, the reader will read from it instead of directly from the underlying stream.

In general, each read request made by a Reader causes a corresponding read request to be made of the underlying character or byte stream. It is therefore advisable to wrap a BufferedReader around any Reader whose read() operations may be costly, such as FileReaders and InputStreamReaders

BuferredReader expects a Reader in its constructor to extend the reader functionaility.

```java
BufferedReader reader = 
  new BufferedReader(new FileReader("src/main/resources/input.txt"));
```

But, if buffering doesn't matter to us we could just use a *FileReader* directly:

```java
FileReader reader = 
  new FileReader("src/main/resources/input.txt");
```

**Using a stream**

BufferedReader can take any kind of input stream as an underlying source. We can do it using *InputStreamReader* and wrapping it in the constructor:

```java
BufferedReader reader = 
  new BufferedReader(new InputStreamReader(System.in));
```

### BufferedReader vs Scanner

* BR is synchronized and thread-safe

* BR can change the size of the buffer, scanner doesn't 

* BR has larger default buffer size compared to scanner

* 

### Reading Files

**BufferedReader**

To read a large file we can use the BufferedReader.

```java
@Test
public void whenReadWithBufferedReader_thenCorrect()
  throws IOException {
     String expected_value = "Hello, world!";
     String file ="src/test/resources/fileTest.txt";

     BufferedReader reader = new BufferedReader(new FileReader(file));
     String currentLine = reader.readLine();
     reader.close();

    assertEquals(expected_value, currentLine);
}
```

### Writing Files

We'll make use of *BufferedWriter*, *PrintWriter*, *FileOutputStream*, *DataOutputStream*, *RandomAccessFile*, *FileChannel*
