package l_append;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class EncrypAndDecrypt {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";

    private void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    private String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            // System.out.println("invalid");
        }
        return null;
    }



    public static String toEncryptLog(String mg, String token) {
        String secretKey = token;
        String originalString = mg;

        EncrypAndDecrypt aesEncryptionDecryption = new EncrypAndDecrypt();
        String content_encript = aesEncryptionDecryption.encrypt(originalString, secretKey);

        // System.out.println("originalString: " + originalString);
        // System.out.println("encryptedString: " + content_encript);

        return content_encript;
    }

    public static String decryptPriorFile(String priorfile, String token) {
        String secretKey = token;
        EncrypAndDecrypt aesEncryptionDecryption = new EncrypAndDecrypt();
        try {
            String content_decrypt_prior_file = aesEncryptionDecryption.decrypt(priorfile, secretKey);
            // System.out.println("the content ^^^^^^^^: "+content_decrypt_prior_file);
            return content_decrypt_prior_file;

        } catch (Exception exception) {
            return null;
        }
    }

}