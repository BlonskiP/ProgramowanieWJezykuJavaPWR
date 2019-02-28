package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public abstract class KnapsackSolvingAlgorithm {
    protected String description;
    public abstract KnapsackSolvingResult Solve(ArrayList<Item> items, int capacity);
    public String GetDescription() {
        return description;
    }

}
