package encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    public byte[] applyMD5(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return messageDigest.digest(text.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
