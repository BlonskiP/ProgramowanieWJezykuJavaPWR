package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public abstract class KnapsackSolvingAlgorithm {
    protected String description;
    protected InstanceProblem problem;
    protected KnapsackSolvingResult bestResult;
    public abstract KnapsackSolvingResult Solve();
    public KnapsackSolvingAlgorithm(InstanceProblem problem){
        this.problem=problem;
        bestResult=new KnapsackSolvingResult();
    }

    public String GetDescription() {
        return description;
    }
    protected boolean compareResults(KnapsackSolvingResult newResult)
    {
        if(newResult.getBagValue()>bestResult.getBagValue())
        {
            bestResult=new KnapsackSolvingResult(newResult);
            return true;
        }
        return false;
    }
}
