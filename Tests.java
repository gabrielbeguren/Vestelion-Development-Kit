package org.example;

import VestelionKit.VPromise;
import VestelionKit.VConsole;
import VestelionKit.VCrypt;

import java.security.Key;

public class Tests {
    public static void main(String[] args) throws Exception {
        //-------------------------------------------------------------------------------------------------------------------
        //Tests for VCrypt
        //-------------------------------------------------------------------------------------------------------------------

        Key key = VCrypt.generateKey();
        String originalText = "Hello, I am a sample text";

        // Encrypt the text
        String encryptedText = VCrypt.encrypt(originalText, key);
        VConsole.WriteLine("Original text: %s", originalText);
        VConsole.WriteLine("Encrypted text: %s", encryptedText);

        // Decrypt the text
        String decryptedText = VCrypt.decrypt(encryptedText, key);
        VConsole.WriteLine("Decrypted text: %s", decryptedText);

        // Check if the original text and decrypted text are equal
        VConsole.WriteLine("Are the original text and decrypted text equal? %s", originalText.equals(decryptedText));

        //-------------------------------------------------------------------------------------------------------------------
        //Tests for VPromise
        //-------------------------------------------------------------------------------------------------------------------

        VPromise.Run(
            //The first lambda is the code we want to run async (try)
            () -> {
            VConsole.WriteLine("Executing code asynchronously...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            VConsole.WriteLine("Asynchronous code finished.");
        },
        //The second lambda is the code we want to be executed in case of Exception in the first lambda (catch)
        ex -> {
            // Handle exception
            System.err.println("An error occurred: " + ex.getMessage());
        },
        //The third lambda is the code we want to run ALWAYS after the previous lambdas (finally)
        () -> {
            VConsole.WriteLine("The promise has completed.");
        });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        VConsole.Write("End of program");
    }
}
