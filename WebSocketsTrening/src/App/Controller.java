package App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Node;

import javax.sound.midi.Receiver;

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
    TextField ReceiverBox;
    @FXML
    TextArea MessagesLog;
    @FXML
    Label AppName;
    @FXML
    Label MessReceived;

    @FXML
    public void initialize() {
        SetLabels();
        SoapManager.setControler(this);
    }

    public boolean Connect() {
        boolean success = ConnectionManager.connect();
        if (success) {
            AddLog("Connection success Message send");
            System.out.println("Message send");
        } else {
            AddLog("Connection failed");
        }
        return success;

    }

    void SetLabels() {
        AppName.setText(ConnectionManager.name);
        PortLabel.setText(ConnectionManager.listeningPort);
        TargetLabel.setText(ConnectionManager.connectingPort);

    }

    @FXML
    void Send(){
        String message=MessagesTextBox.getText();
        String receiver= ReceiverBox.getText();
        if(Connect()){
        ConnectionManager.sendMessage(message,receiver);
        }

    }
    void AddLog(String mess)
    {
        MessagesLog.setText(MessagesLog.getText()+mess+ "\n");
    }
}
