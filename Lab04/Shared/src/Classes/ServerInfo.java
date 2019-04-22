package Classes;

import java.io.Serializable;

public class ServerInfo implements Serializable {
    public String solverName;
    public String AlgorithmInfo;

    public ServerInfo(String solverName, String AlgorithmInfo)  {
        this.solverName=solverName;
        this.AlgorithmInfo=AlgorithmInfo;
    }
}
