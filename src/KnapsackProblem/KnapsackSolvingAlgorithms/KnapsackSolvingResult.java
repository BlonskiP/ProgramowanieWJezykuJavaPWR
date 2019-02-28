package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public class KnapsackSolvingResult {
    private ArrayList<Item> bestItems;
    private int bagValue=0;
    public KnapsackSolvingResult(ArrayList<Item> bag)
    {
        if(bag!=null){
        for (Item item:bag
             ) {
            bagValue+=item.getValue();
        }
        bestItems=bag;
    }
    }

}
