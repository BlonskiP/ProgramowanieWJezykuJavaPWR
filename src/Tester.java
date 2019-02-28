import KnapsackProblem.Item;
import KnapsackProblem.KnapsackSolvingAlgorithms.*;

import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) {
        ArrayList<Item> bag = new ArrayList<Item>();
        for(int i=0;i<10;i++)
        bag.add(new Item(i,i+1));
        KnapsackBForceAlgorithm BF=new KnapsackBForceAlgorithm();
        InstanceProblem problem= new InstanceProblem(bag,BF,50);
        problem.printItems();
        System.out.println(problem.getCapacity());
    }

}
