package com.example.mongodb.core.util;

import com.example.mongodb.core.exception.CustomException;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import static java.lang.String.format;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

@Slf4j
public class SecurityUtils {
    private SecurityUtils() {
    }

    public static byte[] generateKey(String algorithm, int keySize) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(algorithm);
            generator.init(keySize);
            SecretKey key = generator.generateKey();
            return key.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            log.error("Generate Key", e);
            throw new CustomException("خطا در تولید کلید رمزگذاری", e);
        }
    }

    public static String encrypt(String plainText, String algorithm, String mode, String padding, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(format("%s/%s/%s", algorithm, mode, padding));
            cipher.init(ENCRYPT_MODE, keySpec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (NoSuchPaddingException e) {
            throw new CustomException("NoSuchPaddingException", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException("NoSuchAlgorithmException", e);
        } catch (InvalidKeyException e) {
            throw new CustomException("InvalidKeyException", e);
        } catch (BadPaddingException e) {
            throw new CustomException("BadPaddingException", e);
        } catch (IllegalBlockSizeException e) {
            throw new CustomException("IllegalBlockSizeException", e);
        }
    }

    public static String encrypt(String plainText, byte[] key) {
        return encrypt(plainText, "AES", "ECB", "PKCS5PADDING", key);
    }

    public static String decrypt(String encryptedText, String algorithm, String mode, String padding, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(format("%s/%s/%s", algorithm, mode, padding));
            cipher.init(DECRYPT_MODE, keySpec);
            byte[] encrypted = Base64.getDecoder().decode(encryptedText);
            return new String(cipher.doFinal(encrypted));
        } catch (NoSuchPaddingException e) {
            throw new CustomException("NoSuchPaddingException", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException("NoSuchAlgorithmException", e);
        } catch (InvalidKeyException e) {
            throw new CustomException("InvalidKeyException", e);
        } catch (IllegalBlockSizeException e) {
            throw new CustomException("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            throw new CustomException("BadPaddingException", e);
        }
    }

    public static String decrypt(String encryptText, byte[] key) {
        return decrypt(encryptText, "AES", "ECB", "PKCS5PADDING", key);
    }

    public static String getIp(HttpServletRequest servletRequest) {
        String ip = servletRequest.getHeader("x-real-ip");
        if (ip == null || ip.isEmpty()) {
            String ipForwarded = servletRequest.getHeader("x-forwarded-for");
            String[] ips = ipForwarded == null ? null : ipForwarded.split(",");
            ip = (ips == null || ips.length == 0) ? null : ips[0];
            ip = (ip == null || ip.isEmpty()) ? servletRequest.getRemoteAddr() : ip;
        }
        return ip;
    }

    public static String prefixCode(String name, String ip, UserAgent userAgent) {
        String prefixTicket = name +
                ip +
                userAgent.getOperatingSystem() +
                userAgent.getBrowser() +
                userAgent.getBrowserVersion();
        return prefixTicket.toLowerCase().trim();
    }

    public static String createCode(String prefixTicket) {
        return prefixTicket + UUID.randomUUID();
    }
}
