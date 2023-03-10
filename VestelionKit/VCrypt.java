package VestelionKit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.Key;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class VCrypt
{
    public static String Encrypt(String text, Key key) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedText = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedText);
    }

    public static String Decrypt(String encryptedText, Key key) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedText, "UTF-8");
    }

    public static Key GenerateKey() throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }
}
