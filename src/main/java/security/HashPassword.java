package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    public static String getHashPassword(String password) throws NoSuchAlgorithmException, NullPointerException, UnsupportedEncodingException {

        if (password == null){
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = messageDigest.digest();
        if (digest == null){
            return null;
        }
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }

}
