package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public abstract class KnapsackSolvingAlgorithm {
    protected String description;
    protected InstanceProblem problem;
    protected KnapsackSolvingResult bestResult;
    public abstract KnapsackSolvingResult Solve();
    public KnapsackSolvingAlgorithm(InstanceProblem problem){this.problem=problem;}

    public String GetDescription() {
        return description;
    }

}
