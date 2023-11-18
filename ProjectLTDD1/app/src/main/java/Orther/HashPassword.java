package Orther;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashPassword {
    public static String hashPassword(String password) {
        int iterations = 10000;
        int keyLength = 256;
        char[] passwordChars = password.toCharArray();
        byte[] salt = generateSalt();

        try {
            PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashedBytes = skf.generateSecret(spec).getEncoded();
            return iterations + ":" + bytesToHex(salt) + ":" + bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }
    // Phương thức để tạo salt ngẫu nhiên
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
