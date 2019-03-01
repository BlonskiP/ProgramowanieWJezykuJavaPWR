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
        if(bag.size()!=0) // check if empty
        {
           KnapsackSolvingResult newResult = new KnapsackSolvingResult(bag);
           compareResults(newResult);
           bag.remove(0);
           subsetsCheck(bag);

        }

    }

    private boolean compareResults(KnapsackSolvingResult newResult)
    {
        if(newResult.getBagValue()>bestResult.getBagValue())
        {
            bestResult=new KnapsackSolvingResult(newResult);
            return true;
        }
        return false;
    }

}
