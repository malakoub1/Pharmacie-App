package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordUtil {

    
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return toHex(salt);
    }

    public static String hashPassword(String plainPassword, String saltHex) {
        if (plainPassword == null) plainPassword = "";
        if (saltHex == null) saltHex = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest((saltHex + plainPassword).getBytes(StandardCharsets.UTF_8));
            return toHex(hashed);
        } catch (Exception e) {
            throw new RuntimeException("Erreur hash", e);
        }
    }

    public static boolean verifyPassword(String plainPassword, String saltHex, String expectedHash) {
        String computed = hashPassword(plainPassword, saltHex);
        return computed.equalsIgnoreCase(expectedHash);
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}