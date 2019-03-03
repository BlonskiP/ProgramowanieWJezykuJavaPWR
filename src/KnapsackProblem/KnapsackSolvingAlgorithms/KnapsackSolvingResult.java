package KnapsackProblem.KnapsackSolvingAlgorithms;
import KnapsackProblem.Item;
import java.util.ArrayList;

public class KnapsackSolvingResult {
    private ArrayList<Item> bagItems;
    private int bagValue=0;
    private int bagWeight=0;
    public KnapsackSolvingResult()
    {
        bagItems=new ArrayList<Item>();
    }
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
            bagWeight+=item.getWeight();
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

    public void PrintResult()
    {
        System.out.println();
        for (Item item:bagItems
             ) {
            System.out.print(item.getValue()+" ");

        }
        System.out.println("capacity:"+ bagWeight);
        System.out.println("value:"+ bagValue);

    }
    public void AddNewItem(Item item)
    {
        bagItems.add(item);
        bagValue+=item.getValue();
        bagWeight+=item.getWeight();
    }
    public int GetBagWeight(){return bagWeight;}




}
