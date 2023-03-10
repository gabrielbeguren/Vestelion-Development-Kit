# VestelionKit

VestelionKit is a software development kit that provides several classes for developing applications. These classes include VPromise, VCrypt, and VConsole.

## VPromise Class

The VPromise class is a utility class that provides a simple way to execute tasks asynchronously using CompletableFuture. It allows you to define a task, an exception handler, and an optional completion task. When the task is executed, the exception handler will be called if an exception occurs. If a completion task is defined, it will be executed when the task is completed successfully.

### Class Constructor

The VPromise class provides two constructors:

* **VPromise(Runnable task, Consumer<Throwable> exceptionHandler)**: Creates a new instance of VPromise with the given task and exception handler. The completion task is set to null.

* **VPromise(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)**: Creates a new instance of VPromise with the given task, exception handler, and completion task.
Both constructors throw a NullPointerException if any of the arguments is null.

## Methods

### void RunAsync(Runnable task, Consumer<Throwable> exceptionHandler)</u>

Executes the given task asynchronously and handles any exceptions using the given exception handler.
    
Throws a NullPointerException if any of the arguments is null.
    
Example usage:
```Java
VPromise.RunAsync(() -> {
    // Execute some long-running task here
}, ex -> {
    // Handle any exceptions that occur
});
```

### static void RunAsync(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)

Executes the given task asynchronously, handles any exceptions using the given exception handler, and executes the given completion task when the task is completed successfully.

Throws a NullPointerException if any of the arguments is null.
    
Example usage:
```Java
VPromise.RunAsync(() -> {
// Execute some long-running task here
}, ex -> {
    // Handle any exceptions that occur
}, () -> {
    // Execute this task when the main task is completed successfully
});
```

### void RunAsync()

Executes the task asynchronously, handles any exceptions using the defined exception handler, and executes the defined completion task if it exists.
    
Throws a NullPointerException if any of the arguments is null.
    
Example usage:
```Java
VPromise promise = new VPromise(() -> {
// Execute some long-running task here
}, ex -> {
    // Handle any exceptions that occur
}, () -> {
    // Execute this task when the main task is completed successfully
});

promise.RunAsync();
```
    
## VCrypt class

The VCrypt class provides methods to encrypt and decrypt text using the AES encryption algorithm. It also provides a method to generate a secret key that can be used for encryption and decryption.
    
### encrypt(String text, Key key)
    
This method encrypts a given text using a symmetric key algorithm. It takes two parameters:

* **text**: the text to encrypt.
* **key**: the key to use for encryption.
    
Example usage:

```Java
Key key = VCrypt.generateKey();
String originalText = "Hello, this is a test text.";

String encryptedText = VCrypt.encrypt(originalText, key);
System.out.println("Original text: " + originalText);
System.out.println("Encrypted text: " + encryptedText);
```    
    
### decrypt(String encryptedText, Key key)
    
This method decrypts an encrypted text using a symmetric key algorithm. It takes two parameters:

* **encryptedText**: the text to decrypt.
* **key**: the key to use for decryption.
    
Example usage:

```Java
Key key = VCrypt.generateKey();
String originalText = "Hello, this is a test text.";

String encryptedText = VCrypt.encrypt(originalText, key);
String decryptedText = VCrypt.decrypt(encryptedText, key);

System.out.println("Original text: " + originalText);
System.out.println("Encrypted text: " + encryptedText);
System.out.println("Decrypted text: " + decryptedText);
```
    
### generateKey()
    
This method generates a random symmetric key. It takes no parameters.

Example usage:

```Java
Key key = VCrypt.generateKey();
```
    
## VConsole class

### Write(String format, Object... args)
    
This method writes a formatted string to the console, without appending a new line character. It takes a format string and a variable number of arguments.

Example usage:

```Java
VConsole.Write("This is a formatted %s", "string");
```
    
### WriteLine(String format, Object... args)
    
This method writes a formatted string to the console, appending a new line character. It takes a format string and a variable number of arguments.

Example usage:

```Java
VConsole.WriteLine("This is a formatted %s", "string");
```
