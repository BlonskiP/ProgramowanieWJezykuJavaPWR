package Registry;

import Classes.ServerInfo;
import Interfaces.IRemoteServerListRegister;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerRegistry extends UnicastRemoteObject implements IRemoteServerListRegister {
    ArrayList<ServerInfo> serverInfoList=new ArrayList<>();
    public ServerRegistry() throws RemoteException {
        super();
    }

    @Override
    public boolean register(ServerInfo serverInfo) {

        if(serverInfoList.contains(serverInfo))return false;
        System.out.println("New server registered: " + serverInfo.solverName + " Algorithm: " + serverInfo.AlgorithmInfo);
        return true;
    }

    @Override
    public List<ServerInfo> getServerList() throws RemoteException {
        return serverInfoList;
    }

}
