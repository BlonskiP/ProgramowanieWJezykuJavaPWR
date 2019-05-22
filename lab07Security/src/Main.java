import Cryptography.EncryptionManager;

public class Main {

    public static void main(String[] args) {
        try {
            if (args[0].equals("crypt")) {
                EncryptionManager.startEncryption();
            }
            if (args[0].equals("knapsack")) {

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Args should be: crypt or knapsack");
        }
    }
}
