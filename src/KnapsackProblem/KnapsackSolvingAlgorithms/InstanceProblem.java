package KnapsackProblem.KnapsackSolvingAlgorithms;

import KnapsackProblem.Item;

import java.util.ArrayList;

public class InstanceProblem {
    private ArrayList<Item> bag;
    private KnapsackSolvingAlgorithm solver;
    private int capacity;
    public InstanceProblem(ArrayList<Item> bag, KnapsackSolvingAlgorithm solver, int capacity)
    {
        this.bag=bag;
        this.solver=solver;
        this.capacity=capacity;
    }
    public KnapsackSolvingResult Solve()
    {
        KnapsackSolvingResult finalResult=solver.Solve(bag,capacity);
        return finalResult;
    }

    public int getCapacity() {
        return capacity;
    }
    public void printItems()
    {
        for (Item item:bag
             ) {
            System.out.println("Value " + item.getValue() + " Weight: " + item.getWeight());

        }
    }

}
