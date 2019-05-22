package Cryptography;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class FileEncryptor {
    private static File fileToEncrypt;
    private static File KeyFile;
    private static File destinationFile;
    private static String encryptAlgorith="RSA";
    public static void Init() throws Exception {
        System.out.print("Enter a filename to encrypt: ");
        String file = EncryptionManager.sc.nextLine();
        System.out.print("Enter a private key location: ");
        String priv = EncryptionManager.sc.nextLine();
        System.out.print("Enter a destination filename: ");
        String dest = EncryptionManager.sc.nextLine();


        KeyFile = new File(priv);
        fileToEncrypt = new File(file);
        destinationFile = new File(dest);
        EncryptFile();
    }
    private static PrivateKey getKey(File file) throws Exception {
        Path filepath = file.toPath();
        byte[] keyBytes = Files.readAllBytes(filepath);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(encryptAlgorith);
        return kf.generatePrivate(spec);
    }


    private static void saveToFile(File file, String toSave) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(toSave);
        fileWriter.close();
    }

    private static void EncryptFile() throws Exception {
        Cipher cipher = Cipher.getInstance(encryptAlgorith); // Cipher pol -> Szyfr klasa do szyfrowania w Javie
        //This class provides the functionality of a cryptographic cipher for encryption and decryption. It forms the core of the Java Cryptographic Extension (JCE) framework.
        cipher.init(Cipher.ENCRYPT_MODE, getKey(KeyFile));
        String fileBody = EncryptionManager.getMessage(fileToEncrypt);
        //This class consists exclusively of static methods for obtaining encoders and decoders for the Base64 encoding scheme. The implementation of this class supports the following types of Base64 as specified in RFC 4648 and RFC 2045.
        String encryptedBody = Base64.getEncoder().encodeToString(cipher.doFinal(fileBody.getBytes("UTF-8")));
        System.out.println("Encoding complete with no Exceptions");
        saveToFile(destinationFile,encryptedBody);

    }

}
