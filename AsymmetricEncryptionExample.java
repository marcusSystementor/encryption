import java.security.*;
import java.util.Base64;

import javax.crypto.Cipher;

public class AsymmetricEncryptionExample {

    public static void main(String[] args) {
        try {

            // Generera ett nyckelpar, keypair för en publik och privat nyckel för RSA-kryptering.
            KeyPair keyPair = generateKeyPair();

            // Meddelande i vanlig text som ska krypteras
            String plaintext = "Det här är ett hemligt meddelande.";


            // Kryptera meddelandet genom att använda public key.
            String encryptedText = encrypt(plaintext, keyPair.getPublic());
            System.out.println("Encrypted Text: " + encryptedText);


            // Dekryptera meddelandet genom att använda den privata nyckeln.
            String decryptedText = decrypt(encryptedText, keyPair.getPrivate());
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generera ett nyckelpar (public and private keys) för RSA-kryptering
    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Du kan välja nyckelsize (e.g., 1024, 2048, or 4096 bits)
        return keyPairGenerator.generateKeyPair();
    }


    // Encrypt a message using RSA encryption with the public key
    private static String encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt an RSA-encrypted message using the private key
    private static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
