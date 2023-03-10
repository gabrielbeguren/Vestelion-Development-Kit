
import VestelionKit.VPromise;
import VestelionKit.VConsole;
import VestelionKit.VCrypt;

import java.security.Key;

public class Tests 
{
    public static void main(String[] args) throws Exception
    {
        // Test VPromise class
        VPromise.RunAsync(() -> {
            // This task will succeed
            VConsole.WriteLine("Task running successfully");
        }, ex -> {
            // This exception handler won't be called
            VConsole.WriteLine("Exception handler called with exception: %s", ex.getMessage());
        }, () -> {
            // This completion task will be called
            VConsole.WriteLine("Completion task called");
        });

        VPromise.RunAsync(() -> {
            // This task will throw an exception
            throw new RuntimeException("Task failed");
        }, ex -> {
            // This exception handler will be called with the thrown exception
            VConsole.WriteLine("Exception handler called with exception: %s", ex.getMessage());
        });

        // Test VCrypt class
        Key key = VCrypt.generateKey();
        String originalText = "Hello, world!";
        String encryptedText = VCrypt.encrypt(originalText, key);
        String decryptedText = VCrypt.decrypt(encryptedText, key);
        VConsole.WriteLine("Original text: %s", originalText);
        VConsole.WriteLine("Encrypted text: %s", encryptedText);
        VConsole.WriteLine("Decrypted text: %s", decryptedText);
    }
}
