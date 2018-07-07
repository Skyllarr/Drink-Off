package com.violetbutterfly.drinkoff.service;

import com.violetbutterfly.drinkoff.api.exception.UserAuthenticationException;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public class AuthenticationUtils {

    public static String createHash(String unencryptedPassword) throws UserAuthenticationException {

        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 18;
        final int PBKDF2_ITERATIONS = 1000;

        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(unencryptedPassword.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        int hashSize = hash.length;

        return PBKDF2_ITERATIONS + ":" + AuthenticationUtils.toBase64(salt) + ":" + AuthenticationUtils.toBase64(hash);
    }

    private static byte[] fromBase64(String hex)
            throws IllegalArgumentException {

        return DatatypeConverter.parseBase64Binary(hex);
    }

    private static String toBase64(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }

    public static boolean verifyPassword(String password, String correctHash) throws UserAuthenticationException {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        if (correctHash == null) {
            throw new IllegalArgumentException("password hash is null");
        }

        String[] params = correctHash.split(":");

        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromBase64(params[1]);
        byte[] hash = fromBase64(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

        private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws UserAuthenticationException {

        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException ex) {
            throw new UserAuthenticationException("Authentication error", ex);
        }
    }
}
