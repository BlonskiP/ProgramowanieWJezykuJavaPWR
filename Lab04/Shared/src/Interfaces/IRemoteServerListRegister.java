
package Interfaces;

import Classes.ServerInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServerListRegister extends Remote {
    boolean register(ServerInfo info) throws RemoteException;
}
