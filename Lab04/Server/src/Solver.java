import Interfaces.IRemoteSolver;
import KnapsackProblem.KnapsackSolvingAlgorithms.InstanceProblem;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackSolvingResult;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Solver extends UnicastRemoteObject implements IRemoteSolver {
    public Solver() throws  RemoteException
    {
        super();
    }
    @Override
    public KnapsackSolvingResult solve(InstanceProblem instanceProblem) throws RemoteException {

            System.out.println("RMI works");
            return null;

    }
}
