package Classes;

import java.io.Serializable;

public class ServerInfo implements Serializable {
    public String solverName;
    public String AlgorithmInfo;

    public ServerInfo(String solverName, String AlgorithmInfo)  {
        this.solverName=solverName;
        this.AlgorithmInfo=AlgorithmInfo;
    }
    @Override
    public boolean equals(Object d)
    {
        ServerInfo temp=(ServerInfo)d;
        if(temp.solverName==this.solverName)
            return true;
        else
            return false;
    }
}
