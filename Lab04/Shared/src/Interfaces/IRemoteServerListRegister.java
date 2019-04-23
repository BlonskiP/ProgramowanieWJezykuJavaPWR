
package Interfaces;

import Classes.ServerInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteServerListRegister extends Remote {
    boolean register(ServerInfo info) throws RemoteException;
    List<ServerInfo> getServerList() throws  RemoteException;
}
