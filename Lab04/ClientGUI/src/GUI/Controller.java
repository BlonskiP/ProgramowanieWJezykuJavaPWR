package GUI;

import Interfaces.IRemoteServerListRegister;
import Interfaces.IRemoteSolver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Controller {
    private static Registry registry;
    IRemoteServerListRegister ServerRegister;
    private IRemoteSolver solver;
    @FXML
    private TextField PortTF;
    @FXML
    private TextField HostTF;
    @FXML
    void ConnectToRegistry(ActionEvent event)
    {
        System.out.println("Trying to connect");
        String hostname= HostTF.getText();
        int port = Integer.parseInt(PortTF.getText());
        System.out.println("Host: " + hostname + " port " + port);
        try {
            registry = LocateRegistry.getRegistry(hostname,port);
            ServerRegister = (IRemoteServerListRegister)registry.lookup("ServerRegister");

        } catch (RemoteException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(" Remote ERROR");
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        System.out.println("Succesfully connected");
    }

}
