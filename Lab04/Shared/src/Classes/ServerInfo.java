package Classes;

import java.io.Serializable;

public class ServerInfo implements Serializable {
    public String solverName;
    public String AlgorithmInfo;
    public String host;

    public ServerInfo(String solverName, String AlgorithmInfo, String host)  {
        this.solverName=solverName;
        this.AlgorithmInfo=AlgorithmInfo;
        this.host=host;
    }
    @Override
    public boolean equals(Object d)
    {
        ServerInfo temp=(ServerInfo)d;
        if(temp.solverName.equals(this.solverName) && temp.host.equals(this.host))
            return true;
        else
            return false;
    }
    @Override
    public String toString()
    {
        return solverName+" "+AlgorithmInfo+" "+host;
    }
}
