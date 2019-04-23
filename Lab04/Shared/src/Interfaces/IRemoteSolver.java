package Interfaces;

import KnapsackProblem.KnapsackSolvingAlgorithms.InstanceProblem;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackSolvingResult;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteSolver extends Remote {
    KnapsackSolvingResult Solve(InstanceProblem instanceProblem) throws RemoteException;
}
