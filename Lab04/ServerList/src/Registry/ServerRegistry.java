package Registry;

import Classes.ServerInfo;
import Interfaces.IRemoteServerListRegister;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRegistry extends UnicastRemoteObject implements IRemoteServerListRegister {
    public ServerRegistry() throws RemoteException {
        super();
    }

    @Override
    public boolean register(ServerInfo serverInfo) {


        System.out.println("New server registered: " + serverInfo.solverName + " Algorithm: " + serverInfo.AlgorithmInfo);
        return true;
    }

}
