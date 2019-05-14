package App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    Button ConnectBtn;
    @FXML
    Button ListenBtn;
    @FXML
    Label PortLabel;
    @FXML
    Label TargetLabel;
    @FXML
    TextArea MessagesTextBox;
    @FXML
    TextArea MessagesLog;
    @FXML
    Label AppName;
    @FXML
    Label MessReceived;
    @FXML
    public void initialize()
    {
        SetLabels();
    }

    @FXML
    public void portListen()
    {
        AddLog("Listening on port " + ConnectionManager.listeningPort);
        ConnectionManager.instance.start();
    }
    @FXML
    public void Connect()
    {
        AddLog("Trying to connect port: "+ConnectionManager.connectingPort);
        if(ConnectionManager.connect())
        {
            AddLog("Connection success");
        }
        else
        {
        AddLog("Connection failed");
        }

    }
    void SetLabels(){
        AppName.setText(ConnectionManager.name);
        PortLabel.setText(ConnectionManager.listeningPort);
        TargetLabel.setText(ConnectionManager.connectingPort);

    }
    void AddLog(String mess)
    {
        MessagesLog.setText(MessagesLog.getText()+mess+ "\n");
    }
}
