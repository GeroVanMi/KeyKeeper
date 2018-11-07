package encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * This class hold methods to en- and decrypt text.
 */
public class CryptionManager {

    private String password;

    /**
     * Constructor-method for the Cryption Manager. Sets the password attribute.
     * @param password The password attribute is used for the SHA-256 algorithm.
     */
    public CryptionManager(String password) {
        this.password = password;
    }

    /**
     * Encrypts a given text with the SHA-256 algorithm.
     * @param text The text that has to be encrypted.
     * @return Returns the encrypted text as a Base64
     */
    public String encrypt(String text) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] key = algorithm.digest(password.getBytes());

            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(text.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }/*

    public String encrypt(char[] password, byte[] salt) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();
            byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] ciphertext = cipher.doFinal("Hello, World!".getBytes("UTF-8"));

            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * Decrypts a given text that is encoded with the SHA-256 algorithm.
     * @param encodedText The text that is in encoded form and has to be decrypted.
     * @return Returns the decrypted String.
     */
    public String decrypt(String encodedText) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encryptedText = decoder.decode(encodedText);

            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] key = algorithm.digest(password.getBytes());

            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            return new String(cipher.doFinal(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypts a given byte-array with the SHA-256 algorithm.
     * @param encryptedText An encrypted byte-array that has to be decrypted.
     * @return Returns the decrypted String.
     */
    public String decrypt(byte[] encryptedText) {
        try {
            Hasher hasher = new Hasher();
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] key = algorithm.digest(hasher.applyMD5(password));

            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            return new String(cipher.doFinal(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
