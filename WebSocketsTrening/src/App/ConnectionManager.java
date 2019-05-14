package App;

import org.w3c.dom.Node;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager extends Thread {
    public static Socket socket;
    public static String listeningPort;
    public static String connectingPort;
    public static String host;
    public static String name;
    public static ConnectionManager instance;
    static ServerSocket serverSocket;

    public ConnectionManager(String name, String listeningPort, String connectingPort, String host)

    {
        ConnectionManager.name = name;
        ConnectionManager.listeningPort=listeningPort;
        ConnectionManager.connectingPort = connectingPort;
        ConnectionManager.host = host;
        ConnectionManager.instance = this;
    }
    static public boolean connect()
    {
        try {
            socket=new Socket(host, Integer.parseInt(connectingPort));
            return true;
        } catch (IOException e) {
           System.out.println("Couldn't connect to: "+host + " port: "+ connectingPort);
           return false;
        }
    }
    static public void portListen() throws IOException {
        System.out.println("Listening on port " + listeningPort);
        serverSocket = new ServerSocket(Integer.parseInt(listeningPort));
        serverSocket.accept();
        System.out.println("Connected");

    }
    public void run()
    {
        try {

            portListen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static public boolean sendMessage(String message, String receiver)
    {
        try {
            Node mess = SoapManager.CreateMessage(message,receiver);
            SoapManager.dumpDocument(mess);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


}
