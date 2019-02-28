
package KnapsackSolvingAlgorithms;

import java.util.ArrayList;

public abstract class KnapsackSolvingAlgorithm {
    private String description;
    public abstract String GetDescription(); // returns algorithm description
    public abstract KnapsackSolvingResult Solve(ArrayList<Item> items, int capacity);

}
