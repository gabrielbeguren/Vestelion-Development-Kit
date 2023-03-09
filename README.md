# VestelionKit

## VPromise class

### Run(Runnable task, Consumer<Throwable> exceptionHandler)
    
This method allows you to run a given task asynchronously using a CompletableFuture. It takes two parameters:

*  **task**: a Runnable that represents the code to be executed asynchronously.
*  **exceptionHandler**: a Consumer<Throwable> that is called if an exception occurs during the execution of the task.
    
Example usage:

```
VPromise.Run(() -> {
    // Asynchronous task to execute
    System.out.println("Executing asynchronous code...");
}, ex -> {
    // Exception handler
    System.err.println("An exception occurred: " + ex.getMessage());
});
```    
    
### Run(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)
    
This method also runs a task asynchronously using a CompletableFuture, but it allows you to specify a completion task to run after the main task has completed. It takes three parameters:

* **task**: a Runnable that represents the main task to be executed asynchronously.
* **exceptionHandler**: a Consumer<Throwable> that is called if an exception occurs during the execution of the task.
* **completionTask**: a Runnable that is executed when the main task has completed successfully.
    
Example usage:

```
VPromise.Run(() -> {
    // Asynchronous task to execute
    System.out.println("Executing asynchronous code...");
}, ex -> {
    // Exception handler
    System.err.println("An exception occurred: " + ex.getMessage());
}, () -> {
    // Completion task
    System.out.println("Asynchronous task completed successfully.");
});
```    
    
## VCrypt class
    
### encrypt(String text, Key key)
    
This method encrypts a given text using a symmetric key algorithm. It takes two parameters:

* **text**: the text to encrypt.
* **key**: the key to use for encryption.
    
Example usage:

```
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

```
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

```
Key key = VCrypt.generateKey();
```
    
## VConsole class

### Write(String format, Object... args)
    
This method writes a formatted string to the console, without appending a new line character. It takes a format string and a variable number of arguments.

Example usage:

```
VConsole.Write("This is a formatted %s", "string");
```
    
### WriteLine(String format, Object... args)
    
This method writes a formatted string to the console, appending a new line character. It takes a format string and a variable number of arguments.

Example usage:

```
VConsole.WriteLine("This is a formatted %s", "string");
```
