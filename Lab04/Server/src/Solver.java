import Interfaces.IRemoteSolver;
import KnapsackProblem.KnapsackSolvingAlgorithms.*;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Solver extends UnicastRemoteObject implements IRemoteSolver {
    String algorithName;
    public Solver() throws  RemoteException
    {
        super();
    }

    public void setAlgorithName(String algorithName) {
        this.algorithName = algorithName;
    }

    @Override
    public KnapsackSolvingResult Solve(InstanceProblem instanceProblem) throws RemoteException {

        System.out.println("Problem solving request");
        KnapsackSolvingAlgorithm alg;
        instanceProblem.printItems();
        KnapsackSolvingResult result=null;
        switch(algorithName)
        {
            case "brute":{alg=new KnapsackBForceAlgorithm(instanceProblem); result=alg.Solve(); break;}
            case "random":{alg= new KnapsackRSearchAlgorithm(instanceProblem); result=alg.Solve(); break; }
            default:{System.out.println("ERROR WRONG ALGORITH NAME");break;}
        }
        if(result==null)
            System.out.println("ERROR problem with algorithm");
        else
            result.PrintResult();
        return result;

    }
}
