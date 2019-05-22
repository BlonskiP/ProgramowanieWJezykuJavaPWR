package Cryptography;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class EncryptionManager {
    public static Scanner sc;
    public static void startEncryption() throws NoSuchAlgorithmException, IOException {

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
                //Encrypt class go here
            }
            else if(userInputFromKeyboard.equals("D")){
                //descrypt class go here
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
        return builder.toString();
    }

}
