package Cryptography;

import java.util.Scanner;

public class EncryptionManager {
    public static void startEncryption(String file)
    {
        System.out.println("What do you want?");
        System.out.println("K - new key pair");
        System.out.println("E - encrypt a file");
        System.out.println("D - decrypt a file");
        System.out.println("End - close program" );
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println(":");
            String userInputFromKeyboard = sc.nextLine();
            if (userInputFromKeyboard.equals("end"))break; // realse from loop
            if(userInputFromKeyboard.equals("K")){}
            else if(userInputFromKeyboard.equals("E")){
                //Encrypt class go here
            }
            else if(userInputFromKeyboard.equals("D")){
                //descrypt class go here
            }
        }
        sc.close();
    }
}