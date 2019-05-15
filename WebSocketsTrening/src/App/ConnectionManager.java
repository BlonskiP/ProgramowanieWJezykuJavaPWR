package App;

import org.w3c.dom.Node;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.*;
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
            System.out.println("Connected to " + connectingPort + "ready to send message");
            return true;
        } catch (IOException e) {
           System.out.println("Couldn't connect to: "+host + " port: "+ connectingPort);
           return false;
        }
    }
    static public void portListen(){

        try {
            serverSocket = new ServerSocket(Integer.parseInt(listeningPort));
            System.out.println("Listening on port " + listeningPort);
            while(!serverSocket.isClosed()) {
                Socket s = serverSocket.accept();

                //

                InputStreamReader in = new InputStreamReader(s.getInputStream());
                BufferedReader bf = new BufferedReader(in);

                String input=bf.readLine();
                String receivedMessage=input;
                while ((input = bf.readLine()) != null) {
                    receivedMessage += input;
                }
                bf.close();
                in.close();
                System.out.println("New Message arrived");
                System.out.println(receivedMessage);
                SoapManager.createMessageFromStream(receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void run()
    {
            portListen();
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
    static public boolean sendMessage(String msg)
    {
        OutputStream os = null;
        try {
            os = ConnectionManager.socket.getOutputStream();
            os.write(msg.getBytes());
            os.flush();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}
