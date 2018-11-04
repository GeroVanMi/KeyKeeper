package encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
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

    /*/**
     * Encrypts a given text with the SHA-256 algorithm.
     * @param text The text that has to be encrypted.
     * @return
     * @throws Exception
     */
    /*public String encrypt(String text) {
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
    }*/

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
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException | InvalidParameterSpecException e) {
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
        Hasher hasher = new Hasher();
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] key = algorithm.digest(hasher.applyMD5(password));

        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        return new String(cipher.doFinal(encryptedText));
    }

    public static void main(String[] args) {
        CryptionManager cm = new CryptionManager("1234");
        System.out.println(cm.encrypt("1234".toCharArray(), "hello".getBytes()));
    }
}
