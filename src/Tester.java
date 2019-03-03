import KnapsackProblem.Item;
import KnapsackProblem.KnapsackSolvingAlgorithms.*;

import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) {
        ArrayList<Item> bag = new ArrayList<Item>();
        bag.add(new Item(1,1));
        bag.add(new Item(3,2));
        bag.add(new Item(2,4));
        bag.add(new Item(4,1));
        bag.add(new Item(1,3));



        InstanceProblem problem= new InstanceProblem(bag,8);
        KnapsackBForceAlgorithm BF=new KnapsackBForceAlgorithm(problem);
        KnapsackRSearchAlgorithm RS = new KnapsackRSearchAlgorithm(problem);
        RS.Solve().PrintResult();
      //  BF.Solve().PrintResult();
        problem.printItems();
        System.out.println(problem.getCapacity());
    }

}
