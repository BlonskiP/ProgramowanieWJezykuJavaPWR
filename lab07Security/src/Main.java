import Cryptography.EncryptionManager;
import KanpsackPacage.KnapSolver;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            if (args[0].equals("crypt")) {
                EncryptionManager.startEncryption();
            }
            if (args[0].equals("knapsack")) {
                KnapSolver.Solve();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Args should be: crypt or knapsack");
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("There is not such algorithm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
