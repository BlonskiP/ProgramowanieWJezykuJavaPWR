package KnapsackProblem.KnapsackSolvingAlgorithms;

import KnapsackProblem.Item;

import java.util.ArrayList;

public class InstanceProblem {
    private ArrayList<Item> bag;
    private int capacity;
    public InstanceProblem(ArrayList<Item> bag,  int capacity)
    {
        this.bag=bag;

        this.capacity=capacity;
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
    public ArrayList<Item> getBag(){return bag;}

}
