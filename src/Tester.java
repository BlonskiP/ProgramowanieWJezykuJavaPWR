import KnapsackProblem.Item;
import KnapsackProblem.KnapsackSolvingAlgorithms.*;

import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) {
        ArrayList<Item> bag = new ArrayList<Item>();
        for(int i=1;i<4;i++)
        bag.add(new Item(i,i+1));

        InstanceProblem problem= new InstanceProblem(bag,50);
        KnapsackBForceAlgorithm BF=new KnapsackBForceAlgorithm(problem);
        BF.Solve();
        problem.printItems();
        System.out.println(problem.getCapacity());
    }

}
