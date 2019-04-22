import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Registry.ServerRegistry;

public class ServerList {

    public static ServerRegistry serverRegister;
    private static Registry registry;
    private static final String name = "ServerRegister";

    public static void main (String[] argv) throws RemoteException
    {

        try {

            serverRegister = new ServerRegistry();

            registry = LocateRegistry.createRegistry(Integer.parseInt(argv[0]));
            registry.rebind(name, serverRegister);
            System.out.println("Server Register is running");
        }
        catch(RemoteException e)
        {
            System.out.println("Service error: " + e);
        }
    }
}
