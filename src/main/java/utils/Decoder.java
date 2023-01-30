package utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Decoder {

    public String getPassword(String encodedPassword) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        String algorithm = "AES/CBC/PKCS5Padding";
        String plainText = decrypt(algorithm, encodedPassword, getKey(), getIvParameterSpec());

        return plainText;
    }

    public static SecretKey getKey() {
        try {
            String line = Files.readAllLines(Path.of("/Users/kashinsergei/Desktop/key.txt")).get(0);
            byte[] rawKey = Base64.getDecoder().decode(line);
            SecretKey originalKey = new SecretKeySpec(rawKey, 0, rawKey.length, "AES");
            return originalKey;
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

    }

    public static IvParameterSpec getIvParameterSpec() {
        try {
            String line = Files.readAllLines(Path.of("/Users/kashinsergei/Desktop/key.txt")).get(1);
            byte[] byteArr = Base64.getDecoder().decode(line.getBytes());
            IvParameterSpec bytes = new IvParameterSpec(byteArr);
            return bytes;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    /*public static void generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        byte[] iv = new byte[]{-36, -57, -6, -17, 43, 74, -115, -3, 92, 90, -77, 77, 31, -88, 59, -28};
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/kashinsergei/Desktop/key.txt"))) {
            String key = Base64.getEncoder().encodeToString(keyGenerator.generateKey().getEncoded());
            writer.write(key + "\n");
            writer.write(Base64.getEncoder().encodeToString(iv));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }*/


}
