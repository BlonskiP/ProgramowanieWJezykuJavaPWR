package KnapsackProblem;

public class Item {
    private float value;
    private int weight;
    public Item(float value,int weight){
        this.value=value;
        this.weight=weight;
    }
    public float getValue(){return value;}
    public float getWeight(){return weight;}
}
