package KanpsackPacage;

import KnapsackProblem.Item;
import KnapsackProblem.KnapsackSolvingAlgorithms.InstanceProblem;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackBForceAlgorithm;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackRSearchAlgorithm;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackSolvingAlgorithm;

import java.util.ArrayList;

public class KnapSolver {

    public static void Solve()
    {
        ArrayList<Item> items=new ArrayList<>();
        items.add(new Item(7,5,"item1"));
        items.add(new Item(4,4,"item2"));
        items.add(new Item(4,3,"item3"));
        items.add(new Item(3,2,"item4"));
        items.add(new Item(1,1,"item5"));

        InstanceProblem newProblem=new InstanceProblem(items,9);
        newProblem.printItems();

        KnapsackSolvingAlgorithm bruteSolver = new KnapsackBForceAlgorithm(newProblem);
        KnapsackSolvingAlgorithm randomSolver = new KnapsackRSearchAlgorithm(newProblem);

        System.out.println(bruteSolver.GetDescription());
        bruteSolver.Solve().PrintResult();

        System.out.println(randomSolver.GetDescription());
        randomSolver.Solve().PrintResult();



    }
}
