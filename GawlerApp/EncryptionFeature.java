import java.security.NoSuchAlgorithmException;
import java.util.Base64;    
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;  
/**
 * This encryption code was 
 * obtanied from --SunJCE AES algorithm--
 * However, it was modified to suite the 
 * purpose of this project
 * @author Carlos
 *
 */
public class EncryptionFeature {
	static Cipher cipher;
	static SecretKey secretKey = null;
	
	/* 
    create key 
    If we need to generate a new key use a KeyGenerator
    If we have existing plaintext key use a SecretKeyFactory
   */ 
	public static void generateSecretKey() 
			throws Exception{
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        secretKey = keyGenerator.generateKey();
        
        /*
        Cipher Info
        Algorithm : for the encryption of electronic data
        mode of operation : to avoid repeated blocks encrypt to the same values.
        padding: ensuring messages are the proper length necessary for certain ciphers 
        mode/padding are not used with stream cyphers.  
       */
        cipher = Cipher.getInstance("AES");
	}
	
	
	public static String encrypt(String plainText)
            throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        System.out.println("encryption: " + encryptedText);
        return encryptedText;
    }

    public static String decrypt(String encryptedText)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        System.out.println("encryption: " + decryptedText);

        return decryptedText;
    }
}
