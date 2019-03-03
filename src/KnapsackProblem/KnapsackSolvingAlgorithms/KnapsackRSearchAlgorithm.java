package KnapsackProblem.KnapsackSolvingAlgorithms;

import KnapsackProblem.Item;

import java.util.ArrayList;
import java.util.Random;

public class KnapsackRSearchAlgorithm extends KnapsackSolvingAlgorithm {

    private Random rnd  = new Random();
    public  KnapsackRSearchAlgorithm(InstanceProblem problem)
    {
        super(problem);
        description="Random Search Algorithm";
    }
    @Override
    public KnapsackSolvingResult Solve() {
        ArrayList<Item> bag= new ArrayList<>(problem.getBag());

        int randomItemIndex;
        Item tempItem;
        KnapsackSolvingResult newResult;
        for(int i=0;i<100;i++)
        {
            newResult=new KnapsackSolvingResult();
            while(newResult.GetBagWeight()<=problem.getCapacity() && bag.size()>0)
            {
            randomItemIndex=rnd.nextInt(bag.size());
            tempItem= bag.get(randomItemIndex);
            if(newResult.GetBagWeight()+tempItem.getWeight()<problem.getCapacity())
            {
                newResult.AddNewItem(tempItem);
                bag.remove(tempItem);

            }
            else if(bag.size()>0)
            {
                bag.remove(tempItem);
            }
            else
            {
                break;
            }
            }
            compareResults(newResult);
            bag= new ArrayList<>(problem.getBag());
        }

        return bestResult;
    }
}
