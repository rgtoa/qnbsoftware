
package software1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Crypto {
    static byte[] key = { 
        'q','n','b','w','a','t','e','r',
        's','o','f','t','e','n','g','2'
    };
    public static String encrypt(String strToEncrypt) {
        String encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return encryptedString;
    }

    public static String decrypt(String codeDecrypt) {
        String decryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return decryptedString;
    }
    
    public static void main(String[] args) {
        System.out.print("Choose Method:\n"
                + "[1] Encrypt\n"
                + "[2] Decrypt\n> ");
        java.util.Scanner s = new java.util.Scanner(System.in);
        if(s.nextInt() == 1) {
            System.out.print("Enter text to encrypt:\n> ");
            String text = s.next();
            System.out.println("Given Text: " + text);
            System.out.println("Encrypted Text: " + encrypt(text));
        } else {
            System.out.print("Enter text to decrypt:\n> ");
            System.out.println("Encrypted Text: " + decrypt(s.next()));
        }
    }
}
