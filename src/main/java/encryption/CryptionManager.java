package encryption;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
     * @return
     * @throws Exception
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
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypts a given text that is encoded with the SHA-256 algorithm.
     * @param encodedText The text that is in encoded form and has to be decrypted.
     * @return
     * @throws Exception
     */
    public String decrypt(String encodedText) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedText = decoder.decode(encodedText);

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] key = algorithm.digest(password.getBytes());

        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        return new String(cipher.doFinal(encryptedText));
    }

    /**
     * Decrypts a given byte-array with the SHA-256 algorithm.
     * @param encryptedText An encrypted byte-array that has to be decrypted.
     * @return
     * @throws Exception
     */
    public String decrypt(byte[] encryptedText) throws Exception {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] key = algorithm.digest(password.getBytes());

        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        return new String(cipher.doFinal(encryptedText));
    }
}
