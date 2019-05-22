package Cryptography;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;


public class FileDecryptor {
    private static File fileToDecrypt;
    private static File KeyFile;
    private static File destinationFile;
    private static String decryptAlgorith="RSA";

    public static void init() throws Exception {
        System.out.print("Enter a filename to decrypt: ");
        String file = EncryptionManager.sc.nextLine();
        System.out.print("Enter a public key location: ");
        String priv = EncryptionManager.sc.nextLine();
        System.out.print("Enter a destination filename: ");
        String dest = EncryptionManager.sc.nextLine();
        DecryptFile();
    }
    private static void saveToFile(File file, String toSave) throws IOException {
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(toSave);
        fileWriter.close();
    }

    private static PublicKey getKey(File file) throws Exception {
        byte[] keyBytes = Files.readAllBytes(file.toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(decryptAlgorith);
        return kf.generatePublic(spec);
    }


    public static void DecryptFile() throws Exception {
        Cipher cipher = Cipher.getInstance(decryptAlgorith);
        cipher.init(Cipher.DECRYPT_MODE, getKey(KeyFile));
        String messageToDecrypt = EncryptionManager.getMessage(fileToDecrypt);
        System.out.println("Decrypting a message.");
        String decoded = new String(cipher.doFinal(Base64.getMimeDecoder().decode(messageToDecrypt)));
        System.out.println("Saving the decoded message to file.");
        saveToFile(destinationFile, decoded);
        System.out.println("Decoded message:");
        System.out.println(decoded);

    }
}
