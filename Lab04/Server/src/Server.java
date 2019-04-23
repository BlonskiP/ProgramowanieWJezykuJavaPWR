//Aplikacja serwera powinna implementować metodę S solve( I ), możliwą do wywołania poprzez RMI,
// gdzie I jest jakąś klasą reprezentującą instancję problemu plecakowego,
// zaś S jest jakąś klasą zawierającą rozwiązanie problemu plecakowego.

import Classes.ServerInfo;
import Interfaces.IRemoteServerListRegister;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

        public static Solver solver;
        public static Registry registry;
        private String host;
        private int port;
        private String remoteObjName;
        private String algorithm;

        public Server(String host, int port, String remoteObjName, String algorithm)
        {
          this.host=host;
          this.port=port;
          this.remoteObjName=remoteObjName;
          this.algorithm=algorithm;
        }
    // 1st arg is host
    // 2nd arg is port
    // 3rd is remoteObject name
    //4rd is knapsac algorithm
        public static void main (String[] argv) throws RemoteException
        {
            try {
                int port = Integer.parseInt(argv[1]);
                Server server=new Server(argv[0],port,argv[2], argv[3]);
                server.ConnectToRegistry(argv[0],port,argv[2]);
                server.registerObject(solver);

                System.out.println("Server is running");
            }
            catch(RemoteException e) {
                System.out.println("Service error: " + e);
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        private void ConnectToRegistry(String host, int port, String objName) throws RemoteException {
            solver = new Solver();
            registry = LocateRegistry.getRegistry(host, port);
            registry.rebind(objName, solver);
        }

        private void registerObject(Solver solver) throws RemoteException, NotBoundException, MalformedURLException {
            ServerInfo serverInfo=new ServerInfo(this.remoteObjName,this.algorithm);
            IRemoteServerListRegister serverRegister = (IRemoteServerListRegister) registry.lookup("ServerRegister");
            if(serverRegister.register(serverInfo)){
                System.out.println("Server has been registered");
            }
            else
                System.out.println("Register error");
        }
    }

