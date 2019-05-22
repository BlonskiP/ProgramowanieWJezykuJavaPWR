package Cryptography;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class KeyGenerator {
    private static File privateKeyTxt;
    private static File publicKeyTxt;
    private static String encryptAlgorithm="RSA";
    private static int keyLength=10;
    public static void run() throws NoSuchAlgorithmException, IOException {
        System.out.println("Give me key name:");
        String keyName = EncryptionManager.sc.nextLine();
        System.out.println("Key lenght:");
        keyLength= Integer.parseInt(EncryptionManager.sc.nextLine());
        privateKeyTxt = new File( keyName+ "_privateKey.txt");
        publicKeyTxt = new File(keyName+"_publicKey.txt");
        GenerateKeys();
    }

    private static void GenerateKeys() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(encryptAlgorithm);
        keygen.initialize(keyLength);
        System.out.println("KeyGen work started");
        KeyPair pair = keygen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        PublicKey pubKey = pair.getPublic();
        System.out.println("KeyPair generated");
        toFile(privateKeyTxt, privKey.getEncoded());
        toFile(publicKeyTxt, pubKey.getEncoded());
    }
    private static void toFile(File keyFile ,byte[] key) throws IOException {
        keyFile.createNewFile();
        FileOutputStream fOS= new FileOutputStream(keyFile);
        System.out.println(keyFile.toPath().toAbsolutePath().toString());
        fOS.write(key);
        fOS.flush();
        fOS.close();
    }

}
