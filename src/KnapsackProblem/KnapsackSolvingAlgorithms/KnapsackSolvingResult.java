package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public class KnapsackSolvingResult {
    private ArrayList<Item> bagItems;
    private int bagValue=0;
    public KnapsackSolvingResult(ArrayList<Item> bag)
    {
        if(bag!=null){
            bagItems=bag;
            countValue();
        }

    }

    private void countValue() {
        bagValue=0;
        for (Item item:bagItems
        ) {
            bagValue+=item.getValue();
        }
    }

    public KnapsackSolvingResult(KnapsackSolvingResult newResult)  {
        bagItems=newResult.getBagItems();
        countValue();

    }

    public int getBagValue() {
        return bagValue;
    }

    public ArrayList<Item> getBagItems(){ return this.bagItems; }


}
