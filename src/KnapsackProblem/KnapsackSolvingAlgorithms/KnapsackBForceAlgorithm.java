package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public class KnapsackBForceAlgorithm extends KnapsackSolvingAlgorithm {


    public KnapsackBForceAlgorithm(InstanceProblem problem)
    {
        super(problem);
        this.description="BruteForce Algorithm";
    }
    @Override
    public KnapsackSolvingResult Solve() {
        if(problem!= null){
            bestResult = new KnapsackSolvingResult(new ArrayList<Item>());
            ArrayList<Item> newBag=new ArrayList<Item>(problem.getBag()); //new object
            subsetsCheck(newBag);
            return this.bestResult;
        }
        else
        return null;
    }

    private void subsetsCheck( ArrayList<Item> bag){
        int numberOfSets=1<<bag.size(); //same as 2^bag.size() because there will be 2^n subsets
        for(int i=0;i<numberOfSets;i++)
        {
            //new subset
            Item tempItem;
            KnapsackSolvingResult newResult = new KnapsackSolvingResult();
            for(int j=0; j < bag.size();j++)
            {

                int subSetBinaryCode=(i&(1<<j));
                if(subSetBinaryCode>0)
                {
                    //fill up newBag
                    tempItem=bag.get(j);
                    if(newResult.GetBagWeight()+tempItem.getWeight()<=problem.getCapacity())
                    newResult.AddNewItem(tempItem);
                    else
                        break;



                }
            }
            //new Result

            compareResults(newResult);
           // newResult.PrintResult(); debug only
        }

    }


    }




