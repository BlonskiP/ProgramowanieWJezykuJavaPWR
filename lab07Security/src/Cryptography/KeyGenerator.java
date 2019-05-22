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
    public static void run()
    {
        System.out.println("Give me key name:");
        String keyName = EncryptionManager.sc.nextLine();
        System.out.println("Key lenght:");
        keyLength= Integer.parseInt(EncryptionManager.sc.nextLine());
        privateKeyTxt = new File( keyName+ "_privateKey.txt");
        publicKeyTxt = new File(keyName+"_publicKey.txt");
    }

    private static void GenerateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(encryptAlgorithm);
        keygen.initialize(keyLength);
        System.out.println("KeyGen work started");
        KeyPair pair = keygen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        PublicKey pubKey = pair.getPublic();
    }
    private static void toFile(File keyFile ,byte[] key) throws IOException {
        keyFile.createNewFile();
        FileOutputStream fOS= new FileOutputStream(keyFile);
        fOS.write(key);
        fOS.flush();
        fOS.close();
    }

}
