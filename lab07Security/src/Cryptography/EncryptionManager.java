package Cryptography;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
// https://www.mkyong.com/java/java-asymmetric-cryptography-example/?fbclid=IwAR2NSz_G6UME3YSLhTe8Zk2td0IK2Csjy5YUL2qqT6y_uoQ-NTo8n8ItsIs
public class EncryptionManager {
    public static Scanner sc;
    public static void startEncryption() throws Exception {

        System.out.println("What do you want?");
        System.out.println("K - new key pair");
        System.out.println("E - encrypt a file");
        System.out.println("D - decrypt a file");
        System.out.println("End - close program" );
        sc = new Scanner(System.in);
        while(true)
        {
            System.out.println(":");
            String userInputFromKeyboard = sc.nextLine();
            if (userInputFromKeyboard.equals("end"))break; // realse from loop
            if(userInputFromKeyboard.equals("K")){
                KeyGenerator.run();
            }
            else if(userInputFromKeyboard.equals("E")){
                FileEncryptor.Init();
            }
            else if(userInputFromKeyboard.equals("D")){
               FileDecryptor.Init();
            }
        }
        sc.close();
    }
    public static String getMessage(File file) throws FileNotFoundException {

        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append("\r\n");
        }
        scanner.close();

        String msg = builder.toString();
        return msg;
    }

}
