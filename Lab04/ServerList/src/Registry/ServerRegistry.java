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

        if(serverInfoList.contains(serverInfo))
        {   System.out.println("Was trying to register new Server: " + serverInfo.solverName + " " + serverInfo.AlgorithmInfo + " \n But I already have it" );
            return false;}
        System.out.println("New server registered: " + serverInfo.solverName + " Algorithm: " + serverInfo.AlgorithmInfo);
        serverInfoList.add(serverInfo);
        return true;
    }

    @Override
    public List<ServerInfo> getServerList(){
        System.out.println("Server List was requested");
        return serverInfoList;
    }

}
