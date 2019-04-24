package GUI;

import Classes.ServerInfo;
import Interfaces.IRemoteServerListRegister;
import Interfaces.IRemoteSolver;
import KnapsackProblem.Item;
import KnapsackProblem.KnapsackSolvingAlgorithms.InstanceProblem;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackSolvingResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Controller {
    private static Registry registry;
    static IRemoteServerListRegister ServerRegister;
    ArrayList<Item> items=new ArrayList<>();
    private IRemoteSolver solver;
    @FXML
    private TextField PortTF;
    @FXML
    private ListView itemView;
    @FXML
    private TextField HostTF;
    @FXML
    private TextField NameInput, ValueInput, WeightInput, capacityInput;
    @FXML
    private ListView serverListView;
    private ArrayList<ServerInfo> serverInfos;
    @FXML
    private Label solverName;
    @FXML Label weightLabel;
    @FXML Label valueLabel;
    @FXML
    private ListView resultListView;

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
            System.out.println("Succesfully connected");

        } catch (RemoteException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Dialog");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(" Remote ERROR");
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GetServerList(ActionEvent event)
    {
        System.out.println("Trying to get serverList from" + registry.toString());
        if(ServerRegister!=null && registry !=null)
        try {
            serverInfos= new ArrayList<>(ServerRegister.getServerList());
            if(serverInfos.size()==0)System.out.println("List is empty");
            else{
                serverListView.getItems().clear();
                for (ServerInfo info:serverInfos
                     ) {
                    serverListView.getItems().add(info.toString());
                }

                serverListView.refresh();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void RemoteSolve(ActionEvent event){
        ServerInfo info = serverInfos.get(serverListView.getSelectionModel().getSelectedIndex());
        try {
            solver= (IRemoteSolver) registry.lookup(info.solverName);
            InstanceProblem problem = new InstanceProblem(items,Integer.parseInt(capacityInput.getText()));
            problem.printItems();
            KnapsackSolvingResult result = solver.Solve(problem);
            result.PrintResult();
            updateResult(result,info.solverName);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void AddNewItem(ActionEvent event)
    {
        String itemName=NameInput.getText();
        int itemWeight=Integer.parseInt(WeightInput.getText());
        float itemValue=Float.parseFloat(ValueInput.getText());

        items.add(new Item(itemValue,itemWeight,itemName));
        ItemListRefresh();

    }

    private void ItemListRefresh() {
        itemView.getItems().clear();
        for (Item item: items
             ) {
            itemView.getItems().add(item.toString());
        }
        itemView.refresh();
    }

    private void updateResult(KnapsackSolvingResult result, String solverNameS)
    {
        solverName.setText("Solver Name: "+ solverNameS);
        weightLabel.setText("Weight: "+result.GetBagWeight());
        valueLabel.setText("Value: "+result.getBagValue());
        resultListView.getItems().clear();
        for (Item item : result.getBagItems()
             ) {
            resultListView.getItems().add(item.toString());
        }
    }

}
