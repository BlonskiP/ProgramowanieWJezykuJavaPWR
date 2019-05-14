package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static ConnectionManager manager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle(manager.name);
        primaryStage.setScene(new Scene(root, 435, 400));
        primaryStage.show();


    }


    public static void main(String[] args) {
        // arg0 -> Name
        // arg1 -> Listn.Port
        // arg2 -> Connect Port
        // arg3 -> Host
        manager=new ConnectionManager(args[0],args[1],args[2],args[3]);

        launch(args);
    }
}
